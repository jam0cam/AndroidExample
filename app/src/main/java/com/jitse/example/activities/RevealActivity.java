package com.jitse.example.activities;

import android.animation.Animator;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jitse.example.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class RevealActivity extends ActionBarActivity {

    private static final String TAG = RevealActivity.class.getName();

    @InjectView(R.id.img_main)
    ImageView mImgMain;

    @InjectView(R.id.img_blue)
    ImageView mImgBlue;

    @InjectView(R.id.ll_main)
    LinearLayout mLlMain;

    @InjectView(R.id.fl_main)
    FrameLayout mFlMain;

    @InjectView(R.id.btn_animate)
    Button mBtnAnimate;

    boolean mIsOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal);
        ButterKnife.inject(this);


        mImgBlue.bringToFront();
        mLlMain.invalidate();

        mLlMain.bringToFront();
        mFlMain.invalidate();

        mBtnAnimate.invalidate();
    }

    @OnClick(R.id.btn_animate)
    public void onClick() {

// get the center for the clipping circle
        int cx = (mImgMain.getLeft() + mImgMain.getRight()) / 2;
        int cy = (mImgMain.getTop() + mImgMain.getBottom()) / 2;

// get the final radius for the clipping circle
        int finalRadius = Math.max(mImgMain.getWidth(), mImgMain.getHeight());

// create the animator for this view (the start radius is zero)
        Animator anim =
                ViewAnimationUtils.createCircularReveal(mImgMain, cx, cy, 0, finalRadius);

// make the view visible and start the animation

        if (mIsOn) {
            mImgMain.setImageDrawable(getDrawable(R.drawable.royal_blue));
            mIsOn = false;
        } else {
            mImgMain.setImageDrawable(getDrawable(R.drawable.orange));
            mIsOn = true;
        }

        anim.start();
    }
}
