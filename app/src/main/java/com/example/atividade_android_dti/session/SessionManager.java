package com.example.atividade_android_dti.session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.util.Log;

import com.example.atividade_android_dti.GlobalApplicationContext;
import com.example.atividade_android_dti.login.LoginActivity;
import com.example.atividade_android_dti.login.domain.models.LoginTokenModel;
import com.example.atividade_android_dti.utils.ActivityStatusHelper;
import com.example.atividade_android_dti.utils.Utils;

import java.util.concurrent.TimeUnit;


public class SessionManager {

    private static SessionManager INSTANCE;
    private static int EXPIRATION_SESSION_TIME;
    private CountDownTimer timerSession;

    public static SessionManager getInstance(){

        if(INSTANCE == null)
            INSTANCE = new SessionManager();

        return INSTANCE;
    }

    private SessionManager() { }

    public void setLoginTokenOnPreferences(LoginTokenModel loginTokenModel){

        EXPIRATION_SESSION_TIME = loginTokenModel.getIntegerValueTempoExpirar();

        SharedPreferences.Editor editor = getSharedPreferences().edit();

        editor.putString("token", loginTokenModel.getToken());

        editor.apply();

    }

    private void removeTokenSharedPreferences(){

        SharedPreferences.Editor editor = getSharedPreferences().edit();

        editor.clear();

        editor.apply();

    }

    private SharedPreferences getSharedPreferences(){

        return GlobalApplicationContext
                .getAPPCONTEXT()
                .getSharedPreferences(Utils.PREF_NAME, Context.MODE_PRIVATE);
    }

    public void startSessionCountDown(){

        if(timerSession != null){

            timerSession.cancel();
            timerSession = null;

        }

       timerSession = new CountDownTimer(TimeUnit.MINUTES.toMillis(EXPIRATION_SESSION_TIME), 2000){

            public void onTick(long millisUntilFinished) { }

            public void onFinish() {
                logOut();
            }

        };

       timerSession.start();
    }

    public void onDestroy(){

        if(timerSession != null)
            timerSession.cancel();

        removeTokenSharedPreferences();

        EXPIRATION_SESSION_TIME = 0;
        INSTANCE = null;

    }

    private void logOut(){

        if(ActivityStatusHelper.isMyApplicationForeground()) {

            Intent i = new Intent(GlobalApplicationContext.getAPPCONTEXT(), LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            GlobalApplicationContext.getAPPCONTEXT().startActivity(i);

        }

        Intent brodCastIntent = new Intent();
        brodCastIntent.setAction(Utils.BROADCAST_MESSAGE);
        GlobalApplicationContext.getAPPCONTEXT().sendBroadcast(brodCastIntent);
    }
}
