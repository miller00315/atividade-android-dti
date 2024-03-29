package com.example.atividade_android_dti.login;

import android.app.Activity;
import android.content.Context;

import com.example.atividade_android_dti.GlobalApplicationContext;
import com.example.atividade_android_dti.login.domain.models.LoginTokenModel;
import com.example.atividade_android_dti.utils.PermissionsHelper;


public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mLoginContractView;
    private LoginInteractor mLoginInteractor;

    public LoginPresenter(LoginContract.View mLoginContractView) {

        this.mLoginContractView = mLoginContractView;

        mLoginInteractor = new LoginInteractor(this);

        mLoginContractView.setPresenter(this);

    }

    @Override
    public void validCredentials(String username, String password) {

        if (mLoginContractView != null)
            mLoginContractView.showLoading();

        mLoginInteractor.doLogin(username, password);

    }

    @Override
    public void checkSelfPermission(Activity act) {
        if (!PermissionsHelper.checkSelfPermissions(act))
            PermissionsHelper.requestSelfPermission(act);
    }

    public void onDestroy() {
        mLoginContractView = null;
        mLoginInteractor.onDestroy();
    }

    @Override
    public void onLoginFailed() {
        if (mLoginContractView != null) {
            mLoginContractView.hideLoading();
            mLoginContractView.onLoginFailed();
        }
    }

    @Override
    public void onLoginSuccess(LoginTokenModel loginTokenModel) {
        if (mLoginContractView != null) {
            mLoginContractView.onLoginSuccess();
        }
    }

    @Override
    public void invalidUserName() {
        if (mLoginContractView != null) {
            mLoginContractView.hideLoading();
            mLoginContractView.invalidUserName();
        }
    }

    @Override
    public void invalidPassword() {
        if (mLoginContractView != null) {
            mLoginContractView.hideLoading();
            mLoginContractView.invalidPassword();
        }
    }

    @Override
    public void noInternetConnection() {
        if (mLoginContractView != null) {
            mLoginContractView.hideLoading();
            mLoginContractView.noConnectionInternet();
        }
    }

}
