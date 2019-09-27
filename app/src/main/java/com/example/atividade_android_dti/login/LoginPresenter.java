package com.example.atividade_android_dti.login;


import com.example.atividade_android_dti.login.contract.LoginContract;
import com.example.atividade_android_dti.login.domain.data.LoginDataSource;
import com.example.atividade_android_dti.login.domain.data.LoginRemoteDataSource;
import com.example.atividade_android_dti.login.domain.model.LoginRequestModel;
import com.example.atividade_android_dti.login.domain.model.LoginTokenModel;
import com.example.atividade_android_dti.session.SessionManager;
import com.example.atividade_android_dti.utils.ConnectionCheck;
import com.example.atividade_android_dti.utils.StringValidator;

import static com.google.common.base.Preconditions.checkNotNull;

public class LoginPresenter implements LoginContract.Presenter, LoginDataSource.DoLoginCallback {

    private LoginContract.View mLoginContractView;
    private LoginRemoteDataSource mLoginremoteDataSource;

    public LoginPresenter(LoginContract.View mLoginContractView) {

        this.mLoginContractView = checkNotNull(mLoginContractView);

        mLoginremoteDataSource = LoginRemoteDataSource.getInstance();

        mLoginremoteDataSource.setRetrofit();

        mLoginContractView.setPresenter(this);

    }

    @Override
    public void doLogin(String user_name, String user_password) {

        boolean isDataOk = true;

        if(!StringValidator.isValidUserName(user_name)){

            mLoginContractView.isValidUserName(false);
            isDataOk = false;
        }

        if(!StringValidator.isValidPassword(user_password)){

            mLoginContractView.isValidUserPassword(false);
            isDataOk = false;
        }

        if(isDataOk)
            if(ConnectionCheck.getInstance().isNetworkAvailable()){
                mLoginContractView.showLoading();
                mLoginremoteDataSource.doLogin(this, new LoginRequestModel(user_name, user_password));

            }else
                mLoginContractView.noConnectionInternet();

    }

    @Override
    public void start() {

    }

    @Override
    public void onLoginSuccess(LoginTokenModel loginTokenModel) {

        saveToken(loginTokenModel);

        mLoginContractView.onLoginSuccess();

    }

    @Override
    public void onLoginFailed() {
        mLoginContractView.onLoginFailed();
    }

    private void saveToken(LoginTokenModel loginTokenModel){

        SessionManager preferenceManager =  SessionManager.getInstance();

        preferenceManager.saveLoginTokenOnPreferences(loginTokenModel);

        preferenceManager.startSessionCountDown();

    }
}
