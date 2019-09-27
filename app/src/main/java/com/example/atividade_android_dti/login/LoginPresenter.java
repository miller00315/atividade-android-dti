package com.example.atividade_android_dti.login;


import com.example.atividade_android_dti.login.contract.LoginContract;
import com.example.atividade_android_dti.login.domain.data.LoginDataSource;
import com.example.atividade_android_dti.login.domain.data.LoginRemoteDataSource;
import com.example.atividade_android_dti.login.domain.model.LoginRequestModel;
import com.example.atividade_android_dti.login.domain.model.LoginResponseModel;
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

        if(isDataOk){

            mLoginContractView.showLoading();
            mLoginremoteDataSource.doLogin(this, new LoginRequestModel(user_name, user_password));

        }

    }

    @Override
    public void start() {

    }

    @Override
    public void onLoginSuccess(LoginResponseModel loginResponseModel) {
        mLoginContractView.onLoginSuccess();
    }

    @Override
    public void onLoginFailed() {
        mLoginContractView.onLoginFailed();
    }
}
