package com.example.vhasija.aide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class helppage extends AppCompatActivity {

     Spinner help_spinner;
    String help_string[] = {"Select","Doctor","Teacher", "Surgeon","Dentist","Fire Figther", "ParaMedic", "Police Officer"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helppage);
        help_spinner= findViewById(R.id.helpspinner);
        ArrayAdapter adapter1 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,help_string);
        help_spinner.setAdapter(adapter1);

    }
}
