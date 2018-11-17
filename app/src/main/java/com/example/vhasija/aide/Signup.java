package com.example.vhasija.aide;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.wampSync.AsyncResponse;
import com.example.wampSync.PostResponseAsyncTask;

public class Signup extends AppCompatActivity implements AsyncResponse {


    public static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    double longitude =00.00;
    double latitude  =00.00;
    RadioButton  user,aide;
    EditText  name,email,dob,phone,pin,confirmpin;
    String Occupation[] = {"How can you Help","Doctor","Teacher", "Surgeon","Dentist","Fire Figther", "ParaMedic", "Police Officer"};
    String Gender[]= {"Gender","Male","Female"};
    String name_value,email_value,dob_value,phone_value,pin_value,confirmpin_value,
            gender_value,occupation_value;
    int flag =1;
    Spinner occupation,gender;
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
        boolean checked = ((RadioButton) view).isChecked();

        if (checked)
        {
            switch(view.getId())
            {
                case R.id.signup_User:
                    //occupation.setEnabled(false);
                    occupation.setVisibility(View.GONE);
                    break;
                case R.id.signup_Aide:
                    flag =-1;
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
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }
        //phone_value = phone.getText().toString();
        send_sms();
        /*System.out.println("MIke");
        if(flag== -1)
          {

          }

        name_value  = name.getText().toString();
        email_value = email.getText().toString();
        phone_value = phone.getText().toString();
        dob_value   = dob.getText().toString();
        pin_value = pin.getText().toString();
        confirmpin_value= confirmpin.getText().toString();

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

    public void send_sms()
    {
        String phnNo = phone.getText().toString();
        String url = "http://192.168.2.36:8089/aide/ConnectExecute.php?phnNo="+phnNo;
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
