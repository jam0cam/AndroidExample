package com.jitse.example.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Button;

import com.jitse.example.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class TestActivity extends ActionBarActivity {

    @InjectView(R.id.button)
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.button)
    public void clicked() {
        Intent intent = new Intent(this, ImmersiveActivity.class);
        startActivity(intent);
    }

}
