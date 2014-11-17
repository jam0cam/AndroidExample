package com.jitse.example.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jitse.example.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class AnimationActivity extends ActionBarActivity {


    private static final String TAG = AnimationActivity.class.getName();

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.inject(this);


        mImgBlue.bringToFront();
        mLlMain.invalidate();

        mLlMain.bringToFront();
        mFlMain.invalidate();

        mBtnAnimate.invalidate();
    }

    @OnClick(R.id.btn_animate)
    void onClick() {

        int [] styleLocation = new int[2];
        mImgBlue.getLocationOnScreen(styleLocation);


        int [] mainLocation = new int[2];
        mImgMain.getLocationOnScreen(mainLocation);


        Log.d(TAG, "style location x: " + styleLocation[0]);
        Log.d(TAG, "style location y: " + styleLocation[1]);

        Log.d(TAG, "main location x: " + mainLocation[0]);
        Log.d(TAG, "main location y: " + mainLocation[1]);

        Log.d(TAG, "mainTop:" + mImgMain.getTop());
        Log.d(TAG, "mainY:" + mImgMain.getY());
        Log.d(TAG, "buttonTop:" + mBtnAnimate.getTop());
        Log.d(TAG, "mainHeight:" + mImgMain.getHeight());
        Log.d(TAG, "buttonHeight:" + mBtnAnimate.getHeight());
        Log.d(TAG, "styleTop:" + mImgBlue.getTop());
        Log.d(TAG, "styleY:" + mImgBlue.getY());
        Log.d(TAG, "styleHeight:" + mImgBlue.getHeight());
        Log.d(TAG, "LLTop:" + mLlMain.getTop());
        Log.d(TAG, "LLHeight:" + mLlMain.getHeight());

        int translationY = mainLocation[1] - styleLocation[1] + (mImgMain.getHeight() - mImgBlue.getHeight())/2;
        Log.d(TAG, "translatingY: " + translationY);
        int translationX = mainLocation[0] - styleLocation[0]  + (mImgMain.getWidth() - mImgBlue.getWidth())/2;;

        Log.d(TAG, "translatingX: " + translationX);

        float scaleY = mImgMain.getHeight()/(float)mImgBlue.getHeight();
        float scaleX = mImgMain.getWidth()/(float)mImgBlue.getWidth();
        Log.d(TAG, "ScalingY: " + scaleY);

        Log.d(TAG, "=========================================");

        mImgBlue.animate().scaleY(scaleY).scaleX(scaleX).translationYBy(translationY).setDuration(2000).translationXBy(translationX).start();
    }
}
