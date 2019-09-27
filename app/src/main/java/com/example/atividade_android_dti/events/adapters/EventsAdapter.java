package com.example.atividade_android_dti.events.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atividade_android_dti.R;
import com.example.atividade_android_dti.events.domain.model.Event;
import com.example.atividade_android_dti.events.domain.model.EventsList;
import com.example.atividade_android_dti.events.viewHolders.EventsViewHolder;
import com.example.atividade_android_dti.utils.DataManipulator;

import java.util.ArrayList;

public class EventsAdapter extends RecyclerView.Adapter<EventsViewHolder> {

    private static EventsAdapter INSTANCE;

    private ArrayList<Event> events;

    private EventsAdapter(){

        events = new ArrayList<>();
    }

    public static EventsAdapter getInstance(){

        if(INSTANCE == null)
            INSTANCE = new EventsAdapter();

        return INSTANCE;
    }

    @NonNull
    @Override
    public EventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EventsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.events_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EventsViewHolder holder, int position) {

        holder.setEvent_data(DataManipulator.getDormatedData(events.get(position).getData()));
        holder.setEvent_description(events.get(position).getDescricao());
        holder.setEvent_name(events.get(position).getNome());
        holder.setEvent_image(events.get(position).getRotaImagem());

    }

    public void addEvents(EventsList eventsList){

        events.addAll(eventsList.getEvents());
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return events != null ? events.size() : 0;
    }
}
