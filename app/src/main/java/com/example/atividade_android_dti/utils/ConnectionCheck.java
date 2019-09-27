package com.example.atividade_android_dti.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.atividade_android_dti.GlobalApplicationContext;

public class ConnectionCheck {

    private static ConnectionCheck INSTANCE;

    public static ConnectionCheck getInstance(){

        if(INSTANCE == null)
            INSTANCE = new ConnectionCheck();

        return INSTANCE;

    }

    public boolean isNetworkAvailable() {

        try {
            ConnectivityManager connectivityManager = (ConnectivityManager)
                    GlobalApplicationContext
                            .getAPPCONTEXT()
                            .getSystemService(Context.CONNECTIVITY_SERVICE);

            if (connectivityManager != null) {
                NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
                return activeNetwork != null && activeNetwork.isConnected();
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }

    }
}
