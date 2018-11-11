package com.example.vhasija.aide;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;

public class MainActivity extends Activity {

    View signup;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void signupform (View view)

    {
        Intent intent = new Intent(this,Signup.class);
        startActivity(intent);
    }

    public void login(View view)
    {

        Intent intent = new Intent(this,login.class);
        startActivity(intent);

    }
}
