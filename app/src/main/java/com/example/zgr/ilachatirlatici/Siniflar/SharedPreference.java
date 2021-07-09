package com.example.zgr.ilachatirlatici.Siniflar;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPreference {

    public static final String PREFS_NAME = "LOGIN_PS";





    public void saveInt(Context context,int id,String PREFS_KEY)
    {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.putInt(PREFS_KEY,id);
        editor.commit();
    }
    public void saveString(Context context,String text,String PREFS_KEY)
    {
        SharedPreferences settings;
        SharedPreferences.Editor editor;


        settings = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.putString(PREFS_KEY,text);
        editor.commit();
    }



    public String getStringValue(Context context,String PREFS_KEY){

        SharedPreferences settings;
        String text;

        settings = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);

        text = settings.getString(PREFS_KEY,"");
        return text;

    }

    public int getIntValue(Context context,String PREFS_KEY){
        SharedPreferences settings;
        int id;

        settings = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);

        id = settings.getInt(PREFS_KEY,0);
        return id;

    }

}
