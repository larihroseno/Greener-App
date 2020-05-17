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

public class WasteActivity extends AppCompatActivity {
    ViewPager viewPager;
    Button btnReusable, btnRecycle;
    int[] layouts;
    Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waste);

        viewPager = findViewById(R.id.pager);
        btnReusable= findViewById(R.id.btnReusable);
        btnRecycle= findViewById(R.id.btnRecycle);

        //init layouts

        layouts = new int[]{
                R.layout.slider,
                R.layout.slider2

        };

        adapter = new Adapter(this, layouts);
        viewPager.setAdapter(adapter);

        Button fab = findViewById(R.id.btnReusable);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick (View fab){


                goToReusableActivity();

            }
        });

        Button fab2 = findViewById(R.id.btnRecycle);
        fab2.setOnClickListener(new View.OnClickListener() {


            @Override

            public void onClick(View fab2) {

                goToRecycleActivity();

            }



        });




    }
    private void goToReusableActivity() {

        Intent intent = new Intent(this, GoReusable.class);

        startActivity(intent);

    }
    private void  goToRecycleActivity() {

        Intent intent2 = new Intent(this, GoRecycle.class);

        startActivity(intent2);

    }


      //set menuBar

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



