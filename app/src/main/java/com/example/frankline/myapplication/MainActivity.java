package com.example.frankline.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public Button loginBtn;
    public TextView userTxt;
    public TextView passTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginBtn = (Button)findViewById(R.id.loginBtn);
        userTxt = (TextView)findViewById(R.id.username);
        passTxt = (TextView)findViewById(R.id.password);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dashIntent = new Intent (getApplicationContext(),Home.class);
                String name = userTxt.getText().toString();
                String pass = passTxt.getText().toString();
                Toast loginToast  = Toast.makeText(getApplicationContext(),"Welcome " + name,Toast.LENGTH_SHORT);
              if (name.length() == 0 || pass.length() == 0){
                  Toast error = Toast.makeText(getApplicationContext(),"Username or Password is invalide",Toast.LENGTH_SHORT);
                  error.show();
              }else {

                  loginToast.show();
                  startActivity(dashIntent);
//                  Todo: connect to firebase

              }

            }
        });
    }



}
