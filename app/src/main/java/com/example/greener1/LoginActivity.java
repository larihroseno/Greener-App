package com.example.greener1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //variable to work with xml file
        final EditText etUsername= (EditText) findViewById(R.id.etEmail);
        final EditText etPassword= (EditText) findViewById(R.id.etPassword);

        //variable to the register botton

        final Button bLogin =(Button) findViewById(R.id.btLogin);

        //variable to the register here

        final TextView registerLink =(TextView) findViewById(R.id.tvRegisterHere);

        //it will open up the register page
        registerLink.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //creating an intent that opens RegisterActivity
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                //tell the current activity to perfome the intent
                LoginActivity.this.startActivity(registerIntent);


            }
        });


    }
}
