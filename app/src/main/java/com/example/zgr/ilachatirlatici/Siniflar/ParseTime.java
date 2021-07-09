package com.example.zgr.ilachatirlatici.Siniflar;

import android.text.format.Time;


public class ParseTime {

    public static Time fromLong(Long millis) {
        Time t = new Time();
        t.set(millis);
        return t;
    }

    public static Time t = new Time();


    public static Time converter(String time)
    {
        try{
            String hours = time.substring(0,2);
            String minutes = time.substring(3);

            t.hour = Integer.parseInt(hours);
            t.minute = Integer.parseInt(minutes);

        }
        catch (Exception e)
        {
            return null;
        }
        return t;

    }



}
