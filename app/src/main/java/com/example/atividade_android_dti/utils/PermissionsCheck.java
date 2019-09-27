package com.example.atividade_android_dti.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;

public class PermissionsCheck {

    private static String[] permissions = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.INTERNET,
    };

    public static int P_CODE = 433;

    private static PermissionsCheck INSTANCE;

    public PermissionsCheck() { }

    public static PermissionsCheck getInstance(){

        if(INSTANCE == null)
            INSTANCE = new PermissionsCheck();

        return INSTANCE;

    }


    public boolean checkSelfPermissions(Activity act){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            boolean allowed = true;

            for(String s: permissions){

                if(ActivityCompat.checkSelfPermission(act, s) != PackageManager.PERMISSION_GRANTED)
                    allowed = false;

            }

            return allowed;

        }else{
            return true;
        }
    }

    public void requestSelfPermission(Activity act){
        ActivityCompat.requestPermissions(act, permissions, P_CODE);
    }

}
