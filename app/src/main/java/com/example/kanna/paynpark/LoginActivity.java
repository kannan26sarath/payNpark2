package com.example.kanna.paynpark;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText username = (EditText) findViewById(R.id.usrnm);
        final EditText password = (EditText) findViewById(R.id.pswd);
        Button b1 = (Button) findViewById(R.id.btn);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String uname=username.getText().toString();
                final String password1=password.getText().toString();
                //Toast.makeText(getApplicationContext(),"invalid username or password",Toast.LENGTH_LONG ).show();
                try {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);

                    OkHttpClient client = new OkHttpClient();

                    HttpUrl.Builder urlBuilder = HttpUrl.parse("http://117.193.161.207/17lemca049/database/login.php").newBuilder();
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

                                        try {
                                            String data = response.body().string();

                                            JSONArray jsonArray = new JSONArray(data);
                                            JSONObject jsonObject;
                                            for (int index = 0; index  < jsonArray.length(); index++) {
                                                //JSONObject jsonObject = jsonArray.getJSONObject(i);
                                                // Do whatever you want with jsonObject.
                                                 jsonObject = jsonArray.getJSONObject(index);



                                            String nme= jsonObject.getString("username");
                                            String psw=jsonObject.getString("password");
                                           // Toast.makeText(getApplicationContext(),nme+psw,Toast.LENGTH_LONG ).show();

                                            if(nme.equals(uname)&&psw.equals(password1)){
                                                Intent i = new Intent(LoginActivity.this,SplashActivity.class);
                                                startActivity(i);
                                                finish();
                                                break;
                                            }
                                            else{

                                                Toast.makeText(getApplicationContext(),"invalid username or password",Toast.LENGTH_SHORT ).show();
                                            }
                                        }


                                        } catch (JSONException e) {
                                            Toast.makeText(getApplicationContext(),"invalid username or password",Toast.LENGTH_SHORT ).show();
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

                /*

                if( (username.getText().toString().equals("")) && (password.getText().toString().equals("")) ) {
                    username.setError("enter username");
                    password.setError("enter password");
                    Toast.makeText(LoginActivity.this,"Please enter username & Password",Toast.LENGTH_LONG).show();

                }else if(username.getText().toString().equals("")){
                    username.setError("enter username");
                    Toast.makeText(LoginActivity.this,"Enter username",Toast.LENGTH_LONG).show();
                }else if(password.getText().toString().equals("")){
                    password.setError("enter password");
                    Toast.makeText(LoginActivity.this,"enter password",Toast.LENGTH_LONG).show();
                }else {
                    String usrnm = username.getText().toString();
                    String pass = password.getText().toString();
                    if(usrnm.equals("admin") && pass.equals("admin")){
                        Toast.makeText(LoginActivity.this,"login successfull",Toast.LENGTH_LONG).show();
                        Intent i = new Intent(LoginActivity.this,HomePage.class);
                        startActivity(i);
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this,"Invalid Usrname or Password",Toast.LENGTH_LONG).show();
                        username.setText("");
                        password.setText("");
                        username.requestFocus();
                    }

                }*/

            }
        });
    }
}

