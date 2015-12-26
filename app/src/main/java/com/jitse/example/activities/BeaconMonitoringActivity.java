package com.jitse.example.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jitse.example.R;
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
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class BeaconMonitoringActivity extends AppCompatActivity {
    private static final String TAG = BeaconMonitoringActivity.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beacon_monitoring);
        ButterKnife.inject(this);


    }

    @Override
    protected void onResume() {
        super.onResume();

        EventBus.getDefault().register(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
