package com.example.greener1;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class AddReusable extends AppCompatActivity {


    FirebaseAuth fAuth;
    StorageReference storageReference;
    ActionBar actionBar;


    FirebaseFirestore fStore;
    FirebaseUser user;

    //user info
    String userId, userName, email, dp;

    //permissions constants

    private static final int CAMERA_REQUEST_CODE = 1000;
    private static final int STORAGE_REQUEST_CODE = 2000;

    //image pick constants
    private static final int IMAGE_PICK_CAMERA_CODE = 1888;
    private static final int IMAGE_PICK_GALLERY_CODE = 1889;


    //PERMISSIONS ARRAY
    String[] cameraPermissions;
    String[] storagePermissions;


    EditText titleEt, descriptionEt;
    ImageView imageView;
    Button uploadBtn;
    //image picked will be samed in this uri
    Uri image_uri = null;

    //progress bar
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reusable);
        actionBar = getSupportActionBar();
        actionBar.setTitle("ADD New Post");
        //enable back button in action bar
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        //init permissions array
        cameraPermissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};


        fAuth = FirebaseAuth.getInstance();

        userId = fAuth.getCurrentUser().getUid();

        user = fAuth.getCurrentUser();

        actionBar.setSubtitle(email);

        //get some infor of current user to include in post
        fStore = FirebaseFirestore.getInstance();
        final DocumentReference documentReference = fStore.collection("user").document(userId);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {

            @Override
            public void onEvent(@NonNull DocumentSnapshot documentSnapshot, @NonNull FirebaseFirestoreException e) {

                userName = "" + documentSnapshot.getString("userName");
                email = "" + documentSnapshot.getString("email");
                dp = "" + documentSnapshot.get("dp");

            }

        });


        //init views
        titleEt = findViewById(R.id.pTitleET);
        descriptionEt = findViewById(R.id.pDescriptionET);
        imageView = findViewById(R.id.imageView);
        uploadBtn = findViewById(R.id.pUploadBtn);
        pd = new ProgressDialog(this);

        //get image from camera/gallery on click
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImagePickDialog();

            }
        });


        // upload button click listener
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get ata(title, description) from EditTexts

                String title = titleEt.getText().toString().trim();
                String description = descriptionEt.getText().toString().trim();
                if (TextUtils.isEmpty(title)) {

                    Toast.makeText(AddReusable.this, "Enter Title", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(description)) {
                    Toast.makeText(AddReusable.this, "Enter Description...", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (image_uri == null) {
                    //post without image
                    uploadData(title, description, "noimage");

                } else {
                    uploadData(title, description, String.valueOf(image_uri));
                }

            }
        });

    }

    private void uploadData(final String title, final String description, String uri) {
        pd.setMessage("Publishing post...");
        pd.show();
        //for post-image name, post-id, post-publishi-time
        final String timeStamp = String.valueOf(System.currentTimeMillis());
        String filePathAndName = "Posts" + "post_" + timeStamp;

        if (!uri.equals("noImage")) {
            //post with image

            StorageReference ref = FirebaseStorage.getInstance().getReference().child(filePathAndName);
            ref.putFile(Uri.parse(uri))
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //image is uploaded to firebase storage, now get its url
                            Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                            while (!uriTask.isSuccessful()) ;
                            String downloadUri = uriTask.getResult().toString();

                            if (uriTask.isSuccessful()) {
                                //url is recveid upload post to firebase fiestore
                                HashMap<Object, String> hashMap = new HashMap<>();
                                //put post info
                                hashMap.put("uid", userId);
                                hashMap.put("userName", userName);
                                hashMap.put("uDp", dp);
                                hashMap.put("email", email);
                                hashMap.put("pId", timeStamp);
                                hashMap.put("pTitle", title);
                                hashMap.put("pDescr", description);
                                hashMap.put("profileImage", downloadUri);
                                hashMap.put("pTime", timeStamp);


                                //PATH TO STORE POST DATA
                                FirebaseFirestore db = FirebaseFirestore.getInstance();
                                db.collection("Posts");
                                db.collection("Posts").add(hashMap)

                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {


                                                //added in firestore
                                                pd.dismiss();
                                                Toast.makeText(AddReusable.this, "Post Published", Toast.LENGTH_SHORT).show();
                                                //reset views
                                                titleEt.setText("");
                                                descriptionEt.setText("");
                                                imageView.setImageURI(null);
                                                image_uri = null;


                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                //failed uploading image
                                                pd.dismiss();
                                                Toast.makeText(AddReusable.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        }
                    })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //failed uploading image
                            pd.dismiss();
                            Toast.makeText(AddReusable.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });


        } else {
            HashMap<Object, String> hashMap = new HashMap<>();
            //put post info
            hashMap.put("uid", userId);
            hashMap.put("userName", userName);
            hashMap.put("uDp", dp);
            hashMap.put("email", email);
            hashMap.put("pId", timeStamp);
            hashMap.put("pTitle", title);
            hashMap.put("pDescr", description);
            hashMap.put("profileImage", "noImage");
            hashMap.put("pTime", timeStamp);


            //PATH TO STORE POST DATA
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("Posts");
            db.collection("Posts").add(hashMap)

                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {


                            //added in firestore
                            pd.dismiss();
                            Toast.makeText(AddReusable.this, "Post Published", Toast.LENGTH_SHORT).show();
                            //reset views
                            titleEt.setText("");
                            descriptionEt.setText("");
                            imageView.setImageURI(null);
                            image_uri = null;


                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //failed uploading image
                            pd.dismiss();
                            Toast.makeText(AddReusable.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

            //post without image
        }
    }


    private void showImagePickDialog() {
                            //options(camer, gallery) to show in dialog

                            String[] options = {"Camera", "Galery"};

                            //dialog
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            builder.setTitle("Choose Image from");

                            //set options to dialog
                            builder.setItems(options, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //item click handle
                                    if (which == 0) {
                                        //camera clicked
                                        if (!checkCameraPermission()) {
                                            requestCameraPermission();
                                        } else {
                                            pickFromCamera();
                                        }
                                    }
                                    if (which == 1) {

                                        //gallery clicked
                                        if (!checkStoragePermission()) {
                                            requestStoragePermission();

                                        } else {
                                            pickFromGallery();
                                        }

                                    }


                                }
                            });

                            //create and show dialog
                            builder.create().show();
                        }

                        private void pickFromGallery() {
                            // intent to pick image from gallery

                            Intent intent = new Intent(Intent.ACTION_PICK);
                            intent.setType("image/*");
                            startActivityForResult(intent, IMAGE_PICK_GALLERY_CODE);

                        }

                        private void pickFromCamera() {

                            //intent to pick image from camera
                            ContentValues cv = new ContentValues();
                            cv.put(MediaStore.Images.Media.TITLE, "Temp Pick");
                            cv.put(MediaStore.Images.Media.DESCRIPTION, "Temp Descr");
                            image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, cv);


                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
                            startActivityForResult(intent, IMAGE_PICK_CAMERA_CODE);
                        }


                        private boolean checkStoragePermission() {
                            //check if storage permission is enabled or not

                            //return true if enabled
                            //return false if not enabled

                            boolean result = ContextCompat.checkSelfPermission(this,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
                            return result;
                        }

                        private void requestStoragePermission() {
                            //request runtime storage permission

                            ActivityCompat.requestPermissions(this, storagePermissions, STORAGE_REQUEST_CODE);


                        }

                        private boolean checkCameraPermission() {
                            //check if camera permission is enabled or not

                            //return true if enabled
                            //return false if not enabled

                            boolean result = ContextCompat.checkSelfPermission(this,
                                    Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);

                            boolean result1 = ContextCompat.checkSelfPermission(this,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
                            return result && result1;
                        }

                        private void requestCameraPermission() {


                            //request runtime camera permission

                            ActivityCompat.requestPermissions(this, cameraPermissions, CAMERA_REQUEST_CODE);

                        }





                        @Override
                        public boolean onSupportNavigateUp() {
                            onBackPressed();
                            return super.onSupportNavigateUp();
                        }



                        //handle permissions results
                        @Override
                        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
                            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                            //this method is called when user press Allow or Deny from permission request dialog
                            //here we will handle permission cases(allowed and denied)

                            switch (requestCode) {

                                case CAMERA_REQUEST_CODE: {
                                    if (grantResults.length > 0) {
                                        boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                                        if (cameraAccepted) {
                                            //both permissions are granted

                                            pickFromCamera();
                                        } else {
                                            //camera or gallery or both permissions were denied

                                            Toast.makeText(this, "Camera & Storage both permissions are necessary...", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {

                                    }
                                }
                                break;
                                case STORAGE_REQUEST_CODE: {
                                    if (grantResults.length > 0) {

                                        boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                                        if (storageAccepted) {
                                            //storage permissons are granted

                                            pickFromGallery();
                                        } else {
                                            //camera or gallery or both permissions were denied

                                            Toast.makeText(this, " Storage permissions are necessary...", Toast.LENGTH_SHORT).show();

                                        }

                                    } else {

                                    }


                                }
                                break;
                            }
                        }

                        @Override
                        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
                            //this method will be called after picking image from camera or gallery

                            if (resultCode == RESULT_OK) {

                                if (requestCode == IMAGE_PICK_GALLERY_CODE) {
                                    //image is picked from gallery, get uri of image
                                    image_uri = data.getData();

                                    //set to imageview
                                    imageView.setImageURI(image_uri);
                                } else if (requestCode == IMAGE_PICK_CAMERA_CODE) {
                                    //image is picked from camera, get uri of image

                                    imageView.setImageURI(image_uri);
                                }

                            }
                            super.onActivityResult(requestCode, resultCode, data);
                        }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);



        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();

        if (id == R.id.home)
        {

            Intent homeIntent = new Intent(this, HomeFragment.class);
            startActivity(homeIntent);
        }
        if (id == R.id.profile)
        {

            Intent settingsIntent= new Intent(this, SettingsUser.class);

            startActivity(settingsIntent);

        }
        if( id == R.id.logOut)
        {
            Intent challengeIntent = new Intent(this, Logout.class);

            startActivity(challengeIntent);


        }
        if (id == R.id.users)
        {
            Intent logoutIntent = new Intent(this, Logout.class);

            startActivity(logoutIntent);


        }
        if (id == R.id.challenge)
        {
            Intent logoutIntent = new Intent(this, UserAreaActivity.class);

            startActivity(logoutIntent);


        }





        return super.onOptionsItemSelected(item);
    }

}

















