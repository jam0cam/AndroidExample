package com.jitse.example;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.CardView;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class StateListActivity extends ActionBarActivity {

    @InjectView(R.id.card_view)
    CardView mCardView;

    private int mLowElevation;
    private int mHighElevation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_list);
        ButterKnife.inject(this);

        mLowElevation = getResources().getDimensionPixelOffset(R.dimen.low_elevation);
        mHighElevation = getResources().getDimensionPixelOffset(R.dimen.high_elevation);
    }

}
