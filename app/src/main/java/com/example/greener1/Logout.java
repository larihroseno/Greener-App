package com.example.greener1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

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

        Intent intent = new Intent(this, LoginActivity.class);

        startActivity(intent);



    }
    private void  goToNoAnswear() {

        Intent intent = new Intent(this, DashboardActivity.class);

        startActivity(intent);

    }






}

