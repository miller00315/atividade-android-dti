package com.example.atividade_android_dti.events;

import com.example.atividade_android_dti.events.adapters.EventsAdapter;
import com.example.atividade_android_dti.events.contract.EventsContract;
import com.example.atividade_android_dti.events.domain.data.EventRemoteDataSource;
import com.example.atividade_android_dti.events.domain.data.EventsDataSource;
import com.example.atividade_android_dti.events.domain.model.EventsList;

public class EventsPresenter implements EventsContract.Presenter, EventsDataSource.requestEventsCallback {

    private EventsContract.View mEventsContractView;
    private EventRemoteDataSource eventRemoteDataSource;


    public EventsPresenter(EventsContract.View mEventsCOntractView) {
        this.mEventsContractView = mEventsCOntractView;
        eventRemoteDataSource = EventRemoteDataSource.getInstance();

        eventRemoteDataSource.setRetrofit();

        mEventsCOntractView.setPresenter(this);



    }

    @Override
    public void start() {
        mEventsContractView.showLoading();
        eventRemoteDataSource.getEventsData(this);
    }

    @Override
    public void onEventsRequestSuccess(EventsList eventsList) {
        mEventsContractView.onRequestEventsSuccess(eventsList);
    }

    @Override
    public void onEventsRequestFailed() {
        mEventsContractView.onRequestEventsFailed();
    }
}
