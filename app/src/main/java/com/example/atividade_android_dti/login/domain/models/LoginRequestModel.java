package com.example.atividade_android_dti.login.domain.models;

import com.google.gson.annotations.Expose;

public class LoginRequestModel {

    @Expose
    private String username;

    @Expose
    private String pass;

    public LoginRequestModel(String username, String pass) {
        this.username = username;
        this.pass = pass;
    }

}
