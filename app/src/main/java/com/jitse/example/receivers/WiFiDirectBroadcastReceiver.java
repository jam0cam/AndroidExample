package com.jitse.example.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;

import com.jitse.example.activities.P2pNsdActivity;

/**
 * Created by jitse on 7/30/15.
 */
public class WiFiDirectBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = WiFiDirectBroadcastReceiver.class.getName();

    private WifiP2pManager mManager;
    private WifiP2pManager.Channel mChannel;
    private P2pNsdActivity mActivity;

    public WiFiDirectBroadcastReceiver(WifiP2pManager manager, WifiP2pManager.Channel channel,
                                       P2pNsdActivity activity) {
        super();
        this.mManager = manager;
        this.mChannel = channel;
        this.mActivity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive");
        String action = intent.getAction();
        Log.d(TAG, action);
        if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {

            if (mManager == null) {
                Log.d(TAG, "manager is null, returning");
                return;
            }

            NetworkInfo networkInfo = (NetworkInfo) intent
                    .getParcelableExtra(WifiP2pManager.EXTRA_NETWORK_INFO);

            if (networkInfo.isConnected()) {
                Log.d(TAG, "it's connected");
                // we are connected with the other device, request connection
                // info to find group owner IP
                Log.d(TAG,
                        "Connected to p2p network. Requesting network details");
                mManager.requestConnectionInfo(mChannel,
                        (WifiP2pManager.ConnectionInfoListener) mActivity);
            } else {
                Log.d(TAG, "it's disconnected");
                // It's a disconnect
            }
        } else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION
                .equals(action)) {

            WifiP2pDevice device = (WifiP2pDevice) intent
                    .getParcelableExtra(WifiP2pManager.EXTRA_WIFI_P2P_DEVICE);
            Log.d(TAG, "Device status:" + device.status);

        }
    }
}