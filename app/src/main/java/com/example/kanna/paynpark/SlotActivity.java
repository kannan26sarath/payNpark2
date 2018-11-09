package com.example.kanna.paynpark;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SlotActivity extends AppCompatActivity implements View.OnClickListener {
    ViewGroup layout;

    String seats =  "__/"
            + "AAAAAAAA/"
            + "AAAAAAAA/"
            + "__/"
            + "AAAAAAAA/"
            + "AAAAAAAA/"
            + "__/"
            + "AAAAAAAA/"
            + "AAAAAAAA/"
            + "__/"
            + "AAAAAAAA/"
            + "AAAAAAAA/"
            + "__/"
            + "AAAAAAAA/"
            + "AAAAAAAA/"
            + "__/"
            + "AAAAAAAA/"
            + "AAAAAAAA/"
            + "__/"
            + "AAAAAAAA/"
            + "AAAAAAAA/"
            + "__/";

    List<TextView> seatViewList = new ArrayList<>();
    int seatSize = 100;
    int seatGaping = 10;

    int STATUS_AVAILABLE = 1;
    int STATUS_BOOKED = 2;
    //int STATUS_RESERVED = 3;
    String selectedIds = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot);
        //JSONArray jsondatas = new JSONArray();
        //GetSlotes getSlote=new GetSlotes(this);
       // jsondatas= getSlote.getSlotes();
        layout = findViewById(R.id.layoutSeat);

        seats = "/" + seats;

        LinearLayout layoutSeat = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutSeat.setOrientation(LinearLayout.VERTICAL);
        layoutSeat.setLayoutParams(params);
        layoutSeat.setPadding(8 * seatGaping, 8 * seatGaping, 8 * seatGaping, 8 * seatGaping);
        layout.addView(layoutSeat);

        LinearLayout layout = null;

        int count = 0;



        for (int index = 0; index < seats.length(); index++) {
            if (seats.charAt(index) == '/') {
                layout = new LinearLayout(this);
                layout.setOrientation(LinearLayout.HORIZONTAL);
                layoutSeat.addView(layout);
            } else if (seats.charAt(index) == 'U') {
                count++;
                TextView view = new TextView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                view.setLayoutParams(layoutParams);
                view.setPadding(0, 0, 0, 2 * seatGaping);
                view.setId(count);
                view.setGravity(Gravity.CENTER);
                view.setBackgroundResource(R.drawable.red);
                view.setTextColor(Color.WHITE);
                view.setTag(STATUS_BOOKED);
                view.setText(count + "");
                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
                layout.addView(view);
                seatViewList.add(view);
                view.setOnClickListener(this);
            } else if (seats.charAt(index) == 'A') {
                count++;
                TextView view = new TextView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                view.setLayoutParams(layoutParams);
                view.setPadding(0, 0, 0, 2 * seatGaping);
                view.setId(count);
                view.setGravity(Gravity.CENTER);
                view.setBackgroundResource(R.drawable.green);
                view.setText(count + "");
                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
                view.setTextColor(Color.BLACK);
                view.setTag(STATUS_AVAILABLE);
                layout.addView(view);
                seatViewList.add(view);
                view.setOnClickListener(this);
            } /*else if (seats.charAt(index) == 'R') {
                count++;
                TextView view = new TextView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                view.setLayoutParams(layoutParams);
                view.setPadding(0, 0, 0, 2 * seatGaping);
                view.setId(count);
                view.setGravity(Gravity.CENTER);
                view.setBackgroundResource(R.drawable.ic_seats_reserved);
                view.setText(count + "");
                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
                view.setTextColor(Color.WHITE);
                view.setTag(STATUS_RESERVED);
                layout.addView(view);
                seatViewList.add(view);
                view.setOnClickListener(this);
            }*/ else if (seats.charAt(index) == '_') {
                TextView view = new TextView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                view.setLayoutParams(layoutParams);
                view.setBackgroundColor(Color.TRANSPARENT);
                view.setText("");
                layout.addView(view);
            }
        }
























        try {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            OkHttpClient client = new OkHttpClient();

            HttpUrl.Builder urlBuilder = HttpUrl.parse("http://117.193.161.207/17lemca049/database/getslotes.php").newBuilder();
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
                                String[] arr;
                                JSONArray jsonArray;
                                try {
                                    String data = response.body().string();
                                    jsonArray = new JSONArray(data);



                                   // Log.d("MyseatsCountLocal",""+ myjson.length());
                                    //JSONObject jsonObject;
                                   /*  arr=new String[jsonArray.length()];
                                    for(int i=0; i<arr.length; i++) {
                                        arr[i]=jsonArray.optString(i);
                                    }*/
                                    setSeat(jsonArray);
                                    //LocaterAdapter reAdapter=new LocaterAdapter(jsonArray);
                                    //recyclerView.setAdapter(reAdapter);


                                } catch (JSONException e) {
                                    Toast.makeText(getApplicationContext(), "Error in Getting Data", Toast.LENGTH_SHORT).show();
                                }


                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }

                private void setSeat(JSONArray jsonArray) {


                       // try {
                            for(int test=0; test<jsonArray.length(); test++){

                            Log.d("Arraylistcount",""+ seatViewList.get(test).getText());
                                try {
                                    JSONObject jsonobject = jsonArray.getJSONObject(test);
                                    //if(String.valueOf(jsonobject.getString("slote_id")) == String.valueOf(seatViewList.get(test).getText()) ){

                                        seatViewList.get(Integer.parseInt(jsonobject.getString("slote_id"))-1).setBackgroundResource(R.drawable.red);
                                        seatViewList.get(Integer.parseInt(jsonobject.getString("slote_id"))-1).setTag(STATUS_BOOKED);
                                    //}
                                } catch (JSONException e) {
                                    Toast.makeText(getApplicationContext(),"fsdfs" + e,Toast.LENGTH_LONG).show();
                                }
                                // Log.d("Myseats",jsonobject.getString("slote_id"));




                            }


                        //} catch (JSONException e) {
                        //    e.printStackTrace();
                        //}



                }

                ;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }








































    }

    @Override
    public void onClick(View view) {
        if ((int) view.getTag() == STATUS_AVAILABLE) {
            if (selectedIds.contains(view.getId() + ",")) {
                selectedIds = selectedIds.replace(+view.getId() + ",", "");
                view.setBackgroundResource(R.drawable.green);
            } else {
                selectedIds = selectedIds + view.getId() + ",";
                view.setBackgroundResource(R.drawable.red);
                String name="U";
                String id= String.valueOf(view.getId());
                int idd= Integer.parseInt(id)+1;
                //seats=seats.substring(0, view.getId())+name+seats.substring(idd);
                //layout.addView(view);
                view.setTag(STATUS_BOOKED);
                Intent intent=new Intent(getApplicationContext(),ParkActivity.class);
               intent.putExtra("SLOTE_NO",""+view.getId());
               startActivity(intent);


            }
        } else if ((int) view.getTag() == STATUS_BOOKED) {
            Toast.makeText(this, "Slot " + view.getId() + " Succussfully Allotted", Toast.LENGTH_SHORT).show();
        } else if ((int) view.getTag() == STATUS_AVAILABLE) {
            Toast.makeText(this, "Slot " + view.getId() + " Already Parked", Toast.LENGTH_SHORT).show();
        }
    }
}