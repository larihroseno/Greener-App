package com.example.greener1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Testimoniais extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testimoniais);






    }
    private void goToMainActivity() {

        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);



        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();

        if( id == R.id.item1)
        {

            Intent aboutIntent = new Intent(this, About.class);
            startActivity(aboutIntent);
        }
        if( id == R.id.item2)
        {

            Intent greenIntent= new Intent(this, GreenTech.class);

            startActivity(greenIntent);

        }
        if( id == R.id.item3)
        {
            Intent testimonialIntent = new Intent(this, Testimoniais.class);

            startActivity(testimonialIntent);


        }
        if( id == R.id.item4)
        {
            Intent intent = new Intent(this, MainActivity.class);

            startActivity(intent);


        }




        return super.onOptionsItemSelected(item);
    }



}
