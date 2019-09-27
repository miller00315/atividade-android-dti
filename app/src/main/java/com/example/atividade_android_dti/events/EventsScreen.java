package com.example.atividade_android_dti.events;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.atividade_android_dti.R;
import com.example.atividade_android_dti.events.adapters.EventsAdapter;
import com.example.atividade_android_dti.events.contract.EventsContract;
import com.example.atividade_android_dti.events.domain.model.EventsList;
import com.example.atividade_android_dti.login.LoginScreen;
import com.example.atividade_android_dti.login.contract.LoginContract;
import com.example.atividade_android_dti.utils.Utils;

public class EventsScreen extends AppCompatActivity implements EventsContract.View {

    private EventsContract.Presenter mLoginContractPresenter;
    private RecyclerView recycle_events;
    private ConstraintLayout loading_layout;
    private EventsAdapter eventsAdapter;
    private BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_screen);

        recycle_events = findViewById(R.id.recylcer_events);
        loading_layout = findViewById(R.id.layout_loading);

        eventsAdapter = EventsAdapter.getInstance();

        setReceiver();

        new EventsPresenter(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recycle_events.setHasFixedSize(true);

        recycle_events.setLayoutManager(linearLayoutManager);

        recycle_events.setAdapter(eventsAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(mReceiver);

    }

    @Override
    public void showLoading() {
        loading_layout.setVisibility(View.VISIBLE);
        recycle_events.setVisibility(View.GONE);
    }

    @Override
    public void onRequestEventsFailed() {

        Toast.makeText(this, "Erro ao obter dados", Toast.LENGTH_LONG).show();
        loading_layout.setVisibility(View.GONE);
        recycle_events.setVisibility(View.GONE);
    }

    @Override
    public void onRequestEventsSuccess(EventsList eventsList) {

        eventsAdapter.addEvents(eventsList);

        loading_layout.setVisibility(View.GONE);
        recycle_events.setVisibility(View.VISIBLE);

    }

    @Override
    public void setPresenter(EventsContract.Presenter presenter) {
        mLoginContractPresenter = presenter;

        mLoginContractPresenter.start();
    }

    public void setReceiver(){

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Utils.BROADCAST_MESSAGE);

        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

               finish();

            }
        };

        registerReceiver(mReceiver, intentFilter);
    }
}
