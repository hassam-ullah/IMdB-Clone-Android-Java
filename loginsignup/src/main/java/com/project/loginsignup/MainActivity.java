package com.project.loginsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText email;
    EditText password;
    Button signUp_btn;


    String nameStr;
    String emailStr;
    String passwordStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        name=findViewById(R.id.editTextTextPersonName);
        email=findViewById(R.id.editTextTextEmailAddress);
        password=findViewById(R.id.editTextTextPassword);
        signUp_btn=findViewById(R.id.su_btn);
        signUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameStr=name.getText().toString();
                emailStr=email.getText().toString();
                passwordStr=password.getText().toString();

                if(!nameStr.isEmpty() && !emailStr.isEmpty() && !passwordStr.isEmpty())
                {
                    if(MySharedPreferences.createUser(getApplicationContext(),nameStr,emailStr,passwordStr))
                    {
                        Toast.makeText(getApplicationContext(), "DATA SAVED", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "COULD NOT SAVE", Toast.LENGTH_SHORT).show();
                    }
                }
                startActivity(new Intent(getApplicationContext(),Login.class));
            }


        });


    }
}