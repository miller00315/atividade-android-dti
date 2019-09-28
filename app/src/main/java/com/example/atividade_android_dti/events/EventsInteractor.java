package com.example.atividade_android_dti.events;


import com.example.atividade_android_dti.events.domain.network.EventsApi;
import com.example.atividade_android_dti.events.domain.network.EventsApiInterface;
import com.example.atividade_android_dti.events.domain.models.EventsList;
import com.example.atividade_android_dti.utils.ConnectionCheck;

public class EventsInteractor implements EventsApiInterface {

    private EventsApi eventsAPI;
    private EventsContract.Presenter presenter;

    public EventsInteractor(EventsContract.Presenter presenter) {

        this.presenter = presenter;
        eventsAPI = new EventsApi();
    }

    public void resquestEventsData(){

        if(ConnectionCheck.getInstance().isNetworkAvailable())
            eventsAPI.getEventsData(this);
        else
            presenter.noConnectionInternet();
    }

    @Override
    public void onEventsRequestSuccess(EventsList eventsList) {


        if(eventsList == null)
            presenter.onEventsRequestFailed();
        else
            presenter.onEventsRequestSuccess(eventsList);

    }

    @Override
    public void onEventsRequestFailed() {

        presenter.onEventsRequestFailed();

    }
}
