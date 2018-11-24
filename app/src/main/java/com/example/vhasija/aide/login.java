package com.example.vhasija.aide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.wampSync.AsyncResponse;
import com.example.wampSync.PostResponseAsyncTask;

import org.json.JSONException;
import org.json.JSONObject;


public class login extends AppCompatActivity {


    EditText name,password;
    String  name_string, password_string;
    int name_flag,password_flag;
    JSONObject jsonobject;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name = (EditText) findViewById(R.id.Name);
        password = (EditText) findViewById(R.id.Password);



    }


    public void Maps_login(View view)
    {
        name_flag=0;
        password_flag=0;
        name_string = name.getText().toString();
        password_string = password.getText().toString();

        System.out.println("Hello name is "+name_string);
        if(name_string.matches(""))
        {
            name_flag=-1;
            Toast.makeText(this, "Enter the details", Toast.LENGTH_SHORT).show();
            name.setError("Email/Phone is missing");
        }
        if(password_string.matches(""))
        {
            password_flag=-1;
            Toast.makeText(this,"Enter details",Toast.LENGTH_SHORT).show();
            password.setError("Password is missing");
        }

        if(name_flag==0 && password_flag==0)
        {

            //Intent intent = new Intent(login.this,ProfilePage.class);
            //startActivity(intent);

            String url = "http://192.168.2.36:8089/aide/index.php?RequestType=login&EmailorPhone="+name_string+"&Pin="+Integer.parseInt(password_string);
            PostResponseAsyncTask task1 = new PostResponseAsyncTask(this, new AsyncResponse() {
                @Override
                public void processFinish(String s) throws JSONException {
                    Toast.makeText(login.this, s, Toast.LENGTH_LONG).show();
                    System.out.println(s);

                    try{
                        //vishwas has to code ahead
                        JSONObject result = new JSONObject(s.toString()).getJSONObject("result");
                        System.out.println("first name is:"+result.getString("first_name"));
                        System.out.println("last name is:"+result.getString("last_name"));
                        System.out.println("email is:"+result.getString("email"));
                        System.out.println("phone no is:"+result.getString("phone"));
                        System.out.println("user type is:"+result.getString("type"));

                    }catch (Exception e)
                    {
                        System.out.println(e);
                    }

                }
            });
            task1.execute(url);

        }

    }

    public void contactUs(View view){
        System.out.println("email");
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("application/octet-stream");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"aideapplicationcanada@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "Contact us");
        i.putExtra(Intent.EXTRA_TEXT   , "");
        try {
            startActivity(Intent.createChooser(i, "Mail your query..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(login.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

}
