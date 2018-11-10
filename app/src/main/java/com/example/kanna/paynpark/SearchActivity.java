package com.example.kanna.paynpark;

import android.app.DatePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.kanna.paynpark.Adapter.LocaterAdapter;
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

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        final Calendar myCalendar = Calendar.getInstance();
        final EditText editTex=findViewById(R.id.editTextsearch);
        Button buttonSearch=findViewById(R.id.buttonSearch);
        

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    sdf = new SimpleDateFormat(myFormat, Locale.US);
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    editTex.setText(sdf.format(myCalendar.getTime()));
                }

            }

        };


        editTex.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(SearchActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        
        
        
        
        
        

        final RecyclerView recyclerView=findViewById(R.id.recyclerViewSearch);
        recyclerView.setHasFixedSize(true);
        final RecyclerView.LayoutManager reLayoutManager=new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(reLayoutManager);






        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editTex.getText().toString().equals("")){
                    
                    setSearch();
                    
                }
            }

            private void setSearch() {


                    try {
                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                        StrictMode.setThreadPolicy(policy);

                        OkHttpClient client = new OkHttpClient();

                        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://117.193.161.207/17lemca049/database/locateall.php").newBuilder();
                        //urlBuilder.addQueryParameter("PID", txtPID.getText().toString());

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
                                    /* arr=new String[jsonArray.length()];
                                    for(int i=0; i<arr.length; i++) {
                                        arr[i]=jsonArray.optString(i);
                                    }*/

                                                SearchAdapter searchAdapter=new SearchAdapter(jsonArray);
                                                recyclerView.setAdapter(searchAdapter);


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
