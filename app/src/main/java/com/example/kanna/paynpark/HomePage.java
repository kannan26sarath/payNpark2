package com.example.kanna.paynpark;

import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_home_page);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        LinearLayout llparka,llsearcha,lllocatea,llbillinga,lladmina,llappinfoa ;
        llparka=findViewById(R.id.llpark);
        llsearcha=findViewById(R.id.llsearch);
        lllocatea=findViewById(R.id.lllocate);
        llbillinga=findViewById(R.id.llbill);
        lladmina=findViewById(R.id.lladmin);
        llappinfoa=findViewById(R.id.llapp);
        TextView textViewhomepage=findViewById(R.id.homepageheading);

       // textViewhomepage.startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.bounce_interpolator));
        //Button b2 = (Button) findViewById(R.id.park);
        //Button b3 = (Button) findViewById(R.id.search);
        //Button b4 = (Button) findViewById(R.id.locate);
        //Button b5 = (Button) findViewById(R.id.billing);
       // Button b6 = (Button) findViewById(R.id.admin);
       // Button b7 = (Button) findViewById(R.id.about);
        llparka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, SlotActivity.class);
                startActivity(i);

            }
        });
        llsearcha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, SearchActivity.class);
                startActivity(i);

            }
        });
        lllocatea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, LocateActivity.class);
                startActivity(i);

            }
        });
        llbillinga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, BillingActivity.class);
                startActivity(i);

            }
        });
        lladmina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, AdminActivity.class);
                startActivity(i);

            }
        });
        llappinfoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, AboutActivity.class);
                startActivity(i);

            }
        });


    }
}
