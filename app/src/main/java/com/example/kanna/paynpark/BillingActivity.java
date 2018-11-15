package com.example.kanna.paynpark;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.StrictMode;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

    public String catgry,vhno,pdate,ptime,totalprktime,prkamnt,mob,pid,totoamnt="";
    public  static final int PERMISSIONS_MULTIPLE_REQUEST = 0;

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

        Button btnPay=findViewById(R.id.buttonpay);


        ImageButton btnCaluclate=findViewById(R.id.buttonCalculate);
        btnCaluclate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {




                try {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);

                    OkHttpClient client = new OkHttpClient();

                    HttpUrl.Builder urlBuilder = HttpUrl.parse("http://117.193.161.207/17lemca049/database/payment.php").newBuilder();
                    urlBuilder.addQueryParameter("park_id", String.valueOf( txtparkno.getText()));
                    pid= txtparkno.getText().toString();

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

                                            catgry=jsonObject.getString("park_catgry");
                                            vhno=jsonObject.getString("park_vehno");
                                            pdate=jsonObject.getString("park_date");
                                            totalprktime= String.valueOf(Integer.valueOf(jsonObject.getString("TIMESTAMPDIFF(SECOND, `park_date`, CURRENT_TIMESTAMP)")));
                                            mob=jsonObject.getString("park_mob");



                                            /*txtvno.setText(jsonObject.getString("park_vehno"));
                                            txtcatgry.setText(jsonObject.getString("park_catgry"));
                                            txtpakdate.setText(jsonObject.getString("park_date"));
                                            txtparktime.setText(jsonObject.getString("park_mob"));
                                            txttotalamout.setText(jsonObject.getString("slote_id"));
                                            txtprktime.setText(jsonObject.getString("TIMESTAMPDIFF(SECOND, `park_date`, CURRENT_TIMESTAMP)"));
*/


                                            // txtPName.setText(jsonObject.getString("PName"));
                                            //txtPrice.setText(jsonObject.getString("Price"));
                                            String Catgry=jsonObject.getString("park_catgry");
                                            getAmount(Catgry);
                                           // Log.d("Amount",Amount[0]);
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

            public void getAmount(final String catgry) {


                try {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);

                    OkHttpClient client = new OkHttpClient();

                    HttpUrl.Builder urlBuilder = HttpUrl.parse("http://117.193.161.207/17lemca049/database/gerfair.php").newBuilder();
                    urlBuilder.addQueryParameter("park_cat",catgry);

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
                                            String amnt ="";
                                            amnt = jsonObject.getString("amount");
                                            // txtPName.setText(jsonObject.getString("PName"));
                                            //txtPrice.setText(jsonObject.getString("Price"));

                                            txtcatgry.setText(catgry);
                                            txtvno.setText(vhno);
                                            txtpakdate.setText(pdate);
                                            int hours=Integer.valueOf(totalprktime) /3600;
                                            txtparktime.setText(hours+  " hour");
                                            int day=Integer.valueOf(totalprktime)/86400;
                                            totoamnt=String.valueOf(Integer.valueOf(amnt)*day);
                                            txttotalamout.setText(String.valueOf(totoamnt));


                                           // txttotalamout.setText(amnt);








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





        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                        checkPermission();

                        UpdatePark();









            }

            private void checkPermission() {

                if (ContextCompat.checkSelfPermission(BillingActivity.this,
                        Manifest.permission.READ_PHONE_STATE) + ContextCompat
                        .checkSelfPermission(BillingActivity.this,
                                Manifest.permission.SEND_SMS)
                        != PackageManager.PERMISSION_GRANTED) {

                    if (ActivityCompat.shouldShowRequestPermissionRationale
                            (BillingActivity.this, Manifest.permission.SEND_SMS) ||
                            ActivityCompat.shouldShowRequestPermissionRationale
                                    (BillingActivity.this, Manifest.permission.READ_PHONE_STATE)) {
                        Snackbar.make(BillingActivity.this.findViewById(android.R.id.content),
                                "Please Grant Permissions to Send SMS",
                                Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                            requestPermissions(
                                                    new String[]{Manifest.permission
                                                            .SEND_SMS, Manifest.permission.READ_PHONE_STATE},
                                                    PERMISSIONS_MULTIPLE_REQUEST);
                                        }
                                    }
                                }).show();
                    } else {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            requestPermissions(
                                    new String[]{Manifest.permission
                                            .SEND_SMS, Manifest.permission.READ_PHONE_STATE},
                                    PERMISSIONS_MULTIPLE_REQUEST);
                        }
                    }
                } else {
                    // write your logic code if permission already granted

                   sendMessage("9072948997","hai this is text");
                }

            }
        });



    }

    private void UpdatePark() {



        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            OkHttpClient client = new OkHttpClient();

            HttpUrl.Builder urlBuilder = HttpUrl.parse("http://117.193.161.207/17lemca049/database/updateparker.php").newBuilder();

            urlBuilder.addQueryParameter("park_id",pid);

            urlBuilder.addQueryParameter("amount",totoamnt);

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

    private void sendMessage(String phoneNo, String msg) {
        try {

            String MSG="Hi Customer,\n" +
                    "Thank you for parking with us ❤\n" +
                    "Parking ID : payNpark@21\n" +
                    "Slot No :  12\n" +
                    "This transaction cannot be cancelled \n";

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, MSG, null, null);
            Toast.makeText(getApplicationContext(), "Message Sent",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),ex.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case PERMISSIONS_MULTIPLE_REQUEST:
                if (grantResults.length > 0) {
                    boolean cameraPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean readExternalFile = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if(cameraPermission && readExternalFile)
                    {
                        // write your logic here

                        sendMessage("9072948997","hai this is text");


                    } else {
                        Snackbar.make(BillingActivity.this.findViewById(android.R.id.content),
                                "Please Grant Permissions to Send SMS",
                                Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                            requestPermissions(
                                                    new String[]{Manifest.permission
                                                            .SEND_SMS, Manifest.permission.READ_PHONE_STATE},
                                                    PERMISSIONS_MULTIPLE_REQUEST);
                                        }
                                    }
                                }).show();
                    }
                }
                break;
        }
    }


}
