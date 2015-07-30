package com.jitse.example.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;

import com.jitse.example.R;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PicassoActivity extends ActionBarActivity {

    @InjectView(R.id.image_view)
    ImageView mImageView;

    String url = "http://mobile.zappos.com/sites/mobiledrupal.zappos.net/files/promos/03_3c5829.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso);

        ButterKnife.inject(this);

        Picasso.with(this)
                .load(url)
                .placeholder(R.drawable.spinner_animation)
                .into(mImageView);

    }

}
