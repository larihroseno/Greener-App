

package com.example.greener1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


// this class has two buttons with two different options to follow
//ADD EVIDENNCE in case the user wnats to post something
//Back button in case the user desire to choose another challenge to complete

public class GoFull extends AppCompatActivity {

    //set buttons
    Button btnAddEvidence, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_full);

        //init buttons
        btnAddEvidence= findViewById(R.id.btnAdd);
        btnBack= findViewById(R.id.btnBack);




        Button fab = findViewById(R.id.btnAdd);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick (View fab){


                goToEvidenceActivity();

            }
        });

        Button fab2 = findViewById(R.id.btnBack);
        fab2.setOnClickListener(new View.OnClickListener() {


            @Override

            public void onClick(View fab2) {

                goToBackActivity();

            }



        });




    }

    //The startActivity() method starts an instance of the Activity that's specified by the Intent.
    private void goToEvidenceActivity() {

        Intent intent = new Intent(this, AddEvidence.class);

        startActivity(intent);

    }
    private void  goToBackActivity() {

        Intent intent2 = new Intent(this, WaterActivity.class);

        startActivity(intent2);

    }


     //set menuBar

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



