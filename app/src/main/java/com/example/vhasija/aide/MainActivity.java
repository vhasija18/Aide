package com.example.vhasija.aide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;

public class MainActivity extends Activity {

     private Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHandler.postDelayed(new Runnable () {

            public void run() {
                Intent intent = new Intent(MainActivity.this, login.class);
                startActivity(intent);

            }
        },4000);

    }
}
