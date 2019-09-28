package com.example.atividade_android_dti.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.atividade_android_dti.R;
import com.example.atividade_android_dti.events.EventsActivity;
import com.example.atividade_android_dti.utils.PermissionsHelper;
import com.example.atividade_android_dti.utils.TextWatchers;
import com.example.atividade_android_dti.utils.TextWatchersInterface;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity implements LoginContract.View, TextWatchersInterface {

    private LoginContract.Presenter loginPresenter;
    private TextWatchers textWatchers;
    private TextInputEditText userName, userPassword;
    private ConstraintLayout loadingScreen, mainLayoutLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = findViewById(R.id.user_name);
        userPassword = findViewById(R.id.user_password);
        loadingScreen = findViewById(R.id.layout_loading);
        mainLayoutLogin = findViewById(R.id.main_layout_login);

        setTextWatchers();

        setNewPresenter();

    }

    public void setTextWatchers(){

        if(textWatchers == null)
            textWatchers = TextWatchers.getInstance();

        textWatchers.setListener(this);

        userName.addTextChangedListener(textWatchers.getUserNameTextWatcher());
        userPassword.addTextChangedListener(textWatchers.getUserPasswordTextWatcher());
    }

    public void setNewPresenter(){
        if(loginPresenter == null)
            new LoginPresenter(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        loginPresenter.onDestroy();
        textWatchers.onDestroy();
    }

    @Override
    public void invalidUserName() {
        userName.setError(getResources().getString(R.string.userNameError));
    }

    @Override
    public void invalidPassword() {
        userPassword.setError(getResources().getString(R.string.userPasswordError));
    }

    public void validCredentials(View view) {
        loginPresenter.validCredentials(Objects.requireNonNull(userName.getText())
                        .toString().trim(),
                Objects.requireNonNull(userPassword.getText())
                        .toString().trim());
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        loginPresenter = presenter;
    }

    @Override
    public void isValidUserName(boolean result) {
        if(result) userName.setError(null);
        else userName.setError(getResources().getString(R.string.userNameError));
    }

    @Override
    public void isValidUserPassword(boolean result) {
        if(result) userPassword.setError(null);
        else userPassword.setError(getResources().getString(R.string.userPasswordError));
    }

    @Override
    public void onLoginFailed() {
        Toast.makeText(this, getResources().getString(R.string.loginerror), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLoginSuccess() {

        finish();

        Intent intent = new Intent(this, EventsActivity.class);
        startActivity(intent);

    }

    @Override
    public void showLoading() {
        loadingScreen.setVisibility(View.VISIBLE);
        mainLayoutLogin.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        loadingScreen.setVisibility(View.GONE);
        mainLayoutLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void noConnectionInternet() {
        Toast.makeText(this, getResources().getString(R.string.noInternetConnection), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(!PermissionsHelper.getInstance().checkSelfPermissions(this))
            PermissionsHelper.getInstance().requestSelfPermission(this);
    }
}
