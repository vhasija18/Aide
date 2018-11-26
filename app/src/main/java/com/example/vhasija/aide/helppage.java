package com.example.vhasija.aide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.wampSync.AsyncResponse;
import com.example.wampSync.PostResponseAsyncTask;

import org.json.JSONObject;

public class helppage extends AppCompatActivity {

     Spinner help_spinner;
    String help_string[] = {"Select","Doctor","Teacher", "Surgeon","Dentist","Fire Figther", "ParaMedic", "Police Officer"};
    String help_value, helper_name,helper_phone,helper_email,helper_gender,helper_longitude,helper_latitude,message;
    JSONObject jsonobject;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helppage);
        help_spinner= findViewById(R.id.helpspinner);
        ArrayAdapter adapter1 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,help_string);
        help_spinner.setAdapter(adapter1);
        help_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               help_value=parent.getItemAtPosition(position).toString();
               fetchresult(help_value);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

   public void fetchresult(String help)
   {
       String url = "http://192.168.2.34:8089/aide/gethelp.php?help="+help_value;
       PostResponseAsyncTask task1 = new PostResponseAsyncTask(this, new AsyncResponse() {
           @Override
           public void processFinish(String s) {
               Toast.makeText(helppage.this, s, Toast.LENGTH_LONG).show();
               System.out.println(s);
              getdetails(s);
           }
       });
       task1.execute(url);

   }

   public void getdetails(String s)
   {
       try{
              jsonobject = new JSONObject(s);

              message = jsonobject.getString("message").toString();
              if(message.matches("Help found"))
              {
                  helper_name =  jsonobject.getString("name").toString();
                  helper_email = jsonobject.getString("email").toString();
                  helper_gender= jsonobject.getString("gender").toString();
                  helper_phone =jsonobject.getString("phone").toString();
                  helper_latitude=jsonobject.getString("latitude").toString();
                  helper_longitude= jsonobject.getString("longitude").toString();

                  Intent intent = new Intent(this,displayinfo.class);

                  intent.putExtra("helper_name",helper_name);
                  intent.putExtra("helper_email",helper_email);
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
