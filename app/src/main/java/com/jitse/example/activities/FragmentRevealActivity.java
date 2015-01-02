package com.jitse.example.activities;

import android.animation.Animator;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;

import com.jitse.example.R;
import com.jitse.example.fragments.ReviewsFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class FragmentRevealActivity extends ActionBarActivity {

    @InjectView(R.id.btn)
    Button mBtn;

    @InjectView(R.id.fl_reviews)
    FrameLayout mFlReviews;

    @InjectView(R.id.fl_master)
    FrameLayout mFlMaster;

    private ReviewsFragment mFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_reveal);

        ButterKnife.inject(this);

        mFragment = (ReviewsFragment) getFragmentManager().findFragmentById(R.id.frag_reviews);
    }

    @OnClick(R.id.btn)
    public void clicked() {
        revealReviews();
    }


    private void revealReviews() {

        int[] locations = new int[2];

        mBtn.getLocationOnScreen(locations);

        int centerY = locations[1] + mBtn.getHeight()/2;
        int centerX = mBtn.getWidth()/2;

        int radius = Math.max(mFlMaster.getWidth(), mFlMaster.getHeight());
        Animator anim = ViewAnimationUtils.createCircularReveal(mFlReviews, centerX, centerY, 0, radius);

        mFlReviews.setVisibility(View.VISIBLE);

        anim.setDuration(500);
        anim.start();
    }

}
