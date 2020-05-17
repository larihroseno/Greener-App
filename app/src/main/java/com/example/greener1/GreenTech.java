package com.example.greener1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



//this class content is to display the images of the students who worked in te project

public class GreenTech extends AppCompatActivity {

   //images set in the activity xml file

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_tech);


    }


    //set a menu Bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);



        return true;
    }

    //The startActivity() method starts an instance of the Activity that's specified by the Intent.
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();

        if( id == R.id.about)
        {

            Intent aboutIntent = new Intent(this, About.class);
            startActivity(aboutIntent);
        }
        if( id == R.id.greenTech)
        {

            Intent greenIntent= new Intent(this, GreenTech.class);

            startActivity(greenIntent);

        }

        if( id == R.id.main)
        {
            Intent intent = new Intent(this, MainActivity.class);

            startActivity(intent);


        }



        return super.onOptionsItemSelected(item);
    }



}
