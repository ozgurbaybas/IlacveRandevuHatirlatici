package com.example.zgr.ilachatirlatici.Siniflar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class ParseDate {

    public static Date d = new Date();

    private static SimpleDateFormat format = new SimpleDateFormat("EE MM dd HH:mm:ss", Locale.getDefault());

    public static Date convertString(String date) {
        try {
            return format.parse(date);
        } catch (Exception ignored) {
            return null;
        }
    }


}

