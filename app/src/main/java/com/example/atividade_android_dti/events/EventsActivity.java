package com.example.atividade_android_dti.events;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.atividade_android_dti.R;
import com.example.atividade_android_dti.events.adapters.EventsAdapter;
import com.example.atividade_android_dti.events.domain.models.EventsList;
import com.example.atividade_android_dti.session.SessionManager;
import com.example.atividade_android_dti.utils.ActivityStatusHelper;
import com.example.atividade_android_dti.utils.Utils;

public class EventsActivity extends AppCompatActivity implements EventsContract.View {

    private EventsContract.Presenter mLoginContractPresenter;
    private RecyclerView recycleEvents;
    private ConstraintLayout loadingLayout;
    private EventsAdapter eventsAdapter;
    private BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_screen);

        recycleEvents = findViewById(R.id.recylcer_events);
        loadingLayout = findViewById(R.id.layout_loading);

        setReceiver();

        setRecycler();

        setNewPresenter();

    }

    private void setRecycler(){

        eventsAdapter = new EventsAdapter();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recycleEvents.setHasFixedSize(true);

        recycleEvents.setLayoutManager(linearLayoutManager);

        recycleEvents.setAdapter(eventsAdapter);

    }

    public void setNewPresenter(){

        if(mLoginContractPresenter == null)
            new EventsPresenter(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(mReceiver);
        mLoginContractPresenter.onDestroy();
        eventsAdapter.onDestroy();

        if(SessionManager.getInstance() != null)
            SessionManager.getInstance().onDestroy();

    }

    @Override
    public void showLoading() {
        loadingLayout.setVisibility(View.VISIBLE);
        recycleEvents.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        loadingLayout.setVisibility(View.GONE);
        recycleEvents.setVisibility(View.VISIBLE);
    }

    @Override
    public void requestEventsData() {
        mLoginContractPresenter.requestEventsData();
    }

    @Override
    public void onEventsRequestSuccess(EventsList eventsList) {
        eventsAdapter.addEvents(eventsList);
    }

    @Override
    public void onEventsRequestFailed() {
        Toast.makeText(this, getResources().getString(R.string.requestDataFailed), Toast.LENGTH_LONG).show();
    }

    @Override
    public void noConnectionInternet() {
        Toast.makeText(this, getResources().getString(R.string.noInternetConnection), Toast.LENGTH_LONG).show();
    }

    @Override
    public void setPresenter(EventsContract.Presenter presenter) {
        mLoginContractPresenter = presenter;
        mLoginContractPresenter.requestEventsData();
    }

    public void setReceiver(){

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Utils.BROADCAST_MESSAGE);

        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                Toast.makeText(context, context.getResources().getString(R.string.expiredSession), Toast.LENGTH_LONG).show();

               finish();

            }
        };

        registerReceiver(mReceiver, intentFilter);
    }
}
