package com.example.atividade_android_dti.events.contract;

import com.example.atividade_android_dti.BasePresenter;
import com.example.atividade_android_dti.BaseView;
import com.example.atividade_android_dti.events.domain.model.EventsList;

public interface EventsContract {

    interface View extends BaseView<Presenter>{

        void showLoading();
        void onRequestEventsFailed();
        void onRequestEventsSuccess(EventsList eventsList);
        void noConnectionInternet();
    }

    interface Presenter extends BasePresenter{ }

}
