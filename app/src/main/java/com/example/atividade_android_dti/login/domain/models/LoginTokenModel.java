package com.example.atividade_android_dti.login.domain.models;

import com.google.gson.annotations.Expose;

public class LoginTokenModel {

    @Expose
    private String token;

    @Expose
    private String tempoExpirar;

    public LoginTokenModel(String token, String tempoExpirar) {
        this.token = token;
        this.tempoExpirar = tempoExpirar;
    }

    public String getToken() {
        return token;
    }

    public String getTempoExpirar() {
        return tempoExpirar;
    }

    public int getIntegerValueTempoExpirar(){

        return Integer.parseInt(tempoExpirar);

    }

}
