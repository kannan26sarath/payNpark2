package com.example.kanna.paynpark;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ChangepassActivity extends AppCompatActivity {
    public String newpass = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepass);
        final EditText usname=findViewById(R.id.usname);
        final EditText opass=findViewById(R.id.opass);
        final EditText npass=findViewById(R.id.npass);
        final EditText cpass=findViewById(R.id.cpass);


        final Button okb=findViewById(R.id.okb);
        final Button cb=findViewById(R.id.cb);

        okb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);


            OkHttpClient client = new OkHttpClient();
            if(npass.getText().toString().equals(cpass.getText().toString())) {
                newpass = npass.getText().toString();
            }



            HttpUrl.Builder urlBuilder = HttpUrl.parse("http://117.193.161.207/17lemca049/database/changepassword.php").newBuilder();

            urlBuilder.addQueryParameter("username",usname.getText().toString());

            urlBuilder.addQueryParameter("password",opass.getText().toString());
            urlBuilder.addQueryParameter("npassword", newpass);
            

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

                                finish();




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
