package com.jitse.example.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageButton;

import com.jitse.example.R;
import com.jitse.example.fragments.DimensionFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class DialogRevealActivity extends ActionBarActivity {

    @InjectView(R.id.fab)
    ImageButton mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_reveal);

        ButterKnife.inject(this);
    }

    @OnClick(R.id.fab)
    public void showDialogFragment() {
        DimensionFragment fragment = DimensionFragment.newInstance(mButton.getLeft(), mButton.getTop(), getResources().getColor(R.color.black));
        fragment.show(getFragmentManager(), DimensionFragment.class.getName());
    }
}
