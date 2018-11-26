package com.example.vhasija.aide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wampSync.AsyncResponse;
import com.example.wampSync.PostResponseAsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

public class Signup extends AppCompatActivity implements AsyncResponse {


    public static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    Double longitude =-00.00;
    Double latitude  =-00.00;
    RadioButton  user,aide;
    EditText  firstname,lastname,email,dob,phone,pin,confirmpin;
    String Occupation[] = {"How can you help","Doctor","Ambulance", "Plumber","Mechanic","Electrician"};
    String Gender[]= {"Gender","Male","Female"};
    String firstname_value,lastname_value,email_value,dob_value,phone_value,pin_value,confirmpin_value, gender_value,occupation_value;
    int name_flag,email_flag,dob_flag,phone_flag,pin_flag,confirmpin_flag,user_type=0;
    Spinner occupation,gender;
    Button taglocation;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        occupation  = (Spinner) findViewById(R.id.signup_help);
        gender      = (Spinner) findViewById(R.id.signup_gender);
        firstname   = (EditText) findViewById(R.id.signup_FirstName);
        lastname    =  (EditText) findViewById(R.id.signup_LastName);
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
                //System.out.println(gender_value);
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

        if(checked)
        {
            switch(view.getId())
            {
                case R.id.signup_User:
                    user_type = 1;
                    occupation.setVisibility(View.GONE);
                    break;
                case R.id.signup_Aide:
                    user_type = 2;
                    occupation.setVisibility(View.VISIBLE);
                    occupation.setEnabled(true);
                    occupation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            occupation_value= parent.getItemAtPosition(position).toString();
                            //System.out.println(occupation_value);
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
        System.out.println(requestCode);
        System.out.println(resultCode);
            if (resultCode == RESULT_OK) {
                longitude = data.getExtras().getDouble("Longi");
                latitude  = data.getExtras().getDouble("Lati");
            }
        }

    public void signup(View view) throws JSONException {
        System.out.println("signup");

        name_flag=0;
        phone_flag=0;
        email_flag=0;
        dob_flag=0;
        pin_flag=0;
        confirmpin_flag=0;
        firstname_value  = firstname.getText().toString();
        lastname_value   = lastname.getText().toString();
        email_value = email.getText().toString();
        phone_value = phone.getText().toString();
        dob_value   = dob.getText().toString();
        pin_value = pin.getText().toString();
        confirmpin_value= confirmpin.getText().toString();

        JSONObject json = new JSONObject();

        if(user_type==0){
            RadioButton lastRadioBtn = (RadioButton) view.findViewById(R.id.signup_User);
            //Toast.makeText(Signup.this, "Select user type", Toast.LENGTH_SHORT).show();
        }else{
            json.put("user_type" , String.valueOf(user_type));
        }

        if(firstname_value.matches("")){
            Toast.makeText(this,"All fields are required", Toast.LENGTH_SHORT).show();
            firstname.setError("First Name is missing");
        }else{
            json.put("firstname" , firstname_value);
        }

        if (lastname_value.matches(""))
        {
            Toast.makeText(this,"All fields are required",Toast.LENGTH_SHORT).show();
            firstname.setError("Last Name is missing");
        }
        else{
            json.put("lastname",lastname_value);
        }

        if(email_value.matches("")){
            Toast.makeText(this,"All fields are required", Toast.LENGTH_SHORT).show();
            email.setError("Email is missing");
        }else{
            json.put("email" , email_value);
        }

        if(phone_value.matches("")){
            Toast.makeText(this,"All fields are required", Toast.LENGTH_SHORT).show();
            phone.setError("Phone number is missing");
        }else{
            json.put("phone" , phone_value);
        }

        if(dob_value.matches("")){
            Toast.makeText(this,"All fields are required",Toast.LENGTH_SHORT).show();
            dob.setError("Date of birth is missing");
        }else{
            json.put("dob" , dob_value);
        }

        if(pin_value.matches("")){
            Toast.makeText(this,"All fields are required",Toast.LENGTH_SHORT).show();
            pin.setError("Please enter your pin");
        }else{
            json.put("pin" , pin_value);
        }

        if(confirmpin_value.matches("")){
            Toast.makeText(this,"All fields are required",Toast.LENGTH_SHORT).show();
            confirmpin.setError("Confirm your pin");

        }else{
            json.put("cpin" , confirmpin_value);
        }

        if(!(confirmpin_value.matches(pin_value))){
            Toast.makeText(this,"Pin and Confirm pin doesn't match",Toast.LENGTH_SHORT).show();
            confirmpin.setError("Doesnot Match");
        }

        if(gender_value.matches("Gender")){
            Toast.makeText(this,"All fields are required",Toast.LENGTH_SHORT).show();
            ((TextView)gender.getSelectedView()).setError("Please select gender");
        }else{
            switch (gender_value) {
                case "Male":
                    gender_value = String.valueOf(1);
                    break;
                case "Female":
                    gender_value = String.valueOf(2);
                    break;
            }
            json.put("gender", gender_value);
        }

        if(user_type == 2){
            if(occupation_value.matches("How can you help")){
                Toast.makeText(this,"All fields are required",Toast.LENGTH_SHORT).show();
                ((TextView)occupation.getSelectedView()).setError("Please select your option");
            }else{
                switch (occupation_value) {
                    case "Ambulance":
                        occupation_value = String.valueOf(1);
                        break;

                    case "Doctor":
                        occupation_value = String.valueOf(2);
                        break;

                    case "Plumber":
                        occupation_value = String.valueOf(3);
                        break;

                    case "Electrician":
                        occupation_value = String.valueOf(4);
                        break;

                    case "Mechanic":
                        occupation_value = String.valueOf(5);
                        break;
                }

                if(occupation_value!=null || occupation_value=="")
                    json.put("occupation" , occupation_value);
            }
        }else
            json.put("occupation" , String.valueOf(0));

        if((latitude==-00.00 && longitude==-00.00) || (latitude==null && longitude==null)){
            Toast.makeText(this,"All fields are required",Toast.LENGTH_SHORT).show();
            ((TextView)taglocation).setError("Tag your location");

        }else{
            ((TextView)taglocation).setError(null);
            json.put("latitude" , String.valueOf(latitude));
            json.put("longitude" , String.valueOf(longitude));
        }

        /*System.out.println(name_value);
        System.out.println(email_value);
        System.out.println(dob_value);
        System.out.println(phone_value);
        System.out.println(gender_value);
        System.out.println(occupation_value);
        System.out.println(pin_value);
        System.out.println(confirmpin_value);
        System.out.println(latitude);
        System.out.println(longitude);*/

        String url = "http://192.168.2.34:8089/aide/index.php?RequestType=signup&dataArr="+json;
        PostResponseAsyncTask task1 = new PostResponseAsyncTask(this, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                Toast.makeText(Signup.this, s, Toast.LENGTH_LONG).show();
                System.out.println(s);
                navigatelogin(s);
            }
        });
        task1.execute(url);


        //sendSmsEmail();
    }

    private void sendSmsEmail()
    {
        String phnNo = phone.getText().toString();
        String emailId = email.getText().toString();
        String url = "http://192.168.2.34:8089/aide/ConnectExecute.php?phnNo="+phnNo+"&emailId="+emailId;
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

    public void navigatelogin(String s )
    {
        if(s.matches("Registered successfully!"))
        {
            Intent intent = new Intent(this,login.class);
            startActivity(intent);
        }
    }
}
