package com.example.greener1;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    //variables needed to implement login activity

    EditText etEmail, etPassword;
    FirebaseAuth fAuth;
    Button btLogin;
    TextView tvRegisterHere;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //variable to work with xml file
        etEmail= findViewById(R.id.etEmail);
        etPassword=findViewById(R.id.etPassword);
        fAuth=FirebaseAuth.getInstance();
        btLogin = findViewById(R.id.btLogin);
        tvRegisterHere =findViewById(R.id.tvRegisterHere);

        btLogin.setOnClickListener(new View.OnClickListener() {
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
                    return;

                }
                    //authenticate the user

                    fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //se if registration is succesful or not

                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), UserAreaActivity.class));

                            } else {
                                Toast.makeText(LoginActivity.this, "ERROR!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }
                        });

                    }


            });

        tvRegisterHere.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), RegisterActivity.class));

                }
            });

        }
    }




