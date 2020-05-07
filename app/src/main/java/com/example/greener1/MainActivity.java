package com.example.greener1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





    Button fab = findViewById(R.id.btLogin);
        fab.setOnClickListener(new View.OnClickListener() {

        @Override

        public void onClick (View fab){

            goToLoginActivity();

        }
    });

    Button fab2 = findViewById(R.id.btRegister);
        fab2.setOnClickListener(new View.OnClickListener() {


        @Override

        public void onClick(View fab2) {

            goToRegisterActivity();

        }



    });




}
    private void goToLoginActivity() {

        Intent intent = new Intent(this, LoginActivity.class);

        startActivity(intent);

    }
    private void goToRegisterActivity() {

        Intent intent2 = new Intent(this, RegisterActivity.class);

        startActivity(intent2);

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);



        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()){

            case R.id.item1:
                Intent intent = new Intent(this, About.class);

                startActivity(intent);




            case R.id.item2:

                Intent intent2 = new Intent(this, Testimoniais.class);

                startActivity(intent2);

            case R.id.item3:

                Intent intent3 = new Intent(this, GreenTech.class);

                startActivity(intent3);

        }
        return true;
    }




}
