package com.example.atividade_android_dti.login;

import com.example.atividade_android_dti.login.domain.network.LoginApi;
import com.example.atividade_android_dti.login.domain.network.LoginApiInterface;
import com.example.atividade_android_dti.login.domain.models.LoginRequestModel;
import com.example.atividade_android_dti.login.domain.models.LoginTokenModel;
import com.example.atividade_android_dti.session.SessionManager;
import com.example.atividade_android_dti.utils.ConnectionCheck;
import com.example.atividade_android_dti.utils.StringsValidator;

public class LoginInteractor implements LoginApiInterface {

    private LoginContract.Presenter listener;
    private LoginApi loginApi;

    public LoginInteractor(LoginContract.Presenter listener) {
        this.listener = listener;
        loginApi = new LoginApi();
    }

    public void doLogin(String user_name, String user_password){

        if(ConnectionCheck.getInstance().isNetworkAvailable()) {

            boolean isDataOk = true;

            if (!StringsValidator.isValidUserName(user_name)) {
                isDataOk = false;
                listener.invalidUserName();
            }


            if (!StringsValidator.isValidPassword(user_password)) {
                isDataOk = false;
                listener.invalidPassword();
            }

            if (isDataOk)
                loginApi.doLogin(this, new LoginRequestModel(user_name, user_password));
        }else
            listener.noInternetConnection();

    }

    private void setToken(LoginTokenModel loginTokenModel){

        SessionManager sessionManager =  SessionManager.getInstance();

        sessionManager.setLoginTokenOnPreferences(loginTokenModel);

        sessionManager.startSessionCountDown();

    }

    @Override
    public void onLoginSuccess(LoginTokenModel loginTokenModel) {

        if(loginTokenModel != null){
            setToken(loginTokenModel);
            if(listener != null)
                listener.onLoginSuccess(loginTokenModel);
        }else{
            if(listener != null)
                listener.onLoginFailed();
        }

    }

    @Override
    public void onLoginFailed() {
        if(listener != null)
            listener.onLoginFailed();
    }

    public void onDestroy(){
        listener = null;
        loginApi.onDestroy();
    }

}