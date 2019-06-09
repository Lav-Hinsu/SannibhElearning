package com.sannibhelearning;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements TaskCompleted{

    String semail,spassword;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

                final EditText  email=(EditText)findViewById(R.id.Email);
                final EditText  password=(EditText)findViewById(R.id.password);
                final Button login = (Button)findViewById(R.id.login);
                Button register = (Button)findViewById(R.id.RegisterActivity);
            //input validation
            if(password.length()>6)
            {
                login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //password validation
                    }
                });

            }
            else{
                Toast.makeText(this,"Password Too Short",Toast.LENGTH_SHORT);
            }

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    semail=email.getText().toString();
                    spassword=password.getText().toString();
                    LoginQuery q = new LoginQuery(LoginActivity.this);
                    q.execute(email.getText().toString(), password.getText().toString());


                }
            });

            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent register_activity = new Intent(v.getContext(),RegisterActivity .class);
                    startActivity(register_activity);
                }
            });

    }

    @Override
    public void onTaskComplete(String result) {
        Toast.makeText(this, ""+result, Toast.LENGTH_SHORT).show();
        if(result=="success"){
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            preferences=getSharedPreferences("UserPrefs",Context.MODE_PRIVATE);
            SharedPreferences.Editor data = preferences.edit();
            data.putString("email", semail);
            data.putString("password", spassword);
            data.commit();

            startActivity(i);
        }
        else if(result=="fail")
        {
            Toast.makeText(getApplicationContext(),"Invalid Email Or Password",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Network Error",Toast.LENGTH_SHORT).show();
        }

    }
}