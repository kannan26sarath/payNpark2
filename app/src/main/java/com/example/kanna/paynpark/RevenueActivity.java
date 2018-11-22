package com.example.kanna.paynpark;

import android.app.DatePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kanna.paynpark.Adapter.SearchAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RevenueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revenue);

        final Calendar myCalendarrevenue = Calendar.getInstance();
        final EditText editTex=findViewById(R.id.editTextrevenuesearch);
        Button buttonSearch=findViewById(R.id.buttonrevenueSearch);




        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendarrevenue.set(Calendar.YEAR, year);
                myCalendarrevenue.set(Calendar.MONTH, monthOfYear);
                myCalendarrevenue.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "YYYY/MM/dd"; //In which you need put here
                SimpleDateFormat sdf = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    sdf = new SimpleDateFormat(myFormat, Locale.US);
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    editTex.setText(sdf.format(myCalendarrevenue.getTime()));
                }

            }

        };



        editTex.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(RevenueActivity.this, date, myCalendarrevenue
                        .get(Calendar.YEAR), myCalendarrevenue.get(Calendar.MONTH),
                        myCalendarrevenue.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editTex.getText().toString().equals("")){

                    setSearch();

                }
                else {

                    Toast.makeText(getApplicationContext(),"Please Select a date",Toast.LENGTH_SHORT).show();
                }
            }
        private void setSearch() {


            try {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                OkHttpClient client = new OkHttpClient();

                HttpUrl.Builder urlBuilder = HttpUrl.parse("http://117.193.161.207/17lemca049/database/revenue.php").newBuilder();
                //urlBuilder.addQueryParameter("PID", txtPID.getText().toString());
                urlBuilder.addQueryParameter("returndate", editTex.getText().toString());
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
                                    //txtInfo.setText(response.body().string());
                                    String[]arr;

                                    try {
                                        String data = response.body().string();
                                        JSONArray jsonArray = new JSONArray(data);
                                        JSONObject jsonObject;
                                        jsonObject = jsonArray.getJSONObject(0);

                                        if (jsonArray.length()==0){
                                            Toast.makeText(getApplicationContext(),"No one is parked in this date",Toast.LENGTH_SHORT).show();


                                        }
                                        else {
                                            TextView textView=findViewById(R.id.txxtrevenuetotal);




                                            textView.setText("RS."+jsonObject.getString("sum(`amount`)"));
                                            Toast.makeText(getApplicationContext(),jsonObject.getString("sum(`amount`)"),Toast.LENGTH_SHORT ).show();
                                            ///SearchAdapter searchAdapter = new SearchAdapter(jsonArray);
                                            ///recyclerView.setAdapter(searchAdapter);

                                        }

                                    } catch (JSONException e) {
                                        Toast.makeText(getApplicationContext(),"Error in Getting Data",Toast.LENGTH_SHORT ).show();
                                    }


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
