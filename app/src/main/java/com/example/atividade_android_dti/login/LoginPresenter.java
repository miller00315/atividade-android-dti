package com.example.atividade_android_dti.login;


import android.content.Context;
import android.util.Log;

import com.example.atividade_android_dti.GlobalApplicationContext;
import com.example.atividade_android_dti.login.domain.models.LoginTokenModel;
import com.example.atividade_android_dti.utils.PermissionsHelper;

import static com.google.common.base.Preconditions.checkNotNull;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mLoginContractView;
    private LoginInteractor mLoginInteractor;

    public LoginPresenter(LoginContract.View mLoginContractView) {

        this.mLoginContractView = checkNotNull(mLoginContractView);

        mLoginInteractor = new LoginInteractor(this);

        mLoginContractView.setPresenter(this);

        checkSelfPermission();

    }

    @Override
    public void validCredentials(String username, String password) {

        if (mLoginContractView != null)
            mLoginContractView.showLoading();

        mLoginInteractor.doLogin(username, password);

    }

    public void onDestroy() {
        mLoginContractView = null;
        mLoginInteractor.onDestroy();
        PermissionsHelper.getInstance().onDestroy();
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

    @Override
    public void start() {
    }

    private void checkSelfPermission(){

        Context context = GlobalApplicationContext.getAPPCONTEXT();
        PermissionsHelper permissionsHelper = PermissionsHelper.getInstance();

       if (!permissionsHelper.checkSelfPermissions(context))
           permissionsHelper.requestSelfPermission(context);

    }

}
