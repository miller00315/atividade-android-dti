package com.example.atividade_android_dti.services;

import com.example.atividade_android_dti.events.domain.model.EventsList;
import com.example.atividade_android_dti.login.domain.model.LoginRequestModel;
import com.example.atividade_android_dti.login.domain.model.LoginTokenModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    public static String BASE_URL = "https://testapi.io/api/dti-triforce-trib/";

    @POST("login")
    Call<LoginTokenModel> login(@Body LoginRequestModel loginRequestModel);

    @GET("eventos-mensais")
    Call<EventsList> getEvents();

}
