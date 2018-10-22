package com.example.kanna.paynpark;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class ParkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park);
        TextView txttime, txtDate;
        final EditText etxtVid,etxtMobileNum;
        Button btnPark,btnReset;
        txttime = findViewById(R.id.txttime);
        txtDate = findViewById(R.id.txtdate);
        btnPark=findViewById(R.id.btnpark);
        btnReset=findViewById(R.id.btnRest);

        etxtVid=findViewById(R.id.etxtVid);
        etxtMobileNum=findViewById(R.id.etxtMob);


        Spinner dropdown = findViewById(R.id.spinner1);
        String[] items = new String[]{"2 wheeler", "4 wheeler"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);


        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            df = new SimpleDateFormat("dd-MMM-yyyy");
        }
        String formattedDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            formattedDate = df.format(c);
            txtDate.setText(formattedDate.toString());
        }
        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        String delegate = "hh:mm aaa";
        String times = (String) DateFormat.format(delegate, Calendar.getInstance().getTime());
        // txttime.setText(today.hour+":"+today.minute+""+""+today.timezone);
        txttime.setText(times);


        btnPark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mob= String.valueOf(etxtMobileNum.getText());
                String vid= String.valueOf(etxtVid.getText());


            }
        });

    }
}