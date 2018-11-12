package com.example.kanna.paynpark;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final EditText username = (EditText) findViewById(R.id.usrnm);
        final EditText password = (EditText) findViewById(R.id.pswd);
        Button b1 = (Button) findViewById(R.id.btn);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( (username.getText().toString().equals("")) && (password.getText().toString().equals("")) ) {
                    username.setError("enter username");
                    password.setError("enter password");
                    Toast.makeText(Main2Activity.this,"Please enter username & Password",Toast.LENGTH_LONG).show();

                }else if(username.getText().toString().equals("")){
                    username.setError("enter username");
                    Toast.makeText(Main2Activity.this,"Enter username",Toast.LENGTH_LONG).show();
                }else if(password.getText().toString().equals("")){
                    password.setError("enter password");
                    Toast.makeText(Main2Activity.this,"enter password",Toast.LENGTH_LONG).show();
                }else {
                    String usrnm = username.getText().toString();
                    String pass = password.getText().toString();
                    if(usrnm.equals("admin") && pass.equals("admin")){
                        Toast.makeText(Main2Activity.this,"login successfull",Toast.LENGTH_LONG).show();
                        Intent i = new Intent(Main2Activity.this,HomePage.class);
                        startActivity(i);
                        finish();
                    }else{
                        Toast.makeText(Main2Activity.this,"Invalid Usrname or Password",Toast.LENGTH_LONG).show();
                        username.setText("");
                        password.setText("");
                        username.requestFocus();
                    }

                }

            }
        });
    }
}

