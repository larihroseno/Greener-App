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
    ViewPager viewPager;
    Button btnWalk, btnSave;
    int[] layouts;
    Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_energy);

        viewPager = findViewById(R.id.pager);
        btnWalk= findViewById(R.id.btnWalk);
        btnSave= findViewById(R.id.btnSave);

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
    private void goToWalkActivity() {

        Intent intent = new Intent(this, GoWalk.class);

        startActivity(intent);

    }
    private void  goToSaveActivity() {

        Intent intent2 = new Intent(this, GoSave.class);

        startActivity(intent2);

    }



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



