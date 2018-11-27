package com.example.vhasija.aide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wampSync.AsyncResponse;
import com.example.wampSync.PostResponseAsyncTask;

import org.json.JSONObject;

import static java.sql.Types.NULL;

public class change_password extends AppCompatActivity {


    EditText change_old_pin,change_new_pin,change_confirm_pin;
    String old_pin_value,new_pin_value,confirm_pin_value;
    int id;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent =getIntent();
        id = intent.getExtras().getInt("id");
        setContentView(R.layout.activity_change_password);
        change_old_pin = findViewById(R.id.change_old_pin);
        change_new_pin = findViewById(R.id.change_new_pin);
        change_confirm_pin = findViewById(R.id.change_confirm_pin);

    }

    public void savechanges(View view)
    {
        int flag =1;
        JSONObject json = new JSONObject();
        old_pin_value        = change_old_pin.getText().toString();
        new_pin_value        = change_new_pin.getText().toString();
        confirm_pin_value    = change_confirm_pin.getText().toString();
         try
         {
             if (!(old_pin_value.matches("^\\d{4}$")))
             {
                  flag = 0;
                  Toast.makeText(this,"Please enter your old pin",Toast.LENGTH_SHORT).show();
                  change_old_pin.setError("Please enter 4 digit old pin");
             }
             else
                  json.put("id",id);
                  json.put("old_pin",old_pin_value);
             if(!(new_pin_value.matches("^\\d{4}$")))
             {
                 flag =0;
                 Toast.makeText(this, "Please enter new pin", Toast.LENGTH_SHORT).show();
                 change_new_pin.setError("Please enter 4 digit new pin ");
             }
             else
                 json.put("new_pin",new_pin_value);

             if(!(confirm_pin_value.matches("^\\d{4}$")))
             {
                 flag =0;
                 Toast.makeText(this, "Please enter your confirm pin", Toast.LENGTH_SHORT).show();
                 change_confirm_pin.setError("Please enter 4 digit confirm pin");
             }

             if(!(confirm_pin_value.matches(new_pin_value)))
             {
                 flag =0;
                 Toast.makeText(this,"Please enter correct confirm pin",Toast.LENGTH_SHORT).show();
                 change_confirm_pin.setError("New pin does not matches confirm pin");
             }

         }
         catch (Exception e)
         {
             System.out.println(e);
         }

         if(flag==1)
         {
             changepassword(json);
         }
         else
         {
             Toast.makeText(this, "One or other fields are missing", Toast.LENGTH_SHORT).show();
         }
    }

    public void changepassword(JSONObject json)
    {
        String url = "http://192.168.2.34:8089/aide/changepassword.php?RequestType=change&data="+json;
        PostResponseAsyncTask task1 = new PostResponseAsyncTask(this, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
             //   Toast.makeText(change_password.this, s, Toast.LENGTH_LONG).show();
                System.out.println(s);
                  checkresult(s);
            }
        });
        task1.execute(url);

    }

    public void checkresult(String s)
    {
        if(s.matches("updated"))
        {
            Toast.makeText(this,"Your Pin has changed",Toast.LENGTH_LONG).show();
            finish();
        }
        else if (s.matches("No match")){
            Toast.makeText(this,"Old pin is incorrect. Try again!",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this,"error", Toast.LENGTH_SHORT).show();
        }
    }

    public void exit(View view)
    {
        finish();
    }

}
