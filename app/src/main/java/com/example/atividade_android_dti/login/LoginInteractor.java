package com.example.atividade_android_dti.login;

import com.example.atividade_android_dti.login.domain.network.LoginApi;
import com.example.atividade_android_dti.login.domain.network.LoginApiInterface;
import com.example.atividade_android_dti.login.domain.models.LoginRequestModel;
import com.example.atividade_android_dti.login.domain.models.LoginTokenModel;
import com.example.atividade_android_dti.session.SessionManager;
import com.example.atividade_android_dti.utils.ConnectionCheck;
import com.example.atividade_android_dti.utils.StringsValidator;

public class LoginInteractor implements LoginApiInterface {

    private LoginContract.Presenter presenter;
    private LoginApi loginApi;

    public LoginInteractor(LoginContract.Presenter presenter) {
        this.presenter = presenter;
        loginApi = new LoginApi();
    }

    public void doLogin(String user_name, String user_password){

        if(ConnectionCheck.getInstance().isNetworkAvailable()) {

            boolean isDataOk = true;

            if (!StringsValidator.isValidUserName(user_name)) {
                isDataOk = false;
                presenter.invalidUserName();
            }


            if (!StringsValidator.isValidPassword(user_password)) {
                isDataOk = false;
                presenter.invalidPassword();
            }

            if (isDataOk)
                loginApi.doLogin(this, new LoginRequestModel(user_name, user_password));
        }else
            presenter.noInternetConnection();

    }

    private void startSession(LoginTokenModel loginTokenModel){

        SessionManager sessionManager =  SessionManager.getInstance();

        sessionManager.setLoginTokenOnPreferences(loginTokenModel);

        sessionManager.startSessionCountDown();

    }

    @Override
    public void onLoginSuccess(LoginTokenModel loginTokenModel) {

        if(loginTokenModel != null){
            startSession(loginTokenModel);
            if(presenter != null)
                presenter.onLoginSuccess(loginTokenModel);
        }else{
            if(presenter != null)
                presenter.onLoginFailed();
        }

    }

    @Override
    public void onLoginFailed() {
        if(presenter != null)
            presenter.onLoginFailed();
    }

    public void onDestroy(){
        presenter = null;
        loginApi.onDestroy();
    }

}
