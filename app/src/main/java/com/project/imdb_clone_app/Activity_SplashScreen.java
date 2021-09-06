package com.project.imdb_clone_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_SplashScreen extends AppCompatActivity {

    String nameStr;
    String emailStr;
    String passwordStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                String[] userLoginInfo= new String[3];
                String[] userData = MySharedPreferences.getUser(getApplicationContext());
                if(userData[0].isEmpty() && userData[1].isEmpty() && userData[2].isEmpty())
                {
                    startActivity(new Intent(Activity_SplashScreen.this, Activity_SignUp_or_Login.class));
                }
                else
                {
                    if(MySharedPreferences.getLoginStatus(Activity_SplashScreen.this))
                    {
                        Intent i = new Intent(Activity_SplashScreen.this, Activity_Home.class);
                        startActivity(i);
                    }
                    else{
                        startActivity(new Intent(Activity_SplashScreen.this, Activity_Login.class));
                    }

                }
                finish();
            }
        }, 1000);
    }
}