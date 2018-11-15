package com.example.kanna.paynpark;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Handler;
import android.os.StrictMode;
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
import android.widget.Toast;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.net.sip.SipErrorCode.TIME_OUT;

public class ParkActivity extends AppCompatActivity {
    private static int TIME_OUTs = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park);
        final TextView txttime, txtDate;
        final EditText etxtVid,etxtMobileNum;
        final Button btnPark,btnReset;
        txttime = findViewById(R.id.txttime);
        txtDate = findViewById(R.id.txtdate);
        btnPark=findViewById(R.id.btnpark);
        btnReset=findViewById(R.id.btnRest);
        String formattedDate = null;
        etxtVid=findViewById(R.id.etxtVid);
        etxtMobileNum=findViewById(R.id.etxtMob);
        TextView txtSloteno=findViewById(R.id.sloteno);


        Button btnOpenReader=findViewById(R.id.btnOpenreader);


        btnOpenReader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(ParkActivity.this,NumberPlateReaderActivity.class));

            }
        });







        final Spinner dropdown = findViewById(R.id.spinner1);
        String[] items = new String[]{"2 wheeler", "4 wheeler"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);


        Bundle extras = getIntent().getExtras();
        final String SloteNO=extras.getString("SLOTE_NO");
        txtSloteno.setText(SloteNO);


        final Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            df = new SimpleDateFormat("dd-MMM-yyyy");
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            formattedDate = df.format(c);
            txtDate.setText(formattedDate.toString());
        }
        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        String delegate = "hh:mm aaa";
        final String times = (String) DateFormat.format(delegate, Calendar.getInstance().getTime());
        // txttime.setText(today.hour+":"+today.minute+""+""+today.timezone);
        txttime.setText(times);
        final String finalFormattedDate = formattedDate;

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etxtVid.setText("");
                txtDate.setText("");
                txttime.setText("");
                etxtMobileNum.setText("");
                txtDate.setText(finalFormattedDate.toString());
                txttime.setText(times);
            }

        });



        btnPark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mob= String.valueOf(etxtMobileNum.getText());
                String vid= String.valueOf(etxtVid.getText());
                String catgry = dropdown.getSelectedItem().toString();
                String Sloteno=SloteNO;



                try {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);

                    OkHttpClient client = new OkHttpClient();

                    HttpUrl.Builder urlBuilder = HttpUrl.parse("http://117.193.161.207/17lemca049/database/create.php").newBuilder();
                    urlBuilder.addQueryParameter("park_vehno",vid);
                    urlBuilder.addQueryParameter("park_catgry",catgry);
                    urlBuilder.addQueryParameter("park_mob",mob);
                    urlBuilder.addQueryParameter("slote_id",Sloteno);

                    String url = urlBuilder.build().toString();

                    Request request = new Request.Builder()
                            .url(url)
                            .build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, final Response response) throws IOException {

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    try {
                                       // txtInfo.setText(response.body().string());
                                        Toast.makeText(getApplicationContext(),response.body().string(),Toast.LENGTH_LONG).show();
                                        etxtVid.setText("");
                                        txtDate.setText("");
                                        txttime.setText("");
                                        etxtMobileNum.setText("");
                                        txtDate.setText(finalFormattedDate.toString());
                                        txttime.setText(times);


                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                Intent i = new Intent(getApplicationContext(), HomePage.class);
                                                startActivity(i);
                                                finish();
                                            }
                                        }, TIME_OUTs);

                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                        }

                        ;
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });

    }
}