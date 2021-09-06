package com.project.loginsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.RecursiveTask;

public class Login extends AppCompatActivity {

    EditText email;
    EditText password;
    Button login_btn;


    String emailStr;
    String passwordStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=findViewById(R.id.login_email_input);
        password=findViewById(R.id.login_password_input);
        login_btn =findViewById(R.id.login_btn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailStr=email.getText().toString();
                passwordStr=password.getText().toString();

                if(validateLogin())
                {
                    Toast.makeText(getApplicationContext(), "LOGIN SUCCESFUL", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "FAILED", Toast.LENGTH_SHORT).show();
                }
            }


        });
    }

    public Boolean validateLogin()
    {
        if(!emailStr.isEmpty() && !passwordStr.isEmpty())
        {
            String[] userData = MySharedPreferences.getUser(getApplicationContext());
            if(userData[1].equals(emailStr) && userData[2].equals(passwordStr))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "EMPTY INPUT FIELD ", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}