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

//this class will show the nav_Bar menu with two options
// and the menuBar with 5 options

public class DashboardActivity extends AppCompatActivity {

    //Firebase authentication
    FirebaseAuth fAuth;
    FirebaseUser user;

    String userId;


    //apply Action bar to the page
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
                        //challenge page is not a fragment but an activity thats way the way you have to call it is different

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




