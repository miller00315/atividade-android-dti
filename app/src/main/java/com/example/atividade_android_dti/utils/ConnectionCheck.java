package com.example.atividade_android_dti.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.atividade_android_dti.GlobalApplicationContext;

public class ConnectionCheck {

    public static boolean isNetworkAvailable() {

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
