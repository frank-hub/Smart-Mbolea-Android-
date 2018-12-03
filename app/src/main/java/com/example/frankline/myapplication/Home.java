package com.example.frankline.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.net.InetAddress;

import io.opencensus.tags.Tag;

public class Home extends AppCompatActivity {
    public CardView profCard;
    public Button fertiliserBtn,programBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.activity_home);

//        Checking for internet connection

//        private boolean isNetworkConnected() {
//            ConnectivityManager cm =
//                    (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//
//            return  cm.getActiveNetworkInfo()!= null;
//        }

        profCard = (CardView) findViewById(R.id.profileCard);
        fertiliserBtn = (Button) findViewById(R.id.fertilisers);
        programBtn = (Button) findViewById(R.id.progBtn);

        programBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent programs = new Intent(getApplicationContext(),Programs.class);
                startActivity(programs);
            }
        });

        fertiliserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("message/details");

                // Read from the database
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String value = dataSnapshot.getValue(String.class);
                        Toast fertiliserToast = Toast.makeText(getApplicationContext(),value,Toast.LENGTH_SHORT);
                        fertiliserToast.show();
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
//                        Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });
            }
        });



    }
    public boolean isConnected() throws InterruptedException, IOException {
        final String command = "ping -c 1 google.com";
        return Runtime.getRuntime().exec(command).waitFor() == 0;
    }


}
