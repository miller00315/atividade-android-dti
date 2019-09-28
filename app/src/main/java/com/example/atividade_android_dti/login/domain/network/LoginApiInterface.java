package com.example.atividade_android_dti.login.domain.network;

import com.example.atividade_android_dti.login.domain.models.LoginTokenModel;

public interface LoginApiInterface {

        void onLoginSuccess(LoginTokenModel loginTokenModel);
        void onLoginFailed();

}
