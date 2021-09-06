package com.project.imdb_clone_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_SignUp_or_Login extends AppCompatActivity {

    Button signIn_btn;
    Button create_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_or_login);

        signIn_btn = findViewById(R.id.signIn_button);
        signIn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_SignUp_or_Login.this, Activity_Login.class);
                startActivity(intent);
            }
        });

        create_btn = findViewById(R.id.create_account_btn);
        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_SignUp_or_Login.this, Activity_SignUp.class);
                startActivity(intent);
            }
        });
    }
}