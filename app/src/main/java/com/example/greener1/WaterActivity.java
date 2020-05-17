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

public class WaterActivity extends AppCompatActivity {
    //using viewPager to display two page in one (two slides)


    // ViewPager objects have built-in swipe gestures to transition through pages
// and they display screen slide animations by default
    ViewPager viewPager;
    //create buttons to navigation to other two pages
    Button btnGlass, btnFull;
    int[] layouts;
    Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);

        viewPager = findViewById(R.id.pager);
        btnGlass= findViewById(R.id.btnGoGlass);
        btnFull= findViewById(R.id.btnFull);

        //init layouts

        layouts = new int[]{
                R.layout.slider3,
                R.layout.slider4

        };

        adapter = new Adapter(this, layouts);
        viewPager.setAdapter(adapter);

        Button fab = findViewById(R.id.btnGoGlass);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick (View fab){


                goToGlassActivity();

            }
        });

        Button fab2 = findViewById(R.id.btnFull);
        fab2.setOnClickListener(new View.OnClickListener() {


            @Override

            public void onClick(View fab2) {

                goToFullActivity();

            }



        });




    }
    private void goToGlassActivity() {

        Intent intent = new Intent(this, GoGlass.class);

        startActivity(intent);

    }
    private void  goToFullActivity() {

        Intent intent2 = new Intent(this, GoFull.class);

        startActivity(intent2);

    }

    //set menu Bar

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);



        return true;
    }

    //The startActivity() method starts an instance of the Activity that's specified by the Intent.

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



