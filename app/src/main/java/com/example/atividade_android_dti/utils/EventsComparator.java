package com.example.atividade_android_dti.utils;

import com.example.atividade_android_dti.events.domain.models.Event;

import java.util.Comparator;

public class EventsComparator implements Comparator<Event> {
    @Override
    public int compare(Event firstEvent, Event secondEvent) {
        return firstEvent.getDate().compareTo(secondEvent.getDate());
    }
}
