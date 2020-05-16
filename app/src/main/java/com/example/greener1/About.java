package com.example.greener1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class About extends AppCompatActivity {


    ViewPager viewPager;
    int[] layouts;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);



        viewPager = findViewById(R.id.pager);

        layouts = new int[]{
                R.layout.slidermain,
                R.layout.slidermain2

        };

        adapter = new Adapter(this, layouts);
        viewPager.setAdapter(adapter);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);



        return true;
    }

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
