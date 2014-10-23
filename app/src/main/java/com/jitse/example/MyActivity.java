package com.jitse.example;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.jitse.example.service.MyService;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MyActivity extends Activity {


    private static final String TAG = MyActivity.class.getName();

    @InjectView(R.id.btn_start_service)
    Button mBtnStartService;

    @InjectView(R.id.btn_one)
    Button mBtnOne;

    @InjectView(R.id.btn_two)
    Button mBtnTwo;

    @InjectView(R.id.tv_1)
    TextView mTv1;

    @InjectView(R.id.tv_2)
    TextView mTv2;


    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                String string = bundle.getString("word");
                String string2 = bundle.getString("word2");

                mTv1.setText(string + string2);
                mTv2.setText(string2);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        ButterKnife.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mReceiver, new IntentFilter("asdf"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
    }

    @OnClick(R.id.btn_start_service)
    public void onClick() {
        // use this to start and trigger a service
        Intent i= new Intent(this, MyService.class);

        // potentially add data to the intent
        i.putExtra("KEY1", "Value to be used by the service");
        this.startService(i);

        Log.d(TAG, "Starting service");
    }

    @OnClick(R.id.btn_one)
    public void oneClicked() {
        Intent i = new Intent(this, FrameLayoutActivity.class);
        i.putExtra("option", "1");
        startActivity(i);
    }


    @OnClick(R.id.btn_two)
    public void twoClicked() {
        Intent i = new Intent(this, FrameLayoutActivity.class);
        i.putExtra("option", "2");
        startActivity(i);
    }


}
