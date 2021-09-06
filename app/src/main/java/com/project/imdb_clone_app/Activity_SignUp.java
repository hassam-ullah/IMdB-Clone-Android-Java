package com.project.imdb_clone_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class Activity_SignUp extends AppCompatActivity {

    EditText name;
    EditText email;
    EditText password;
    Button signUp_btn;

    SQLiteHelper helper;

    String nameStr;
    String emailStr;
    String passwordStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        helper = new SQLiteHelper(this);

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
                    List<Model_User> users= helper.readAllUsers();
                    for (Model_User user : users) {
                            if (user.getName().equals(nameStr) && user.getEmail().equals(emailStr)) {
                                Toast.makeText(getApplicationContext(), "ACCOUNT ALREADY EXISTS", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }

                    helper.addUser(new Model_User(nameStr,emailStr,passwordStr));
                    Intent intent =new Intent(getApplicationContext(), Activity_Login.class);
                    startActivity(intent);
                }
            }
        });
    }
}