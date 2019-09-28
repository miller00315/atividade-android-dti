package com.example.atividade_android_dti.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateHandler {

    public static String getDormatedData(long millis){

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyy", Locale.getDefault());

        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(millis);

        return formatter.format(calendar.getTime());


    }
}
