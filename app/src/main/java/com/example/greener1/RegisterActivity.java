package com.example.greener1;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    //variables that we have to create to get information
    EditText etAge, etName, etEmail, etPassword;
    Button btRegister;

    //using firebase to register the user
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this line is showing which xml file we will work on
        setContentView(R.layout.activity_register);

        //variable to work with xml file
        etAge = findViewById(R.id.etAge);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        //variable to the register botton

        btRegister = findViewById(R.id.btRegister);

        //getting instance of the firebase
        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);





        //when use the click gedt iknfoprmation from the filed  and pass it to register redquest and execute the register request
        btRegister.setOnClickListener(new View.OnClickListener() {

            //here is going all the operation
            @Override
            public void onClick(View v) {

                // get variables to store, convert it to string because it inicially comes as an object - trim to format the data.

                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    etEmail.setError("Email is required.");
                }
                if (TextUtils.isEmpty(password)) {
                    etPassword.setError("Password is required.");
                    return;
                }
                if (password.length() < 6) {
                    etPassword.setError("Password Must be >= 6 Characteres");
                }


                progressBar.setVisibility(View.VISIBLE);

                //register the user in firebase

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //se if registration is succesful or not

                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), UserAreaActivity.class));

                        } else {
                            Toast.makeText(RegisterActivity.this, "ERROR!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }


                    }

                });


            }

        });

    }
}