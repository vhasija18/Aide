package com.example.vhasija.aide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class displayinfo extends AppCompatActivity {

    Intent intent;
    TextView name,gender,email,phone,location;
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

         name.setText(intent.getExtras().getString("helper_name").toString());
         gender.setText(intent.getExtras().getString("helper_gender").toString());
         email.setText(intent.getExtras().getString("helper_email").toString());
         phone.setText(intent.getExtras().getString("helper_phone").toString());
         location.setText("http://maps.google.com/?q="+intent.getExtras().getString("helper_latitude")+","+intent.getExtras().getString("helper_longitude"));
        location.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
