package com.example.atividade_android_dti.utils;


public class StringsHelpers {

    public static boolean isValidUserName(String name){
        return name != null && name.matches("[a-zA-Z0-9&.]+$") && !name.isEmpty();
    }

    public static boolean isValidPassword(String password){
        return password.length() >= 6;
    }
}
