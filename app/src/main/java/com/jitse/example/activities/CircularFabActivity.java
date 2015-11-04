package com.jitse.example.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.jitse.example.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class CircularFabActivity extends ActionBarActivity {

    @InjectView(R.id.image_button)
    ImageButton mImageButton;

    @InjectView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @InjectView(R.id.button)
    Button mButton;

    @InjectView(R.id.btn_change)
    Button mBtnChange;

    @InjectView(R.id.frame_layout)
    FrameLayout mFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_fab);

        ButterKnife.inject(this);
    }

    @OnClick(R.id.btn_change)
    public void changeColor() {
        Rect bounds = mProgressBar.getIndeterminateDrawable().getBounds();
        mProgressBar.setIndeterminateDrawable(getResources().getDrawable(R.drawable.ring_progress));
        mProgressBar.getIndeterminateDrawable().setBounds(bounds);
        mProgressBar.setIndeterminate(false);
        mProgressBar.setIndeterminate(true);
    }

    @OnClick(R.id.button)
    public void animateFinish() {
        mImageButton.animate().alpha(0).setDuration(250).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mImageButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_white));
                mImageButton.animate().alpha(1).setDuration(250).start();
            }
        }).start();

        mProgressBar.animate().alpha(0).setDuration(250).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mProgressBar.setVisibility(View.GONE);
                mProgressBar.setAlpha(1);
            }
        }).start();
    }

}
