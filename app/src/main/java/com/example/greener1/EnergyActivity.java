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
import androidx.viewpager.widget.ViewPager;

public class EnergyActivity extends AppCompatActivity {

    //using viewPager to display two page in one (two slides)


    // ViewPager objects have built-in swipe gestures to transition through pages
// and they display screen slide animations by default
    ViewPager viewPager;
    //create buttons to navigation to other two pages
    Button btnWalk, btnSave;
    int[] layouts;
    Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_energy);

        //init
        viewPager = findViewById(R.id.pager);
        btnWalk= findViewById(R.id.btnWalk);
        btnSave= findViewById(R.id.btnSave);

        //init the layouts

        layouts = new int[]{
                R.layout.slider7,
                R.layout.slider8

        };

        adapter = new Adapter(this, layouts);
        viewPager.setAdapter(adapter);

        Button fab = findViewById(R.id.btnWalk);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick (View fab){


                goToWalkActivity();

            }
        });

        Button fab2 = findViewById(R.id.btnSave);
        fab2.setOnClickListener(new View.OnClickListener() {


            @Override

            public void onClick(View fab2) {

                goToSaveActivity();

            }



        });




    }

    //The startActivity() method starts an instance of the Activity that's specified by the Intent.

    private void goToWalkActivity() {

        Intent intent = new Intent(this, GoWalk.class);

        startActivity(intent);

    }
    private void  goToSaveActivity() {

        Intent intent2 = new Intent(this, GoSave.class);

        startActivity(intent2);

    }

    //set menu bar

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



