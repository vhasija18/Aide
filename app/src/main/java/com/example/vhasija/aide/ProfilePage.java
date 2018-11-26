package com.example.vhasija.aide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ProfilePage extends AppCompatActivity {

    TextView name, phone,email,dob;
    String name_sql,phone_sql,email_sql,dob_Sql;
    int id;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent =getIntent();
        id=intent.getExtras().getInt("id");
        setContentView(R.layout.activity_profile_page);
        name  = findViewById(R.id.profile_name);
        phone = findViewById(R.id.profile_phone);
        email = findViewById(R.id.profile_email);
        dob   = findViewById(R.id.profile_dob);
        fetchdetails(id);
    }

    public void edit_profile(View view)
    {

    }


    public void needhelp(View view)
    {

        Intent intent = new Intent(this,Maps_Login.class);
        startActivity(intent);
    }


    public void fetchdetails (Integer id)
    {

    }

}
