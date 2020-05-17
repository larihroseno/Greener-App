package com.example.greener1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

//class that contains information about the APP

public class About extends AppCompatActivity {
    //using viewPager to display two page in one(two slides)


// ViewPager objects have built-in swipe gestures to transition through pages
// and they display screen slide animations by default

    ViewPager viewPager;

    //two pages using LAYOUTS
    int[] layouts;
    //adapter to make the transition

    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


       //id of the page "activity_about"
        viewPager = findViewById(R.id.pager);

        //layouts created as activities
        layouts = new int[]{
                R.layout.slidermain,
                R.layout.slidermain2

        };

        adapter = new Adapter(this, layouts);
        viewPager.setAdapter(adapter);

    }


      //menuBar to show the options at the top of the page

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

            Intent greenIntent= new Intent(this,GreenTech.class);

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
