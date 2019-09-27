package com.example.atividade_android_dti.events.viewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atividade_android_dti.R;
import com.squareup.picasso.Picasso;

public class EventsViewHolder extends RecyclerView.ViewHolder {

    private TextView event_data, event_description, event_name;
    private ImageView event_image;

    public EventsViewHolder(@NonNull View itemView) {
        super(itemView);

        event_data          = itemView.findViewById(R.id.event_data);
        event_description   = itemView.findViewById(R.id.event_description);
        event_name          = itemView.findViewById(R.id.event_name);
        event_image         = itemView.findViewById(R.id.event_image);
    }

    public void setEvent_data(String data) {
         event_data.setText(data);
    }

    public void setEvent_description(String description) {
        event_description.setText(description);
    }

    public void setEvent_name(String name) {
        event_name.setText(name);
    }

    public void setEvent_image(String imageSource) {

        if(!imageSource.isEmpty())
            Picasso
                    .get()
                    .load(imageSource)
                    .placeholder(R.drawable.progress_animation)
                    .error(R.drawable.ic_balloons)
                    .into(event_image);
    }
}
