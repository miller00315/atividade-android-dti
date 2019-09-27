package com.example.atividade_android_dti.login.contract;

import com.example.atividade_android_dti.BasePresenter;
import com.example.atividade_android_dti.BaseView;

public interface LoginContract {

    interface View extends BaseView<Presenter>{

        void onDataNotAvailable();
        void isValidUserName(boolean result);
        void isValidUserPassword(boolean result);
        void onLoginFailed();
        void onLoginSuccess();
        void showLoading();

    }

    interface Presenter extends BasePresenter{
        void doLogin(String user_name, String user_password);
    }

}
