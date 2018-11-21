package com.example.vhasija.aide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wampSync.AsyncResponse;
import com.example.wampSync.PostResponseAsyncTask;

public class Signup extends AppCompatActivity implements AsyncResponse {


    public static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    double longitude =-00.00;
    double latitude  =-00.00;
    RadioButton  user,aide;
    EditText  name,email,dob,phone,pin,confirmpin;
    String Occupation[] = {"How can you help","Doctor","Teacher", "Surgeon","Dentist","Fire Figther", "ParaMedic", "Police Officer"};
    String Gender[]= {"Gender","Male","Female"};
    String name_value,email_value,dob_value,phone_value,pin_value,confirmpin_value,
            gender_value,occupation_value;
    int aide_value ,user_value;
    int name_flag,email_flag,dob_flag,phone_flag,pin_flag,confirmpin_flag;
    Spinner occupation,gender;
    Button taglocation;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        occupation  = (Spinner) findViewById(R.id.signup_help);
        gender      = (Spinner) findViewById(R.id.signup_gender);
        name        = (EditText) findViewById(R.id.signup_Name);
        email       = (EditText) findViewById(R.id.signup_email);
        phone       = (EditText) findViewById(R.id.signup_phone);
        dob         = (EditText) findViewById(R.id.signup_dob);
        pin    = (EditText) findViewById(R.id.signup_pin);
        confirmpin = (EditText) findViewById(R.id.signup_confirmpin);
        taglocation = findViewById(R.id.button3);
        aide = findViewById(R.id.signup_Aide);
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,Gender);
        ArrayAdapter adapter1 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,Occupation);
        gender.setAdapter(adapter);
        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender_value= parent.getItemAtPosition(position).toString();
                System.out.println(gender_value);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        occupation.setAdapter(adapter1);
        occupation.setEnabled(false);

            }
    public void tagurlocation(View view) {
        Intent intent = new Intent(Signup.this,Maps.class);
        startActivityForResult(intent,10);
    }
    public void  onRadioButtonClicked(View view) {
        user_value =0;
        aide_value =0;
        boolean checked = ((RadioButton) view).isChecked();

        if (checked)
        {
            switch(view.getId())
            {
                case R.id.signup_User:
                    user_value = 1;
                    occupation.setVisibility(View.GONE);
                    break;
                case R.id.signup_Aide:
                    aide_value = 1;
                    occupation.setVisibility(View.VISIBLE);
                    occupation.setEnabled(true);
                    occupation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            occupation_value= parent.getItemAtPosition(position).toString();
                            System.out.println(occupation_value);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

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

    public void signup(View view)
    {
        sendSmsEmail();
        /*System.out.println("MIke");
        if(flag== -1)
          {

          }


        name_flag=0;
        phone_flag=0;
        email_flag=0;
        dob_flag=0;
        pin_flag=0;
        confirmpin_flag=0;
        name_value  = name.getText().toString();
        email_value = email.getText().toString();
        phone_value = phone.getText().toString();
        dob_value   = dob.getText().toString();
        pin_value = pin.getText().toString();
        confirmpin_value= confirmpin.getText().toString();

        if(name_value.matches(""))
        {
            Toast.makeText(this,"All fields are required", Toast.LENGTH_SHORT).show();
            name.setError("Name is missing");
        }
        if(email_value.matches(""))
        {
            Toast.makeText(this,"All fields are required", Toast.LENGTH_SHORT).show();
            email.setError("Email is missing");
        }
        if(phone_value.matches(""))
        {
            Toast.makeText(this,"All fields are required", Toast.LENGTH_SHORT).show();
            phone.setError("Phone number is missing");
        }

        if(dob_value.matches(""))
        {
            Toast.makeText(this,"All fields are required",Toast.LENGTH_SHORT).show();
            dob.setError("Date of birth is missing");
        }
        if(pin_value.matches(""))
        {
            Toast.makeText(this,"All fields are required",Toast.LENGTH_SHORT).show();
            pin.setError("Please enter your pin");

        }

        if (confirmpin_value.matches(""))
        {
            Toast.makeText(this,"All fields are required",Toast.LENGTH_SHORT).show();
            confirmpin.setError("Confirm your pin");

        }

        if (!(confirmpin_value.matches(pin_value)))
        {
            Toast.makeText(this,"Pin and Confirm pin doesn't match",Toast.LENGTH_SHORT).show();
            confirmpin.setError("Doesnot Match");
        }

        if(gender_value.matches("Gender"))
        {
            Toast.makeText(this,"All fields are required",Toast.LENGTH_SHORT).show();
            ((TextView)gender.getSelectedView()).setError("Please select gender");
        }

        if(aide_value ==1)
        {

            if(occupation_value.matches("How can you help"))
            {
                Toast.makeText(this,"All fields are required",Toast.LENGTH_SHORT).show();
                ((TextView)occupation.getSelectedView()).setError("Please select any option");
            }
        }

        if(latitude==-00.00 && longitude==-00.00)
        {
            Toast.makeText(this,"All fields are required",Toast.LENGTH_SHORT).show();
            taglocation.setError("Tag your location");

        }
        System.out.println(name_value);
        System.out.println(email_value);
        System.out.println(dob_value);
        System.out.println(phone_value);
        System.out.println(gender_value);
        System.out.println(occupation_value);
        System.out.println(pin_value);
        System.out.println(confirmpin_value);
        System.out.println(latitude);
        System.out.println(longitude);*/

    }

    private void sendSmsEmail()
    {
        String phnNo = phone.getText().toString();
        String emailId = email.getText().toString();
        String url = "http://192.168.2.36:8089/aide/ConnectExecute.php?phnNo="+phnNo+"&emailId="+emailId;
        PostResponseAsyncTask task1 = new PostResponseAsyncTask(this, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                Toast.makeText(Signup.this, s, Toast.LENGTH_LONG).show();
            }
        });
        task1.execute(url);
        /*String phoneNo = "+91"+phone.getText().toString();
        String msg = "Test";//"Welcome to AIDE. Help you need is on fingertips.";

        PendingIntent pi = PendingIntent.getActivity(this, 0,
                new Intent(this, Signup.class), 0);
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNo, "5455", msg, pi, null);*/
        /*try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);
            Toast.makeText(getApplicationContext(), "SMS Sent!",
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "SMS failed, please try again later!",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }*/
    }

    @Override
    public void processFinish(String s) {
        Toast.makeText(Signup.this, s, Toast.LENGTH_LONG).show();
    }
}
