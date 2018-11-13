package com.example.kanna.paynpark;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        //Button fareb = (Button) findViewById(R.id.fareupdate);
        LinearLayout llchangepassword,llfareupdate,llrevenue,lllogout;
        llchangepassword=findViewById(R.id.llchangep);
        llfareupdate=findViewById(R.id.llfareup);
        llrevenue=findViewById(R.id.llrev);
        lllogout=findViewById(R.id.lllog);


        llfareupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminActivity.this, FareupActivity.class);
                startActivity(i);

            }
        });
        llchangepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminActivity.this, ChangepassActivity.class);
                startActivity(i);

            }
        });
    }
}
