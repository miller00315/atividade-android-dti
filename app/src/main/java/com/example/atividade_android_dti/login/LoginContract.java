package com.example.atividade_android_dti.login;

import android.app.Activity;

import com.example.atividade_android_dti.BasePresenter;
import com.example.atividade_android_dti.BaseView;
import com.example.atividade_android_dti.login.domain.models.LoginTokenModel;

public interface LoginContract {

    interface View extends BaseView<Presenter>{

        void invalidUserName();

        void invalidPassword();

        void onLoginFailed();

        void onLoginSuccess();

        void showLoading();

        void hideLoading();

        void noConnectionInternet();

    }

    interface Presenter extends BasePresenter{

        void onLoginFailed();

        void onLoginSuccess(LoginTokenModel loginTokenModel);

        void invalidUserName();

        void invalidPassword();

        void noInternetConnection();

        void validCredentials(String username, String password);

        void checkSelfPermission(Activity act);

        void onDestroy();
    }

}
