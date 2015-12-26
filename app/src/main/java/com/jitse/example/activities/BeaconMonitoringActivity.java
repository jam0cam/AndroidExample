package com.jitse.example.activities;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.jitse.example.R;
import com.jitse.example.event.BeaconEvent;
import com.jitse.example.service.BeaconService;

import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.powersave.BackgroundPowerSaver;
import org.altbeacon.beacon.service.RangedBeacon;
import org.altbeacon.beacon.startup.BootstrapNotifier;
import org.altbeacon.beacon.startup.RegionBootstrap;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class BeaconMonitoringActivity extends AppCompatActivity {
    private static final String TAG = BeaconMonitoringActivity.class.getName();

    @InjectView(R.id.main_layout)
    View mMainLayout;

    int mDarkGreen;
    int mLightRed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beacon_monitoring);
        ButterKnife.inject(this);

        mDarkGreen = ContextCompat.getColor(this, R.color.dark_green);
        mLightRed = ContextCompat.getColor(this, R.color.red_light);
    }

    public void onEventMainThread(BeaconEvent event) {
        Log.d(TAG, "beacon event is present:" + event.isNearby);
        if (event.isNearby) {
            mMainLayout.setBackgroundColor(mDarkGreen);
        } else {
            mMainLayout.setBackgroundColor(mLightRed);
        }

        EventBus.getDefault().removeStickyEvent(event);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().registerSticky(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

}
