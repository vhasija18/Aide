package com.example.vhasija.aide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ProfilePage extends AppCompatActivity {

    EditText  name, phone,email,dob;
    Button edit,change,location,save;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        name  = findViewById(R.id.profile_name);
        phone = findViewById(R.id.profile_phone);
        email = findViewById(R.id.profile_email);
        dob   = findViewById(R.id.profile_dob);
        name.setEnabled(false);
        phone.setEnabled(false);
        email.setEnabled(false);
        dob.setEnabled(false);
        edit = findViewById(R.id.profile_edit);
        change = findViewById(R.id.profile_changepass);
        location = findViewById(R.id.profile_tageurlocation);
        save = findViewById(R.id.profile_save);
        save.setVisibility(View.INVISIBLE);
        change.setVisibility(View.INVISIBLE);
        location.setVisibility(View.INVISIBLE);

    }

    public void save_profile(View view)
    {


    }

    public void edit_profile(View view)
    {
       name.setEnabled(true);
       phone.setEnabled(true);
       email.setEnabled(true);
       save.setVisibility(View.VISIBLE);
       change.setVisibility(View.VISIBLE);
       location.setVisibility(View.VISIBLE);
    }

    public void tagurlocation_profile(View view)
    {

    }


    public void changepass_profile(View view)
    {

    }

    public void needhelp(View view)
    {

        Intent intent = new Intent(this,Maps_Login.class);
        startActivity(intent);
    }

}
