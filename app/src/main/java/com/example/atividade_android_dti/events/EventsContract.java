package com.example.atividade_android_dti.events;

import com.example.atividade_android_dti.BasePresenter;
import com.example.atividade_android_dti.BaseView;
import com.example.atividade_android_dti.events.domain.models.EventsList;

public interface EventsContract {

    interface View extends BaseView<Presenter>{

        void showLoading();
        void hideLoading();
        void requestEventsData();
        void onEventsRequestSuccess(EventsList eventsList);
        void onEventsRequestFailed();
        void noConnectionInternet();
    }

    interface Presenter extends BasePresenter{

        void requestEventsData();
        void onEventsRequestSuccess(EventsList eventsList);
        void onEventsRequestFailed();
        void noConnectionInternet();
        void onDestroy();
    }

}
