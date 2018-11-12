package com.example.kanna.paynpark;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class LocateActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate);


        final EditText txtVehno=findViewById(R.id.editTextvehno);
        Button buttonLocate=findViewById(R.id.buttonLocate);








        final RecyclerView recyclerView=findViewById(R.id.my_recycler_viewlocate);
        recyclerView.setHasFixedSize(true);
        final RecyclerView.LayoutManager reLayoutManager=new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(reLayoutManager);


        buttonLocate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocate();
            }

            private void setLocate() {

                try {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);

                    OkHttpClient client = new OkHttpClient();

                    HttpUrl.Builder urlBuilder = HttpUrl.parse("http://117.193.161.207/17lemca049/database/locateall.php").newBuilder();
                    urlBuilder.addQueryParameter("park_vehno", txtVehno.getText().toString());

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

                                            LocaterAdapter reAdapter=new LocaterAdapter(jsonArray);
                                            recyclerView.setAdapter(reAdapter);


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





                /*

                JSONArray jsonarray = new JSONArray(jsonStr);
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                    String name = jsonobject.getString("name");
                    String url = jsonobject.getString("url");
                }



                 */




    }
}
