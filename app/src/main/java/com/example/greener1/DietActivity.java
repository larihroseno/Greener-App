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

public class DietActivity extends AppCompatActivity {

    //using viewPager to display two page in one (two slides)


    // ViewPager objects have built-in swipe gestures to transition through pages
// and they display screen slide animations by default
    ViewPager viewPager;
    //create buttons to navigation to other two pages
    Button btnDairy, btnVegan;
    int[] layouts;
    Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

        //init
        viewPager = findViewById(R.id.pager);
        btnDairy= findViewById(R.id.btnDairy);
        btnVegan= findViewById(R.id.btnVegan);

        //init the layouts
        layouts = new int[]{
                R.layout.slider5,
                R.layout.slider6

        };

        adapter = new Adapter(this, layouts);
        viewPager.setAdapter(adapter);

        Button fab = findViewById(R.id.btnDairy);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick (View fab){


                goToDairyActivity();

            }
        });

        Button fab2 = findViewById(R.id.btnVegan);
        fab2.setOnClickListener(new View.OnClickListener() {


            @Override

            public void onClick(View fab2) {

                goToVeganActivity();

            }



        });




    }

    //The startActivity() method starts an instance of the Activity that's specified by the Intent.

    private void goToDairyActivity() {

        Intent intent = new Intent(this, GoDairy.class);

        startActivity(intent);

    }
    private void  goToVeganActivity() {

        Intent intent2 = new Intent(this, GoVegan.class);

        startActivity(intent2);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);



        return true;
    }//The startActivity() method starts an instance of the Activity that's specified by the Intent.

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



