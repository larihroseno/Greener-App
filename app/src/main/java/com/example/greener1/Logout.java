package com.example.greener1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Logout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);




        Button fab = findViewById(R.id.btYes);
        fab.setOnClickListener(new View.OnClickListener() {


            @Override

            public void onClick(View fab) {

                goToYesAnswear();

            }



        });

        Button fab2= findViewById(R.id.btNo);
        fab2.setOnClickListener(new View.OnClickListener() {


            @Override

            public void onClick(View fab) {

                goToNoAnswear();

            }



        });




    }
    private void  goToYesAnswear() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();



    }
    private void  goToNoAnswear() {

        Intent intent = new Intent(this, UserAreaActivity.class);

        startActivity(intent);

    }






}

