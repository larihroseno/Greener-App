package com.example.greener1;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.core.Tag;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {


    public static final String TAG = "TAG";

    //variables that we have to create to get information
    EditText etUsername, etEmail, etPassword;
    Button btRegister;
    TextView mLoginBtn;
    FirebaseFirestore fStore;
    String userID;

    //using firebase to register the user
    FirebaseAuth fAuth;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this line is showing which xml file we will work on
        setContentView(R.layout.activity_register);

        //variable to work with xml file

        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        mLoginBtn=findViewById(R.id.mLoginBtn);

        //variable to the register botton

        btRegister = findViewById(R.id.btRegister);

        //getting instance of the firebase
        fAuth = FirebaseAuth.getInstance();
        fStore= FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);





        //when use the click gedt iknfoprmation from the filed  and pass it to register redquest and execute the register request
        btRegister.setOnClickListener(new View.OnClickListener() {

            //here is going all the operation
            @Override
            public void onClick(View v) {

                // get variables to store, convert it to string because it inicially comes as an object - trim to format the data.

                final String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                final String userName =  etUsername.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    etEmail.setError("Email is required.");
                }
                if (TextUtils.isEmpty(password)) {
                    etPassword.setError("Password is required.");
                    return;
                }
                if (password.length() < 6) {
                    etPassword.setError("Password Must be >= 6 Characteres");
                }


                progressBar.setVisibility(View.VISIBLE);

                //register the user in firebase

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //se if registration is succesful or not

                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                            userID= fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("user").document(userID);
                            Map<String, Object> user= new HashMap<>();
                            user.put("userName", userName);
                            user.put("email", email);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    Log.d(TAG, "OnSuccess: user progile is created for" +userID);

                                }
                            });


                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));

                        } else {
                            Toast.makeText(RegisterActivity.this, "ERROR!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                   }

                });
            }

        });

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));

            }
        });

    }
}