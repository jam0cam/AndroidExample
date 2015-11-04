package com.jitse.example.activities;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jitse.example.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class BluetoothActivity extends ActionBarActivity {

    private static final String TAG = BluetoothActivity.class.getName();

    private static final String ORIGINAL_BT_NAME = "original-bt-name";
    private static final int DISCOVERABLE_DURATION = 300;


    @InjectView(R.id.list_view)
    ListView mListView;

    @InjectView(R.id.button)
    Button mButton;

    @InjectView(R.id.text_view)
    TextView mTextview;

    ArrayAdapter<String> mListAdapter;
    List<String> mBluetoothMembers;
    BluetoothAdapter mBluetoothdapter;
    String mOriginalBTName;

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                Log.d(TAG, "bluetooth discovery started");
                //discovery starts, we can show progress dialog or perform other tasks
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                Log.d(TAG, "bluetooth discovery finished");
                //discovery finishes
            } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                //bluetooth device found
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String name = null == device.getName() ? device.getAddress() : device.getName();
                Log.d(TAG, "bluetooth found " + name);
                addUnique(name);
                Toast.makeText(BluetoothActivity.this, "Found device " + name, Toast.LENGTH_LONG).show();
            } else if (BluetoothAdapter.ACTION_SCAN_MODE_CHANGED.equals(action)) {
                //the current device's bluetooth mode has changed.
                int newScanMode = intent.getIntExtra(BluetoothAdapter.EXTRA_SCAN_MODE, BluetoothAdapter.SCAN_MODE_NONE);

                if (newScanMode == BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
                    Log.d(TAG, "scan mode became discoverable");
                    startTimer();
                } else {
                    Log.d(TAG, "scan mode changed to not discoverable");
                    updateText("Discoverability disabled");
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_bluetooth);

        ButterKnife.inject(this);

        mBluetoothMembers = new ArrayList<>();
        mListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mBluetoothMembers);
        mListView.setAdapter(mListAdapter);
    }

    private void addUnique(String value) {
        if (!mBluetoothMembers.contains(value)) {
            mBluetoothMembers.add(value);
            mListAdapter.notifyDataSetChanged();
        }
    }

    private void startTimer() {
        Log.d(TAG, "starting timer");
        new CountDownTimer(DISCOVERABLE_DURATION * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                //here you can have your logic to set text to edittext
                updateText("seconds remaining: " + millisUntilFinished / 1000);
            }
            public void onFinish() {
                Log.d(TAG, "timer expired");
                updateText("Discoverability disabled");
            }
        }.start();
    }

    private void updateText(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTextview.setText(text);
            }
        });
    }

    @OnClick(R.id.button)
    public void enableDiscoverability() {
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, DISCOVERABLE_DURATION);
        startActivity(discoverableIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");

        mBluetoothdapter = BluetoothAdapter.getDefaultAdapter();
        mOriginalBTName = mBluetoothdapter.getName();

        IntentFilter filter = new IntentFilter();

        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        filter.addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);
        Log.d(TAG, "Registering receiver");
        registerReceiver(mReceiver, filter);

        //make sure discovery is canceled before being started
        if (mBluetoothdapter.isDiscovering()) {
            Log.d(TAG, "canceling discovery");
            mBluetoothdapter.cancelDiscovery();
        }

        Log.d(TAG, "my current device name is:" + mOriginalBTName);

        Log.d(TAG, "starting discovery");
        mBluetoothdapter.startDiscovery();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        Log.d(TAG, "UNRegistering receiver");
        unregisterReceiver(mReceiver);

        if (mBluetoothdapter.isDiscovering()) {
            Log.d(TAG, "canceling discovery");
            mBluetoothdapter.cancelDiscovery();
        }

        //restore the original BT name
        Log.d(TAG, "restoring device name to:" + mOriginalBTName);
        mBluetoothdapter.setName(mOriginalBTName);
    }
}
