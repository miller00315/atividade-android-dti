package com.example.atividade_android_dti.events.domain.data;

import android.util.Log;

import com.example.atividade_android_dti.events.domain.model.Event;
import com.example.atividade_android_dti.events.domain.model.EventsList;
import com.example.atividade_android_dti.services.ApiService;
import com.google.gson.JsonObject;

import java.util.EventListener;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EventRemoteDataSource {

    private static EventRemoteDataSource INSTANCE;

    private ApiService apiService;

    public static EventRemoteDataSource getInstance(){

        if(INSTANCE == null)
            INSTANCE = new EventRemoteDataSource();

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

    public void getEventsData(final EventsDataSource.requestEventsCallback eventsDataSource){

        Call<EventsList> request_events_data = apiService.getEvents();


        request_events_data.enqueue(new Callback<EventsList>() {
            @Override
            public void onResponse(Call<EventsList> call, Response<EventsList> response) {

                EventsList eventsList = response.body();

                if(eventsList != null)
                    eventsDataSource.onEventsRequestSuccess(eventsList);
                else
                    eventsDataSource.onEventsRequestFailed();

            }

            @Override
            public void onFailure(Call<EventsList> call, Throwable t) {
                eventsDataSource.onEventsRequestFailed();
            }
        });

    }
}
