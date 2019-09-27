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

            Picasso
                    .get()
                    .load(imageSource.isEmpty() ? getNoImage() : imageSource )
                    .fit()
                    .placeholder(R.drawable.ic_balloons)
                    .error(R.drawable.ic_garland)
                    .into(event_image);

    }

    private String getNoImage(){

        Context context = GlobalApplicationContext.getAPPCONTEXT();

        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + context.getResources().getResourcePackageName(R.drawable.ic_garland)
                + '/' + context.getResources().getResourceTypeName(R.drawable.ic_garland) + '/' + context.getResources().getResourceEntryName(R.drawable.ic_garland)).toString();


    }
}
