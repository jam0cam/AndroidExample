package com.jitse.example.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.jitse.example.R;
import com.jitse.example.event.BeaconEvent;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class EventBusActivity extends AppCompatActivity {
    private static final String TAG = EventBusActivity.class.getName();

    @InjectView(R.id.btn_post_event)
    Button mBtnPost;

    @InjectView(R.id.btn_receive_event)
    Button mBtnReceive;

    boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);

        ButterKnife.inject(this);
    }

    @OnClick(R.id.btn_post_event)
    public void postEvent() {
        flag = !flag;
        Log.d(TAG, "posting event:" + flag);
        EventBus.getDefault().postSticky(new BeaconEvent(flag));
    }

    @OnClick(R.id.btn_receive_event)
    public void register() {
        Log.d(TAG, "registerSticky");
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().registerSticky(this);
        }
    }


    @OnClick(R.id.btn_unregister)
    public void unRegister() {
        Log.d(TAG, "UNregisterSticky");
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }


    public void onEventMainThread(BeaconEvent event) {
        Log.d(TAG, "beacon event is present:" + event.isNearby);
        EventBus.getDefault().removeStickyEvent(event);
    }

}
