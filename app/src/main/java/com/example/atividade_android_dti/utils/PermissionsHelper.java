package com.example.atividade_android_dti.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;

public class PermissionsHelper {

    private static String[] permissions = new String[]{
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_WIFI_STATE
    };

    public static int P_CODE = 433;

    private static PermissionsHelper INSTANCE;

    private PermissionsHelper() { }

    public static PermissionsHelper getInstance(){

        if(INSTANCE == null)
            INSTANCE = new PermissionsHelper();

        return INSTANCE;

    }

    public boolean checkSelfPermissions(Context context){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            boolean allowed = true;

            for(String s: permissions){

                if(ActivityCompat.checkSelfPermission(context, s) != PackageManager.PERMISSION_GRANTED)
                    allowed = false;

            }

            return allowed;

        }else{
            return true;
        }
    }

    public void onDestroy(){
        PermissionsHelper.INSTANCE = null;
    }

    public void requestSelfPermission(Context act){
        ActivityCompat.requestPermissions((Activity) act, permissions, P_CODE);
    }

}
