package com.example.vhasija.aide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

public class Signup extends AppCompatActivity {


    double longitude =00.00;
    double latitude  =00.00;
    CheckBox firefigther,doctor,mechanic,ambulance;
    AutoCompleteTextView gender,occupation;
    RadioButton  user,aide;
    EditText  name,email,dob,phone,username,password,confirmpassword;
    String Occupation[] = {"Doctor","Teacher", "Surgeon","Dentist","Fire Figther", "ParaMedic", "Police Officer"};
    String Gender[]= {"Male","Female"};
    String name_value,email_value,dob_value,phone_value,username_value,password_value,confirmpassword_value,
            gender_value,occupation_value;
    String occupation1,occupation2,occupation3,occupation4;
    int flag =1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        firefigther = (CheckBox) findViewById(R.id.signup_Fire_Fighter);
        doctor      = (CheckBox) findViewById(R.id.signup_Doctor);
        mechanic    = (CheckBox) findViewById(R.id.signup_Mechanic);
        ambulance   = (CheckBox) findViewById(R.id.signup_Ambulance);
        gender      = (AutoCompleteTextView) findViewById(R.id.signup_Gender);
        occupation  = (AutoCompleteTextView) findViewById(R.id.signup_Occupation);
        name        = (EditText) findViewById(R.id.signup_Name);
        email       = (EditText) findViewById(R.id.signup_email);
        phone       = (EditText) findViewById(R.id.signup_phone);
        dob         = (EditText) findViewById(R.id.signup_dob);
        username    = (EditText) findViewById(R.id.signup_userName);
        password    = (EditText) findViewById(R.id.signup_password);
        confirmpassword = (EditText) findViewById(R.id.signup_confirmpassword);
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,Gender);
        ArrayAdapter adapter1 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,Occupation);
        gender.setAdapter(adapter);
        gender.setThreshold(1);
        occupation.setAdapter(adapter1);
        occupation.setThreshold(1);
            }
    public void Maps(View view) {
        Intent intent = new Intent(Signup.this,Maps.class);
        startActivityForResult(intent,10);
    }
    public void  onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        if (checked)
        {
            switch(view.getId())
            {
                case R.id.signup_User:
                    break;
                case R.id.signup_Aide:
                    doctor.setEnabled(true);
                    mechanic.setEnabled(true);
                    firefigther.setEnabled(true);
                    ambulance.setEnabled(true);
                    flag =-1;
                    break;
            }
        }
    }


    public void onActivityResult(int requestCode, int resultCode,Intent data) {
        System.out.println("Hello");
        System.out.println(requestCode);
        System.out.println(resultCode);
            if (resultCode == RESULT_OK) {
                longitude = data.getExtras().getDouble("Longi");
                latitude  = data.getExtras().getDouble("Lati");
            }
        }

    public void last(View View)
    {
        System.out.println("MIke");
        if(flag== -1)
          {
              if(doctor.isChecked())
              {
                  occupation1 = "doctor";
              }
              if(mechanic.isChecked())
              {
                  occupation2= "Mechanic";
              }

              if(firefigther.isChecked())
              {
                  occupation3 ="Fire Figther";
              }

              if(ambulance.isChecked())
              {
                  occupation4 = "Ambulance";
              }

          }

        name_value  = name.getText().toString();
        email_value = email.getText().toString();
        phone_value = phone.getText().toString();
        dob_value   = dob.getText().toString();
        username_value = username.getText().toString();
        password_value = password.getText().toString();
        confirmpassword_value= confirmpassword.getText().toString();
        gender_value =gender.getText().toString();
        occupation_value= occupation.getText().toString();

        System.out.println(name_value);
        System.out.println(email_value);
        System.out.println(dob_value);
        System.out.println(phone_value);
        System.out.println(gender_value);
        System.out.println(occupation_value);
        System.out.println(username_value);
        System.out.println(password_value);
        System.out.println(confirmpassword_value);
        System.out.println(occupation1);
        System.out.println(occupation2);
        System.out.println(occupation3);
        System.out.println(occupation4);
        System.out.println(latitude);
        System.out.println(longitude);
        ////hello vish



    }

}
