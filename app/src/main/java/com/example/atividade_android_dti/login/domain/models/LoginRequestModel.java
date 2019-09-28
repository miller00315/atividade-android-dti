package com.example.atividade_android_dti.login.domain.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequestModel {

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("pass")
    @Expose
    private String pass;

    public LoginRequestModel(String username, String pass) {
        this.username = username;
        this.pass = pass;
    }

    public String getUsername() {
        return username;
    }

    public String getPass() {
        return pass;
    }
}
