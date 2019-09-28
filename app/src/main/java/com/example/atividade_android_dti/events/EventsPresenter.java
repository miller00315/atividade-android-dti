package com.example.atividade_android_dti.events;

import com.example.atividade_android_dti.events.domain.models.EventsList;

public class EventsPresenter implements EventsContract.Presenter {

    private EventsContract.View mEventsContractView;
    private EventsInteractor eventsInteractor;

    public EventsPresenter(EventsContract.View mEventsCOntractView) {

        this.mEventsContractView = mEventsCOntractView;

        eventsInteractor = new EventsInteractor(this);

        mEventsCOntractView.setPresenter(this);

        requestEventsData();

    }

    @Override
    public void start() { }

    @Override
    public void requestEventsData() {

        if(mEventsContractView != null)
            mEventsContractView.showLoading();

        eventsInteractor.resquestEventsData();
    }

    @Override
    public void onEventsRequestSuccess(EventsList eventsList) {
        if(mEventsContractView != null) {
            mEventsContractView.onEventsRequestSuccess(eventsList);
            mEventsContractView.hideLoading();
        }
    }

    @Override
    public void onEventsRequestFailed() {
        if(mEventsContractView != null) {
            mEventsContractView.onEventsRequestFailed();
            mEventsContractView.hideLoading();
        }
    }

    @Override
    public void noConnectionInternet() {
        if(mEventsContractView != null) {
            mEventsContractView.noConnectionInternet();
            mEventsContractView.hideLoading();
        }
    }

    @Override
    public void onDestroy(){

        mEventsContractView = null;
    }

}
