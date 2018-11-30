package com.example.vhasija.aide;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class displayinfo extends AppCompatActivity {

    Intent intent;
    TextView name,gender,email,phone,location,heading;
    Geocoder geocoder;
    List<Address> addresses;
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
        double latitude = intent.getExtras().getDouble("helper_latitude");
        double longitude = intent.getExtras().getDouble("helper_longitude");

        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        } catch (IOException e) {
            e.printStackTrace();
        }

        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
        String city = addresses.get(0).getLocality();
        String state = addresses.get(0).getAdminArea();
        String country = addresses.get(0).getCountryName();
        String postalCode = addresses.get(0).getPostalCode();
        String knownName = addresses.get(0).getFeatureName();
        /*System.out.println(knownName);
        System.out.println(postalCode);
        System.out.println(country);
        System.out.println(state);
        System.out.println(city);*/
        System.out.println(address);

        heading.setText("Nearest " + intent.getExtras().getString("help").toString() + " is");
        name.setText(intent.getExtras().getString("helper_name").toString());
        email.setText(intent.getExtras().getString("helper_email").toString());
        phone.setText(intent.getExtras().getString("helper_phone").toString());
        location.setText(address+" \n"+"http://maps.google.com/?q="+intent.getExtras().getDouble("helper_latitude")+","+intent.getExtras().getDouble("helper_longitude"));
        location.setMovementMethod(LinkMovementMethod.getInstance());
        if(intent.getExtras().getString("helper_gender").toString()=="1"){
         gender.setText("Male");
        }else{
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
