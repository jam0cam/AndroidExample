package com.jitse.example.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.jitse.example.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class BackgroundActivity extends ActionBarActivity {

    private static final String TAG = BackgroundActivity.class.getName();

    @InjectView(R.id.button)
    Button mButton;

    @InjectView(R.id.text_view)
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background);

        ButterKnife.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @OnClick(R.id.button)
    public void clicked() {
        Log.d(TAG, "Button Clicked");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                updateText("Timer has finished running");
            }
        }, 5000);
    }

    public void updateText(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "Updating text field");
                mTextView.setText(message);
            }
        });
    }
}
