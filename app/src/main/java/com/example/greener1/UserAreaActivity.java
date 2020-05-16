package com.example.greener1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class UserAreaActivity extends AppCompatActivity {
    AppCompatImageButton water, waste, diet, energy;
    TextView welcomeMsg;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;

    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        water = findViewById(R.id.btnWater);
        waste = findViewById(R.id.btnWaste);
        diet = findViewById(R.id.btnDiet);
        energy = findViewById(R.id.btnEnergy);
        welcomeMsg = findViewById(R.id.welcomeMsg);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();

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

        if (id == R.id.home)
        {

            Intent homeIntent = new Intent(this, DashboardActivity.class);
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

        if (id == R.id.challenge)
        {
            Intent logoutIntent = new Intent(this, UserAreaActivity.class);

            startActivity(logoutIntent);


        }





        return super.onOptionsItemSelected(item);
    }


        }
