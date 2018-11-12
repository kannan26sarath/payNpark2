package com.example.kanna.paynpark;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Button b2 = (Button) findViewById(R.id.park);
        Button b3 = (Button) findViewById(R.id.search);
        Button b4 = (Button) findViewById(R.id.locate);
        Button b5 = (Button) findViewById(R.id.billing);
        Button b6 = (Button) findViewById(R.id.admin);
        Button b7 = (Button) findViewById(R.id.about);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, ParkActivity.class);
                // i.putExtra("ans", res);
                startActivity(i);
                finish();

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, SearchActivity.class);
                // i.putExtra("ans", res);
                startActivity(i);
                finish();

            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, LocateActivity.class);
                // i.putExtra("ans", res);
                startActivity(i);
                finish();

            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, BillingActivity.class);
                // i.putExtra("ans", res);
                startActivity(i);
                finish();

            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, AdminActivity.class);
                // i.putExtra("ans", res);
                startActivity(i);
                finish();

            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, AboutActivity.class);
                // i.putExtra("ans", res);
                startActivity(i);
                finish();

            }
        });


    }
}
