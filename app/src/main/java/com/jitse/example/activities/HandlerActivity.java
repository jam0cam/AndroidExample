package com.jitse.example.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.jitse.example.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class HandlerActivity extends AppCompatActivity {
    private static final String TAG = HandlerActivity.class.getName();

    @InjectView(R.id.txt1)
    TextView mTxt1;

    @InjectView(R.id.txt2)
    TextView mTxt2;

    int ctr1;
    int ctr2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        ButterKnife.inject(this);
        ctr1 = 0;
        ctr2 = 0;
        startTimer1();
        startTimer2();
    }

    private void startTimer1() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mTxt1.setText(String.valueOf(ctr1++));
                startTimer1();
            }
        }, 1000);
    }

    private void startTimer2() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mTxt2.setText(String.valueOf(ctr2++));
                            }
                        });
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Log.e(TAG, e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        });

        thread.start();
    }

}
