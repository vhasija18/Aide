package com.example.vhasija.aide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class login extends AppCompatActivity {


    EditText name,password;
    String  name_string, password_string;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name = (EditText) findViewById(R.id.Name);
        password = (EditText) findViewById(R.id.Password);



    }


    public void Maps_login(View view)
    {
        name_string = name.getText().toString();
        password_string = password.getText().toString();

        Intent intent = new Intent(login.this,Maps_Login.class);
        startActivity(intent);

    }
}
