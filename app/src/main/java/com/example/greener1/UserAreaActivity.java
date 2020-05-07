package com.example.greener1;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        //links to the text field
        final EditText etUsername= (EditText) findViewById(R.id.etEmail);
        final EditText etAge= (EditText) findViewById(R.id.etAge);
        final TextView welcomeMessage = (TextView)findViewById(R.id.tvWelcomeMsg);




    }
}
