package com.example.kanna.paynpark;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.kanna.paynpark.Adapter.LocaterAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetSlotes {

    private Activity activity;
    final static JSONArray myjson = new JSONArray();
    public GetSlotes(Activity activity) {
        this.activity = activity;

    }

    public JSONArray getSlotes() {



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

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                //txtInfo.setText(response.body().string());
                                String[] arr;
                                JSONArray jsonArray = new JSONArray();
                                try {
                                    String data = response.body().string();
                                     jsonArray = new JSONArray(data);
                                     // myjson =jsonArray;

                                    Log.d("MyseatsCountLocal",""+ myjson.length());
                                    JSONObject jsonObject;
                                    /* arr=new String[jsonArray.length()];
                                    for(int i=0; i<arr.length; i++) {
                                        arr[i]=jsonArray.optString(i);
                                    }*/

                                    //LocaterAdapter reAdapter=new LocaterAdapter(jsonArray);
                                    //recyclerView.setAdapter(reAdapter);


                                } catch (JSONException e) {
                                    Toast.makeText(activity.getApplicationContext(), "Error in Getting Data", Toast.LENGTH_SHORT).show();
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

        Log.d("MyseatsCounts",""+ myjson.length());

        return myjson;
    }
}




