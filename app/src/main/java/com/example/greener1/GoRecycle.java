

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

public class GoRecycle extends AppCompatActivity {
    ViewPager viewPager;
    Button btnAddEvidence, btnBack;
    int[] layouts;
    Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_recycle);


        btnAddEvidence= findViewById(R.id.btnAdd);
        btnBack= findViewById(R.id.btnBack);




        Button fab = findViewById(R.id.btnAdd);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick (View fab){


                goToEvidenceActivity();

            }
        });

        Button fab2 = findViewById(R.id.btnBack);
        fab2.setOnClickListener(new View.OnClickListener() {


            @Override

            public void onClick(View fab2) {

                goToBackActivity();

            }



        });




    }
    private void goToEvidenceActivity() {

        Intent intent = new Intent(this, AddEvidence.class);

        startActivity(intent);

    }
    private void  goToBackActivity() {

        Intent intent2 = new Intent(this, WasteActivity.class);

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



