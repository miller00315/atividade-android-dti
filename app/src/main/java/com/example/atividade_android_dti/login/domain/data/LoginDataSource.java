package com.example.atividade_android_dti.login.domain.data;

import com.example.atividade_android_dti.login.domain.model.LoginResponseModel;

public interface LoginDataSource {

    interface DoLoginCallback{

        void onLoginSuccess(LoginResponseModel loginResponseModel);
        void onLoginFailed();
    }
}
