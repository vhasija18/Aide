package com.example.vhasija.aide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.wampSync.AsyncResponse;
import com.example.wampSync.PostResponseAsyncTask;

import org.json.JSONObject;

public class helppage extends AppCompatActivity {

    Spinner help_spinner;
    String help_string[] = {"Select a service","Ambulance","Doctor", "Plumber","Electrician","Mechanic"};
    String help_value, helper_name,helper_email,helper_gender,user_name,user_email,message;
    int errflag,help_value_int;
    String helper_phone,user_phone;
    double user_latitude,user_longitude,helper_longitude,helper_latitude;
    JSONObject jsonobject;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        user_latitude=intent.getExtras().getDouble("user_latitude");
        user_longitude=intent.getExtras().getDouble("user_longitude");
        user_name = intent.getExtras().getString("user_name").toString();
        user_email = intent.getExtras().getString("user_email").toString();
        user_phone = intent.getExtras().getString("user_phone");
        System.out.println(user_name + " "+ " "+user_phone+" "+user_email+" "+user_latitude+" "+user_longitude);
        setContentView(R.layout.activity_helppage);
        help_spinner= findViewById(R.id.helpspinner);
        ArrayAdapter adapter1 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,help_string);
        help_spinner.setAdapter(adapter1);
        help_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               help_value=parent.getItemAtPosition(position).toString();
               }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

   public void fetch_result(View view)
   {
       if(help_value.matches("Select a service")){
           errflag = 0;
           ((TextView)help_spinner.getSelectedView()).setError("please select one option");
       }else{
           switch (help_value) {
               case "Ambulance":
                   help_value_int = 1;
                   break;

               case "Doctor":
                   help_value_int = 2;
                   break;

               case "Plumber":
                   help_value_int = 3;
                   break;

               case "Electrician":
                   help_value_int = 4;
                   break;

               case "Mechanic":
                   help_value_int = 5;
                   break;
           }

         }


         if (help_value_int !=0) {
             System.out.println(help_value_int);
             String url = "http://192.168.2.36:8089/aide/gethelp.php?RequestType=help&help_value="+help_value_int+"&latitude="+user_latitude+"&longitude="+user_longitude;
             PostResponseAsyncTask task1 = new PostResponseAsyncTask(this, new AsyncResponse() {
                 @Override
                 public void processFinish(String s) {
                     //Toast.makeText(helppage.this, s, Toast.LENGTH_LONG).show();
                     System.out.println(s);
                     getdetails(s);
                 }
             });
             task1.execute(url);
         }
   }

   public void getdetails(String s)
   {
       try{
              jsonobject = new JSONObject(s).getJSONObject("result");

              message = jsonobject.getString("message").toString();
              if(message.matches("Help found"))
              {
                  helper_name =  jsonobject.getString("first_name").toString() + " "+jsonobject.getString("last_name").toString();
                  helper_email = jsonobject.getString("email").toString();
                  helper_gender = jsonobject.getString("gender").toString();
                  helper_phone =jsonobject.getString("phone");
                  helper_latitude=jsonobject.getDouble("latitude");
                  helper_longitude= jsonobject.getDouble("longitude");

                  Intent intent = new Intent(this,displayinfo.class);

                  intent.putExtra("helper_name",helper_name);
                  intent.putExtra("helper_email",helper_email);
                  intent.putExtra("help",help_value);
                  intent.putExtra("helper_gender",helper_gender);
                  intent.putExtra("helper_phone",helper_phone);
                  intent.putExtra("helper_latitude",helper_latitude);
                  intent.putExtra("helper_longitude",helper_longitude);

                  startActivity(intent);
              }

       }
       catch(Exception e)
       {
           System.out.println(e);
       }


   }
}
