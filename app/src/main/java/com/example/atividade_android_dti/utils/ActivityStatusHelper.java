package com.example.atividade_android_dti.utils;

import android.app.ActivityManager;

public class ActivityStatusHelper {

    public static boolean isMyApplicationForeground(){

        ActivityManager.RunningAppProcessInfo myProcess = new ActivityManager.RunningAppProcessInfo();
        ActivityManager.getMyMemoryState(myProcess);

        return myProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND;

    }

}
