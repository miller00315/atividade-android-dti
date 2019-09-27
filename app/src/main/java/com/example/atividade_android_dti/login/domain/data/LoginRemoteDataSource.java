package com.example.atividade_android_dti.login.domain.data;

import com.example.atividade_android_dti.login.domain.model.LoginRequestModel;
import com.example.atividade_android_dti.login.domain.model.LoginTokenModel;
import com.example.atividade_android_dti.services.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginRemoteDataSource {

    private static LoginRemoteDataSource INSTANCE;

    private ApiService apiService;

    public static LoginRemoteDataSource getInstance(){

        if(INSTANCE == null){

            INSTANCE = new LoginRemoteDataSource();
        }

        return INSTANCE;
    }

    public void setRetrofit(){

        if(apiService == null){

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiService = retrofit.create(ApiService.class);

        }
    }

    /**
     *
     * @param callback
     * @param loginRequestModel
     */

    public void doLogin(final LoginDataSource.DoLoginCallback callback, LoginRequestModel loginRequestModel){


        final Call<LoginTokenModel> requestLogin = apiService.login(loginRequestModel);

        requestLogin.enqueue(new Callback<LoginTokenModel>() {
            @Override
            public void onResponse(Call<LoginTokenModel> call, Response<LoginTokenModel> response) {

                if(response.isSuccessful()){

                    LoginTokenModel resultResponse = response.body();

                    if(resultResponse != null){

                        callback.onLoginSuccess(resultResponse);

                    }else{

                        callback.onLoginFailed();
                    }

                }

            }

            @Override
            public void onFailure(Call<LoginTokenModel> call, Throwable t) {

                callback.onLoginFailed();

            }
        });
    }

}
