package com.jitse.example.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Button;
import android.widget.Toast;

import com.jitse.example.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class AdcNotificationActivity extends ActionBarActivity {

    @InjectView(R.id.button)
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adc_notification);

        ButterKnife.inject(this);


    }

    @OnClick(R.id.button)
    public void clicked() {
        Toast.makeText(this, "clicked", Toast.LENGTH_LONG).show();
    }
}
