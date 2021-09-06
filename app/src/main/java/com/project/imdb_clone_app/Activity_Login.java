package com.project.imdb_clone_app;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class Activity_Login extends AppCompatActivity {

    Button login_btn;
    Button createNew_btn;
    CheckBox checkBox;
    EditText email;
    EditText password;

    String emailStr;
    String passwordStr;
    Boolean checked=false;
    SQLiteHelper helper = new SQLiteHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(MySharedPreferences.getLoginStatus(this))
        {
            Intent i = new Intent(this, Activity_Home.class);
            startActivity(i);
        }

        createNew_btn = findViewById(R.id.login_create_new_acc_btn);
        checkBox=findViewById(R.id.login_checkbox);
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
                    startActivity(new Intent(Activity_Login.this, Activity_Home.class));
                }
                else{
                    Toast.makeText(getApplicationContext(), "LOGIN FAILED", Toast.LENGTH_SHORT).show();
                }
            }
        });
        createNew_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_Login.this, Activity_SignUp.class);
                startActivity(intent);
            }
        });
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checked==true)
                {
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    checked=false;
                }
                else
                {
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    checked=true;
                }
            }
        });
    }

    public Boolean validateLogin()
    {
        if(!emailStr.isEmpty() && !passwordStr.isEmpty())
        {
            List<Model_User> users= helper.readAllUsers();
            for (Model_User user : users) {
                if (user.getEmail().equals(emailStr) && user.getPassword().equals(passwordStr)) {
                    MySharedPreferences.createUser(getApplicationContext(),user.getName(),user.getEmail(),user.getPassword());
                    MySharedPreferences.setLoginStatus(Activity_Login.this, true);
                    return true;
                }
            }
            return false;
        }
        else
        {
            Toast.makeText(getApplicationContext(), "EMPTY INPUT FIELD ", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}