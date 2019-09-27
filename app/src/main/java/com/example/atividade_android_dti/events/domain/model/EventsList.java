package com.example.atividade_android_dti.events.domain.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventsList {

    @SerializedName("eventos")
    private List<Event> events;

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
