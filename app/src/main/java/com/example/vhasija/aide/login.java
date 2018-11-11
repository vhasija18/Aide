package com.example.vhasija.aide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

    public void contactUs(View view){
        System.out.println("email");
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("application/octet-stream");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"aide_support@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "Contact us");
        i.putExtra(Intent.EXTRA_TEXT   , "");
        try {
            startActivity(Intent.createChooser(i, "Mail your query..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(login.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

}
