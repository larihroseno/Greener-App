package com.example.greener1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashboardActivity extends AppCompatActivity {

    FirebaseAuth fAuth;
    FirebaseUser user;

    String userId;

    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        actionBar=getSupportActionBar();
        actionBar.setTitle("Posts");

        //init
        fAuth =FirebaseAuth.getInstance();
        fAuth = FirebaseAuth.getInstance();

        userId = fAuth.getCurrentUser().getUid();

        user = fAuth.getCurrentUser();

        //botton navigation

        BottomNavigationView navigationView = findViewById(R.id.navigation);

        navigationView.setOnNavigationItemSelectedListener(selectedListener);

        //home fragment transaction (default, on start)
        actionBar.setTitle("Home"); //change action bar title
        HomeFragment fragment2 = new HomeFragment();
        FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
        ft2.replace(R.id.content, fragment2, "");
        ft2.commit();

    }

          private BottomNavigationView.OnNavigationItemSelectedListener selectedListener=
        new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

             //handle item clicks

                switch(menuItem.getItemId()){

                    case R.id.nav_home:

                        //fragment transaction

                        actionBar.setTitle("Greener Challenges"); //change action bar title
                        HomeFragment fragment2 = new HomeFragment();
                        FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
                        ft2.replace(R.id.content, fragment2, "");
                        ft2.commit();
                        return true;

                }
                switch(menuItem.getItemId()) {

                    case R.id.nav_challenges:
                        actionBar.setTitle("Challenge yourself"); //change action bar title
                        startActivity(new Intent(getApplicationContext(),UserAreaActivity.class));
                        overridePendingTransition(0,0);


                }
                return false;
            }
        };



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
        if (id == R.id.users)
        {
            Intent logoutIntent = new Intent(this, Logout.class);

            startActivity(logoutIntent);


        }
        if (id == R.id.challenge)
        {
            Intent logoutIntent = new Intent(this, UserAreaActivity.class);

            startActivity(logoutIntent);


        }





        return super.onOptionsItemSelected(item);
    }


}




