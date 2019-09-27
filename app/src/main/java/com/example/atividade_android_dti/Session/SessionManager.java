package com.example.atividade_android_dti.Session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.util.Log;

import com.example.atividade_android_dti.GlobalApplicationContext;
import com.example.atividade_android_dti.login.LoginScreen;
import com.example.atividade_android_dti.login.domain.model.LoginResponseModel;
import com.example.atividade_android_dti.utils.Utils;


public class SessionManager {

    private static SessionManager INSTANCE;

    public static SessionManager getInstance(){

        if(INSTANCE == null)
            INSTANCE = new SessionManager();

        return INSTANCE;
    }

    private SessionManager() { }

    public void saveLoginTokenOnPreferences(LoginResponseModel loginResponseModel){

        SharedPreferences sharedPreferences = getSharedPreferences();

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("token", loginResponseModel.getToken());
        editor.putString("tempoExpirar", loginResponseModel.getTempoExpirar());

        editor.apply();
    }

    public void clearSharedPreferences(){

        SharedPreferences sharedPreferences = getSharedPreferences();

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear();

        editor.apply();

    }

    private SharedPreferences getSharedPreferences(){

        return GlobalApplicationContext
                .getAPPCONTEXT()
                .getSharedPreferences(Utils.PREF_NAME, Context.MODE_PRIVATE);
    }

    public void startSessionCountDown(){

        new CountDownTimer(60000, 2000){

            public void onTick(long millisUntilFinished) {
                Log.w("thi", String.valueOf(millisUntilFinished));
            }

            public void onFinish() {
                logOut();
            }
        }.start();


    }

    private void logOut(){

        Intent i = new Intent(GlobalApplicationContext.getAPPCONTEXT(), LoginScreen.class);

        GlobalApplicationContext.getAPPCONTEXT().startActivity(i);

        Intent brodCastIntent = new Intent();
        brodCastIntent.setAction(Utils.BROADCAST_MESSAGE);
        GlobalApplicationContext.getAPPCONTEXT().sendBroadcast(brodCastIntent);
    }
}
