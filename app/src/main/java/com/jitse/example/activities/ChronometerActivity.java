package com.jitse.example.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Chronometer;

import com.jitse.example.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ChronometerActivity extends AppCompatActivity {

    @InjectView(R.id.meter)
    Chronometer mMeter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chronometer);
        ButterKnife.inject(this);

        mMeter.start();
    }
}
