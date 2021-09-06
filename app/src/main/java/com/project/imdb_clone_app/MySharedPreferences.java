package com.project.imdb_clone_app;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreferences {
    public static final String NAME ="username";
    public static final String EMAIL="email";
    public static final String PASSWORD="password";
    public static final String LOGIN_STATUS="loginStatus";
    public static final String WRITE_TO_DATABASE_STATUS="WriteToDatabaseStatus";

    public static boolean createUser(Context context, String name,String email, String password)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(NAME,name);
        editor.putString(EMAIL,email);
        editor.putString(PASSWORD,password);

        return editor.commit();
    }

    public static String[] getUser(Context context)
    {
        String[] userLoginInfo= new String[3];
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        userLoginInfo[0]=sharedPreferences.getString(NAME,"");
        userLoginInfo[1]=sharedPreferences.getString(EMAIL,"");
        userLoginInfo[2]=sharedPreferences.getString(PASSWORD,"");

        return userLoginInfo;
    }

    public static void setLoginStatus(Context context,Boolean loginStatus)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(LOGIN_STATUS,loginStatus);
        editor.commit();
    }
    public static Boolean getLoginStatus(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(LOGIN_STATUS,false);
    }
    public static void setWriteToDatabaseStatus(Context context,Boolean writeToDatabaseStatus)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(WRITE_TO_DATABASE_STATUS,writeToDatabaseStatus);
        editor.commit();
    }
    public static Boolean getWriteToDatabaseStatus(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(WRITE_TO_DATABASE_STATUS,true);
    }

    public static boolean removeUser(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.remove(NAME);
        editor.remove(EMAIL);
        editor.remove(PASSWORD);

        return editor.commit();
    }
}
