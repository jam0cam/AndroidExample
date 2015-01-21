package com.jitse.example.activities;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.jitse.example.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SpinnerActivity extends ActionBarActivity {

    @InjectView(R.id.fl_main)
    FrameLayout mFlMain;

    @InjectView(R.id.image_view)
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        ButterKnife.inject(this);

        ((AnimationDrawable)img.getDrawable()).start();
    }

}
