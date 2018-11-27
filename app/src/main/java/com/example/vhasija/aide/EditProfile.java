package com.example.vhasija.aide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class EditProfile extends AppCompatActivity {

    EditText first_name,last_name,email,phone,dob;
    Spinner help;
    String firstname_value,lastname_value,email_value,dob_value;
    Integer id_value,help_value;
    Long phone_Value;
    String help_input[]={"Select a service","Ambulance","Doctor","Plumber","Electrician","Mechanic"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        first_name = findViewById(R.id.editprofile_firstname);
        last_name  = findViewById(R.id.editprofile_lastname);
        email      = findViewById(R.id.editprofile_email);
        phone      = findViewById(R.id.editprofile_phone);
        dob        = findViewById(R.id.editprofile_dob);
        help       = findViewById(R.id.editprofile_help);
        Intent intent = getIntent();
        firstname_value  = intent.getExtras().getString("firstname");
        lastname_value   = intent.getExtras().getString("lastname");
        email_value      = intent.getExtras().getString("email");
        dob_value        = intent.getExtras().getString("dob");
        id_value         = intent.getExtras().getInt("id");
        help_value       = intent.getExtras().getInt("occupation");
        phone_Value      = intent.getExtras().getLong("phone");
        first_name.setText(firstname_value);
        last_name.setText(lastname_value);
        email.setText(email_value);
        dob.setText(dob_value);
        phone.setText(phone_Value.toString());
        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,help_input);
        help.setAdapter(adapter);
        help.setSelection(help_value);



    }

    public void changepin(View view)
    {
        Intent intent = new Intent(this,change_password.class);
        intent.putExtra("id",id_value);
        startActivity(intent);
    }

    public void  tagnewloc(View view)
    {
        Intent intent = new Intent(this, Tag_new_loaction.class);
        intent.putExtra("id",id_value);
        startActivity(intent);
    }
}