package com.example.atividade_android_dti;

import android.app.Application;
import android.content.Context;

public class GlobalApplicationContext extends Application {

    private static Context APPCONTEXT;

    @Override
    public void onCreate() {
        super.onCreate();

        APPCONTEXT = getApplicationContext();

    }

    public static Context getAPPCONTEXT(){

        return APPCONTEXT;
    }
}
