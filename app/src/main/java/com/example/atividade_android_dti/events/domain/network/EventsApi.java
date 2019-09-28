package com.example.atividade_android_dti.events.domain.network;

import androidx.annotation.NonNull;

import com.example.atividade_android_dti.events.domain.models.EventsList;
import com.example.atividade_android_dti.services.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EventsApi {

    private ApiService apiService;

    public EventsApi() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public void getEventsData(final EventsApiInterface eventsApiInterface){

        if(apiService != null) {

            Call<EventsList> request_events_data = apiService.getEvents();


            request_events_data.enqueue(new Callback<EventsList>() {
                @Override
                public void onResponse(@NonNull Call<EventsList> call, @NonNull Response<EventsList> response) {

                    if (response.isSuccessful())
                        eventsApiInterface.onEventsRequestSuccess(response.body());
                    else
                        eventsApiInterface.onEventsRequestFailed();

                }

                @Override
                public void onFailure(@NonNull Call<EventsList> call, @NonNull  Throwable t) {
                    eventsApiInterface.onEventsRequestFailed();
                    t.printStackTrace();
                }
            });
        }

    }

    public void onDestroy(){
        apiService = null;
    }
}
