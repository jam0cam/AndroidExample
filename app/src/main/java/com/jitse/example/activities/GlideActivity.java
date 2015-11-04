package com.jitse.example.activities;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.jitse.example.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class GlideActivity extends ActionBarActivity {

    @InjectView(R.id.image_view)
    ImageView mImageView;

    String url = "http://mobile.zappos.com/sites/mobiledrupal.zappos.net/files/promos/03_3c5829.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);

        ButterKnife.inject(this);

        mImageView.setBackgroundResource(R.drawable.spinner_animation);
        AnimationDrawable rocketAnimation = (AnimationDrawable) mImageView.getBackground();
        rocketAnimation.start();


        Glide.with(this)
                .load(url)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        mImageView.setBackgroundResource(0);
                        return false;
                    }
                })
                .into(mImageView);

    }
}
