package com.example.atividade_android_dti.events.domain.data;

import com.example.atividade_android_dti.events.domain.model.EventsList;

public interface EventsDataSource {

    interface requestEventsCallback {
        void onEventsRequestSuccess(EventsList eventsList);
        void onEventsRequestFailed();
    }
}
