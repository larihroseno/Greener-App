package com.example.greener1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.auth.User;

import org.w3c.dom.Document;

public class UserAreaActivity extends AppCompatActivity {
    AppCompatImageButton water, waste, diet, energy;
    TextView welcomeMsg;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        water = findViewById(R.id.btnWater);
        waste = findViewById(R.id.btnWaste);
        diet = findViewById(R.id.btnDiet);
        energy = findViewById(R.id.btnEnergy);
        welcomeMsg=findViewById(R.id.welcomeMsg);

        fAuth =FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();

        userId=fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("user").document(userId);

        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                        welcomeMsg.setText(documentSnapshot.getString("userName"));
                    }
                });

                water.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), WaterActivity.class));

                    }
                });
                waste.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), WasteActivity.class));

                    }
                });
                diet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), DietActivity.class));

                    }
                });
                energy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), EnergyActivity.class));

                    }
                });


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

                if( id == R.id.item1)
                {

                    Intent homeIntent = new Intent(this, UserAreaActivity.class);
                    startActivity(homeIntent);
                }
                if( id == R.id.item2)
                {

                    Intent settingsIntent= new Intent(this, SettingsUser.class);

                    startActivity(settingsIntent);

                }
                if( id == R.id.item3)
                {
                    Intent challengeIntent = new Intent(this, ChallengesHistory.class);

                    startActivity(challengeIntent);


                }
                if( id == R.id.item4)
                {
                    Intent logoutIntent = new Intent(this, Logout.class);

                    startActivity(logoutIntent);


                }




                return super.onOptionsItemSelected(item);
            }



        }
