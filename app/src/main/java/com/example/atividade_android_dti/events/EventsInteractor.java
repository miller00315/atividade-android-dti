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
        else if(presenter != null)
            presenter.noConnectionInternet();
    }

    public void onDestroy(){

        presenter = null;
        eventsAPI.onDestroy();
    }

    @Override
    public void onEventsRequestSuccess(EventsList eventsList) {

        if(presenter != null)
            if(eventsList == null)
                presenter.onEventsRequestFailed();
            else
                presenter.onEventsRequestSuccess(eventsList);

    }

    @Override
    public void onEventsRequestFailed() {

        if(presenter != null)
            presenter.onEventsRequestFailed();

    }

}
