package com.example.atividade_android_dti.login.domain.network;

import com.example.atividade_android_dti.login.domain.models.LoginRequestModel;
import com.example.atividade_android_dti.login.domain.models.LoginTokenModel;
import com.example.atividade_android_dti.services.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginApi {

    private ApiService apiService;

    public LoginApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

    }

    public void doLogin(final LoginApiInterface callback, LoginRequestModel loginRequestModel){

        final Call<LoginTokenModel> requestLogin = apiService.login(loginRequestModel);

        requestLogin.enqueue(new Callback<LoginTokenModel>() {
            @Override
            public void onResponse(Call<LoginTokenModel> call, Response<LoginTokenModel> response) {

                if(response.isSuccessful())
                    callback.onLoginSuccess(response.body());
                else
                    callback.onLoginFailed();

            }

            @Override
            public void onFailure(Call<LoginTokenModel> call, Throwable t) {

                callback.onLoginFailed();

            }
        });
    }

}
