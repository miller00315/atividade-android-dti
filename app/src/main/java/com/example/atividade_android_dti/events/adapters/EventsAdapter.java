package com.example.atividade_android_dti.events.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atividade_android_dti.R;
import com.example.atividade_android_dti.events.domain.models.EventsList;
import com.example.atividade_android_dti.events.viewHolders.EventsViewHolder;
import com.example.atividade_android_dti.utils.DateHandler;
import com.example.atividade_android_dti.utils.EventsComparator;

import java.util.Collections;

public class EventsAdapter extends RecyclerView.Adapter<EventsViewHolder> {

    private EventsList eventsList;

    public EventsAdapter(){ }


    @NonNull
    @Override
    public EventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EventsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.events_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EventsViewHolder holder, int position) {

        holder.setEventData(DateHandler.getDormatedData(eventsList.getEvents().get(position).getData()));
        holder.setEventDescription(eventsList.getEvents().get(position).getDescricao());
        holder.setEventName(eventsList.getEvents().get(position).getNome());
        holder.setEventImage(eventsList.getEvents().get(position).getRotaImagem());
        holder.setEventId(String.valueOf(eventsList.getEvents().get(position).getId()));

    }

    public void addEvents(EventsList eventsList){

        this.eventsList = eventsList;

        Collections.sort(this.eventsList.getEvents(), new EventsComparator());

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return eventsList.getEvents() != null ? eventsList.getEvents().size() : 0;
    }

}
