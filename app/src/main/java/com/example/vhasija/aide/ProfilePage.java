package com.example.vhasija.aide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.wampSync.AsyncResponse;
import com.example.wampSync.PostResponseAsyncTask;

import org.json.JSONObject;

public class ProfilePage extends AppCompatActivity {

    TextView name, phone,email,dob;
    String firstname_sql,lastname_sql,phone_sql,email_sql,dob_sql,success;
    //Long phone_sql;
    double latitude_sql,longitutde_sql;
    int id,type_sql,occupation_sql;
    Button need_help,profile_edit;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent =getIntent();
        id=intent.getExtras().getInt("Id");
        System.out.println("id"+id);
        setContentView(R.layout.activity_profile_page);
        name  = findViewById(R.id.profile_name);
        phone = findViewById(R.id.profile_phone);
        email = findViewById(R.id.profile_email);
        dob   = findViewById(R.id.profile_dob);
        need_help = findViewById(R.id.profile_needhelp);
        profile_edit=findViewById(R.id.profile_edit);
        fetchdetails(id);

    }

    public void edit_profile(View view)
    {
        Intent intent = new Intent(this,EditProfile.class);
        intent.putExtra("firstname",firstname_sql);
        intent.putExtra("lastname",lastname_sql);
        intent.putExtra("email",email_sql);
        intent.putExtra("phone",phone_sql);
        intent.putExtra("id",id);
        intent.putExtra("occupation",occupation_sql);
        intent.putExtra("dob",dob_sql);
        startActivity(intent);

    }


    public void needhelp(View view)
    {

        Intent intent = new Intent(this,Maps_Login.class);
        intent.putExtra("name",firstname_sql+" "+lastname_sql);
        intent.putExtra("phone",phone_sql);
        intent.putExtra("email",email_sql);
        startActivity(intent);
    }


    public void fetchdetails (Integer id)
    {
        String url = "http://192.168.2.36:8089/aide/fetchdetails.php?RequestType=fetch&id="+id;
        PostResponseAsyncTask task1 = new PostResponseAsyncTask(this, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
               // Toast.makeText(ProfilePage.this, s, Toast.LENGTH_LONG).show();
               System.out.println(s);
                extractdetails(s);
            }
        });
        task1.execute(url);


    }

    public void extractdetails(String s)
    {
        System.out.println("Welcome");
        try{
            JSONObject jsonObject = new JSONObject(s).getJSONObject("result");
            success = jsonObject.getString("success").toString();
            if(success.matches("true"))
            {
               firstname_sql = jsonObject.getString("first_name");
               lastname_sql  = jsonObject.getString("last_name");
               email_sql     = jsonObject.getString("email");
               phone_sql     = jsonObject.getString("phone");
               dob_sql       = jsonObject.getString("dob");
               type_sql      = jsonObject.getInt("type");
               occupation_sql= jsonObject.getInt("occupation");
               latitude_sql  = jsonObject.getDouble("latitude");
               longitutde_sql= jsonObject.getDouble("longitude");
               name.setText(firstname_sql +" "+lastname_sql);
               email.setText(email_sql);
               dob.setText(dob_sql);
               phone.setText(phone_sql);
               if(type_sql == 2)
               {
                   need_help.setVisibility(View.INVISIBLE);
               }

            }
        }

        catch (Exception e)
        {
            System.out.println(e);
        }


    }

}
