package com.example.atividade_android_dti.login.domain.model;

import com.google.gson.annotations.Expose;

public class LoginResponseModel {

    @Expose
    private String token;

    @Expose
    private String tempoExpirar;

    public LoginResponseModel(String token, String tempoExpirar) {
        this.token = token;
        this.tempoExpirar = tempoExpirar;
    }

    public String getToken() {
        return token;
    }

    public String getTempoExpirar() {
        return tempoExpirar;
    }

}
