package com.example.atividade_android_dti.events.domain.network;

import com.example.atividade_android_dti.events.domain.models.EventsList;

public interface EventsApiInterface {
        void onEventsRequestSuccess(EventsList eventsList);
        void onEventsRequestFailed();

}
