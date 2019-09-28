package com.example.atividade_android_dti.events.viewHolders;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atividade_android_dti.GlobalApplicationContext;
import com.example.atividade_android_dti.R;
import com.squareup.picasso.Picasso;

public class EventsViewHolder extends RecyclerView.ViewHolder {

    private TextView eventData, eventDescription, eventName, eventId;
    private ImageView eventImage;

    public EventsViewHolder(@NonNull View itemView) {
        super(itemView);

        eventData           = itemView.findViewById(R.id.event_data);
        eventDescription    = itemView.findViewById(R.id.event_description);
        eventName           = itemView.findViewById(R.id.event_name);
        eventImage          = itemView.findViewById(R.id.event_image);
        eventId             = itemView.findViewById(R.id.event_id);

    }

    public void setEventId(String eventId) {
        this.eventId.setText(eventId);
    }

    public void setEventData(String data) {
         eventData.setText(data);
    }

    public void setEventDescription(String description) {
        eventDescription.setText(description);
    }

    public void setEventName(String name) {
        eventName.setText(name);
    }

    public void setEventImage(String imageSource) {

            Picasso
                    .get()
                    .load(imageSource.isEmpty() ? getIconToNoImage() : imageSource )
                    .fit()
                    .placeholder(R.drawable.ic_balloons)
                    .error(R.drawable.ic_garland)
                    .into(eventImage);

    }

    private String getIconToNoImage(){

        Context context = GlobalApplicationContext.getAPPCONTEXT();

        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + context.getResources().getResourcePackageName(R.drawable.ic_garland)
                + '/' + context.getResources().getResourceTypeName(R.drawable.ic_garland) + '/' + context.getResources().getResourceEntryName(R.drawable.ic_garland)).toString();


    }
}
