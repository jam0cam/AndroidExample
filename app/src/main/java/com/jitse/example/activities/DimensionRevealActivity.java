package com.jitse.example.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.jitse.example.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class DimensionRevealActivity extends ActionBarActivity {

    @InjectView(R.id.fab)
    ImageButton mFab;

    @InjectView(R.id.fab_clear)
    ImageButton mFabClear;

    @InjectView(R.id.fl_root)
    FrameLayout mFlRoot;

    @InjectView(R.id.fl_dimension)
    FrameLayout mFlMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dimension_reveal);

        ButterKnife.inject(this);
    }

    @OnClick(R.id.fab)
    public void clicked() {
        animateRevealIn(mFlMain);
//        translateTopLeft(mFlMain, mFab);
        revealOut(mFab);
    }

    @OnClick(R.id.fab_clear)
    public void clear() {
        animateRevealIn(mFab);
        revealOut(mFlMain);
    }

    private void translateTopLeft(View view, View startReferenceView) {
        TranslateAnimation animation = new TranslateAnimation(startReferenceView.getRight(), 0, startReferenceView.getBottom(), 0);
        animation.setDuration(500);
        animation.setFillAfter(true);
        view.startAnimation(animation);
    }

    private void animateRevealIn(View myView) {

        // get the center for the clipping circle
        int cx = (myView.getLeft() + myView.getRight()) / 2;
        int cy = (myView.getTop() + myView.getBottom()) / 2;

        // get the final radius for the clipping circle
        int finalRadius = Math.max(myView.getWidth(), myView.getHeight());

        // create the animator for this view (the start radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);

        // make the view visible and start the animation
        myView.setVisibility(View.VISIBLE);
//        anim.setDuration(1500);
        anim.start();
    }

    private void revealOut(View myView) {
        // get the center for the clipping circle
        int cx = (myView.getLeft() + myView.getRight()) / 2;
        int cy = (myView.getTop() + myView.getBottom()) / 2;

        // get the initial radius for the clipping circle
        int initialRadius = myView.getWidth()/2;

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

        // start the animation
//        anim.setDuration(1500);
        anim.start();
    }
}
