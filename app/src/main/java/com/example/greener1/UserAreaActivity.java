package com.example.greener1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);


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

                if( id == R.id.item1)
                {

                    Intent homeIntent = new Intent(this, UserAreaActivity.class);
                    startActivity(homeIntent);
                }
                if( id == R.id.item2)
                {

                    Intent settingsIntent= new Intent(this, SettingsUser.class);

                    startActivity(settingsIntent);

                }
                if( id == R.id.item3)
                {
                    Intent challengeIntent = new Intent(this, ChallengesHistory.class);

                    startActivity(challengeIntent);


                }
                if( id == R.id.item4)
                {
                    Intent logoutIntent = new Intent(this, Logout.class);

                    startActivity(logoutIntent);


                }




                return super.onOptionsItemSelected(item);
            }



        }
