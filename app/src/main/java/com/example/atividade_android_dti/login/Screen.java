package com.example.atividade_android_dti.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.atividade_android_dti.R;
import com.example.atividade_android_dti.events.EventsScreen;
import com.example.atividade_android_dti.login.contract.LoginContract;
import com.example.atividade_android_dti.utils.PermissionsCheck;
import com.example.atividade_android_dti.utils.TextWatchers;
import com.example.atividade_android_dti.utils.TextWatchersInterface;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class Screen extends AppCompatActivity implements LoginContract.View, TextWatchersInterface {

    private LoginContract.Presenter login_presenter;
    private TextWatchers text_watchers;
    private TextInputEditText user_name, user_password;
    private PermissionsCheck permissions_check;
    private ConstraintLayout loading_screen, main_layout_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user_name           = findViewById(R.id.user_name);
        user_password       = findViewById(R.id.user_password);
        loading_screen      = findViewById(R.id.layout_loading);
        main_layout_login   = findViewById(R.id.main_layout_login);

        if(text_watchers == null)
            text_watchers = TextWatchers.getInstance();

        if(permissions_check == null)
            permissions_check = PermissionsCheck.getInstance();

        if(permissions_check.checkSelfPermissions(this))
           permissions_check.requestSelfPermission(this);

        text_watchers.setListener(this);


        user_name.addTextChangedListener(text_watchers.getUserNameTextWatcher());
        user_password.addTextChangedListener(text_watchers.getUserPasswordTextWatcher());

        new LoginPresenter(this);
    }

    @Override
    public void onDataNotAvailable() {

    }

    public void doLogin(View view) {
        login_presenter.doLogin(Objects.requireNonNull(user_name.getText()).toString().trim(),
                Objects.requireNonNull(user_password.getText()).toString().trim()) ;
    }


    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        login_presenter = presenter;
    }

    @Override
    public void isValidUserName(boolean result) {
        if(result) user_name.setError(null);
        else user_name.setError(getResources().getString(R.string.userNameError));
    }

    @Override
    public void isValidUserPassword(boolean result) {
        if(result) user_password.setError(null);
        else user_password.setError(getResources().getString(R.string.userPasswordError));
    }

    @Override
    public void onLoginFailed() {
        loading_screen.setVisibility(View.GONE);
        main_layout_login.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoginSuccess() {

        Intent intent = new Intent(this, EventsScreen.class);
        startActivity(intent);

        loading_screen.setVisibility(View.GONE);
        main_layout_login.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoading() {
        loading_screen.setVisibility(View.VISIBLE);
        main_layout_login.setVisibility(View.GONE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(!permissions_check.checkSelfPermissions(this)){
            permissions_check.requestSelfPermission(this);
        }
    }
}
