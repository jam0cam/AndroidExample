package com.jitse.example.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.jitse.example.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class DialogFragmentActivity extends Activity implements FrameFragment.Listener{

    private static final String TAG = DialogFragmentActivity.class.getName();

    @InjectView(R.id.fab)
    ImageButton mFab;

    @InjectView(R.id.fl_main)
    FrameLayout mFlMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_fragment);

        ButterKnife.inject(this);

        Log.d(TAG, "left: " + mFlMain.getLeft() + ", top: " + mFlMain.getTop() +
                ", right: " + mFlMain.getRight() + ", bottom: " + mFlMain.getBottom());

    }

    @OnClick(R.id.fab)
    public void clicked() {
        FrameFragment f = new FrameFragment();
        f.show(getFragmentManager(), FrameFragment.class.getName());

        Log.d(TAG, "left: " + mFlMain.getLeft() + ", top: " + mFlMain.getTop() +
                ", right: " + mFlMain.getRight() + ", bottom: " + mFlMain.getBottom());

        revealOut(mFab);
    }

    private void animateRevealIn(View myView) {

        int cx = myView.getWidth()/2;
        int cy = myView.getHeight()/2;

        // get the final radius for the clipping circle
        int finalRadius = myView.getWidth()/2;

        // create the animator for this view (the start radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);

        // make the view visible and start the animation
        myView.setVisibility(View.VISIBLE);
        anim.start();
    }


    private void revealOut(View myView) {

        int cx = myView.getWidth()/2;
        int cy = myView.getHeight()/2;

        // get the initial radius for the clipping circle
        int initialRadius = myView.getWidth()/2;

        Log.d(TAG, "CenterX: " + cx + "   CenterY: " + cy);

        // create the animation (the final radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, initialRadius, 0);

        // make the view invisible when the animation is done
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                myView.setVisibility(View.INVISIBLE);
            }
        });

        anim.start();
    }

    @Override
    public void dismissed() {
        animateRevealIn(mFab);
    }
}
