package com.example.greener1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


//class to logout out of the app
//the inital idea was to use "Firebase signout" function
// but it got a little trick at the end, so Ive decide to use bottons to logout

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

    //The startActivity() method starts an instance of the Activity that's specified by the Intent.

    //if yes button is pressed it will take the user to the login page.

    private void  goToYesAnswear() {

        Intent intent = new Intent(this, LoginActivity.class);

        startActivity(intent);



    }
    //if nobutton is pressed it will take the user to the Dashboard Activity where all the posts are
    private void  goToNoAnswear() {

        Intent intent = new Intent(this, DashboardActivity.class);

        startActivity(intent);

    }






}

