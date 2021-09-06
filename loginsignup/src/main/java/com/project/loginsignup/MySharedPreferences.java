package com.project.loginsignup;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreferences {
    public static final String NAME ="username";
    public static final String EMAIL="email";
    public static final String PASSWORD="password";

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
