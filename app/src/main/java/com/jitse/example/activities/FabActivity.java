package com.jitse.example.activities;

import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;

import com.jitse.example.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class FabActivity extends ActionBarActivity {

    @InjectView(R.id.button)
    Button mButton;

    @InjectView(R.id.fab)
    ImageButton mFab;
    private Animation animationEnlarge;
    private Animation animationShrink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab);

        ButterKnife.inject(this);

        animationEnlarge = AnimationUtils.loadAnimation(this,
                R.anim.enlarge);
        animationShrink = AnimationUtils.loadAnimation(this,
                R.anim.shrink);

    }

    @OnClick(R.id.button)
    public void clicked() {
//        final AnimationDrawable background = (AnimationDrawable) mFab.getBackground();
//        background.start();

        final TransitionDrawable background = (TransitionDrawable) mFab.getBackground();
        background.startTransition(300);

        mFab.setImageDrawable(getResources().getDrawable(R.drawable.ic_add_cart_white));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mFab.startAnimation(animationEnlarge);
            }
        }, 400);
    }

}
