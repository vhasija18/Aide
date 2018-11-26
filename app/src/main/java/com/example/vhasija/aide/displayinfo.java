package com.example.vhasija.aide;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

public class displayinfo extends AppCompatActivity {

    Intent intent;
    TextView name,gender,email,phone,location,heading;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayinfo);
         intent = getIntent();
         name = findViewById(R.id.displayinfo_name);
         gender = findViewById(R.id.displayinfo_gender);
         email = findViewById(R.id.displayinfo_email);
         phone = findViewById(R.id.displayinfo_phone);
         location = findViewById(R.id.displayinfo_loccation);
         heading = findViewById(R.id.displayinfo_heading);

         heading.setText("Nearest " + intent.getExtras().getString("help").toString() + " is");
         name.setText(intent.getExtras().getString("helper_name").toString());
         email.setText(intent.getExtras().getString("helper_email").toString());
         phone.setText(intent.getExtras().getString("helper_phone").toString());
         location.setText("http://maps.google.com/?q="+intent.getExtras().getString("helper_latitude")+","+intent.getExtras().getString("helper_longitude"));
        location.setMovementMethod(LinkMovementMethod.getInstance());
         if(intent.getExtras().getInt("helper_gender")==1)
         {
             gender.setText("Male");
         }
          else{
             gender.setText("Female");
         }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void exit(View view)
    {
        finishAffinity();
        System.exit(0);
    }
}
