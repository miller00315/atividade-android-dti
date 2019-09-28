package com.example.atividade_android_dti.events.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atividade_android_dti.GlobalApplicationContext;
import com.example.atividade_android_dti.R;
import com.example.atividade_android_dti.events.domain.models.EventsList;
import com.example.atividade_android_dti.events.viewHolders.EventsViewHolder;
import com.example.atividade_android_dti.login.LoginContract;
import com.example.atividade_android_dti.utils.DateHandler;
import com.example.atividade_android_dti.utils.EventsComparator;

import java.util.Collections;

public class EventsAdapter<P extends LoginContract.Presenter> extends RecyclerView.Adapter<EventsViewHolder> {

    private EventsList eventsList;
    private int lastPosition = -1;

    @NonNull
    @Override
    public EventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EventsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.events_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EventsViewHolder holder, int position) {

            holder.setEventData(DateHandler.getDormatedData(eventsList.getEvents().get(position).getDate()));
            holder.setEventDescription(eventsList.getEvents().get(position).getDescricao());
            holder.setEventName(eventsList.getEvents().get(position).getNome());
            holder.setEventImage(eventsList.getEvents().get(position).getRotaImagem());
            holder.setEventId(String.valueOf(eventsList.getEvents().get(position).getId()));

            setAnimation(holder.itemView, position);

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

    private void setAnimation(View viewToAnimate, int position)
    {
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(GlobalApplicationContext.getAPPCONTEXT(), android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    public void onDestroy(){
        eventsList = null;
    }

}
