package com.example.atividade_android_dti.login.domain.data;

import com.example.atividade_android_dti.login.domain.model.LoginTokenModel;

public interface LoginDataSource {

    interface DoLoginCallback{

        void onLoginSuccess(LoginTokenModel loginTokenModel);
        void onLoginFailed();
    }
}
