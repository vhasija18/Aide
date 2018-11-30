package com.example.vhasija.aide;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wampSync.AsyncResponse;
import com.example.wampSync.PostResponseAsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class EditProfile extends AppCompatActivity {

    EditText first_name,last_name,email,phone,dob;
    String firstname_value,lastname_value,phone_value,email_value,dob_value;
    Integer id_value;
    //Long phone_value;
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
        Intent intent = getIntent();
        firstname_value  = intent.getExtras().getString("firstname");
        lastname_value   = intent.getExtras().getString("lastname");
        email_value      = intent.getExtras().getString("email");
        dob_value        = intent.getExtras().getString("dob");
        id_value         = intent.getExtras().getInt("id");
        phone_value      = intent.getExtras().getString("phone");

        first_name.setText(firstname_value);
        last_name.setText(lastname_value);
        email.setText(email_value);
        dob.setText(dob_value);
        phone.setText(phone_value);




    }

    public void datePickerPopup(View view){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR); // current year
        int mMonth = c.get(Calendar.MONTH); // current month
        int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
        DatePickerDialog datePickerDialog = new DatePickerDialog(EditProfile.this,
                AlertDialog.THEME_DEVICE_DEFAULT_DARK,new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
                // set day of month , month and year value in the edit text
                dob.setError(null);
                dob.setText(dayOfMonth + "/"+ (monthOfYear + 1) + "/" + year);
            }
        }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void changepin(View view){
        Intent intent = new Intent(this,change_password.class);
        intent.putExtra("id",id_value);
        startActivity(intent);
    }

    public void  tagnewloc(View view){
        Intent intent = new Intent(this, Tag_new_loaction.class);
        intent.putExtra("id",id_value);
        startActivity(intent);
    }

    public void saveDetails(View view) throws JSONException {
        firstname_value  = first_name.getText().toString();
        lastname_value   = last_name.getText().toString();
        email_value = email.getText().toString();
        phone_value = phone.getText().toString();
        dob_value   = dob.getText().toString();
        int errflag = 1;
        JSONObject json = new JSONObject();

        json.put("id" , id_value);

        if((!firstname_value.matches("^[a-zA-Z]+$")) || (firstname_value.matches(""))){
            errflag = 0;
            first_name.setError("please enter a valid first name");
        }else{
            json.put("firstname" , firstname_value);
        }

        if ((!lastname_value.matches("^[a-zA-Z]+$")) || (lastname_value.matches(""))){
            errflag = 0;
            last_name.setError("please enter a valid last name");
        }
        else{
            json.put("lastname",lastname_value);
        }

        if((!email_value.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) || (email_value.matches(""))){
            errflag = 0;
            email.setError("please enter a valid email id");
        }else{
            json.put("email" , email_value);
        }

        if((!phone_value.matches("^\\d{10}$")) || (phone_value.matches(""))){
            errflag = 0;
            phone.setError("please enter a valid 10 digits phone number");
        }else{
            json.put("phone" , phone_value);
        }

        if(!dob_value.matches("^\\d{1,2}\\/\\d{1,2}\\/\\d{4}$")){
            errflag = 0;
            dob.setError("please enter a valid date of birth");
        }else{
            json.put("dob" , dob_value);
        }

        if(errflag == 1){
            String url = "http://192.168.2.36:8089/aide/index.php?RequestType=update&dataArr="+json;
            PostResponseAsyncTask task1 = new PostResponseAsyncTask(this, new AsyncResponse() {
                @Override
                public void processFinish(String s) throws JSONException {
                    JSONObject jsonObject = new JSONObject(s);
                    String jsonArray = jsonObject.getString("result");
                    System.out.println(jsonArray);
                    switch (jsonArray){
                        case "true":
                            profile_page();
                            break;

                        case "email":
                            System.out.println("email");
                            Toast.makeText(EditProfile.this,"The email id you have entered already exists, please enter new email id.",Toast.LENGTH_SHORT).show();
                            break;

                        case "phone":
                            System.out.println("phone");
                            Toast.makeText(EditProfile.this, "The phone no you have entered already exists, please enter new phone no.", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
            task1.execute(url);
        }else
            Toast.makeText(EditProfile.this,"One or the other detail entered is either not entered or invalid",Toast.LENGTH_SHORT).show();
    }


    public void profile_page()
    {
        try {
            Toast.makeText(EditProfile.this, "Details updated successfully", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, ProfilePage.class);
            intent.putExtra("Id", id_value);
            startActivity(intent);
        }catch(Exception e){
            System.out.println(e);
        }

    }

}