package com.example.kanna.paynpark;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class BillingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);

        final EditText txtparkno=findViewById(R.id.txtparkid);
        final TextView txtvno=findViewById(R.id.textvhno);
        final TextView txtpakdate=findViewById(R.id.textparkdate);
        final TextView txtparktime=findViewById(R.id.textparktime);
        final TextView txttotalamout=findViewById(R.id.texttotalamount);
        final TextView txtprktime=findViewById(R.id.textparktime);
        final TextView txtcatgry=findViewById(R.id.textcatgry);


        Button btnCaluclate=findViewById(R.id.buttonCalculate);
        btnCaluclate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                try {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);

                    OkHttpClient client = new OkHttpClient();

                    HttpUrl.Builder urlBuilder = HttpUrl.parse("http://117.193.161.207/17lemca049/database/payment.php").newBuilder();
                    urlBuilder.addQueryParameter("park_id", String.valueOf( txtparkno.getText()));

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

                                        try {
                                            String data = response.body().string();

                                            JSONArray jsonArray = new JSONArray(data);
                                            JSONObject jsonObject;

                                            jsonObject = jsonArray.getJSONObject(0);

                                            txtvno.setText(jsonObject.getString("park_vehno"));
                                            txtcatgry.setText(jsonObject.getString("park_catgry"));
                                            txtpakdate.setText(jsonObject.getString("park_date"));
                                            txtparktime.setText(jsonObject.getString("park_mob"));
                                            txttotalamout.setText(jsonObject.getString("slote_id"));
                                            txtprktime.setText(jsonObject.getString("TIMESTAMPDIFF(SECOND, `park_date`, CURRENT_TIMESTAMP)"));



                                            // txtPName.setText(jsonObject.getString("PName"));
                                            //txtPrice.setText(jsonObject.getString("Price"));

                                        } catch (JSONException e) {
                                            //txtInfo.setText(e.getMessage());
                                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
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
