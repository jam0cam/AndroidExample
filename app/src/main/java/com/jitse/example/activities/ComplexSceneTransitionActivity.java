package com.jitse.example.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.jitse.example.R;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ComplexSceneTransitionActivity extends ActionBarActivity {

    private static final String TAG = ComplexSceneTransitionActivity.class.getName();
    @InjectView(R.id.button)
    Button mButton;

    @InjectView(R.id.image_view)
    ImageView mImageView;

    @InjectView(R.id.thumbnail)
    ImageView mThumbnail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complex_scene_transition);
        ButterKnife.inject(this);

        String url = getIntent().getStringExtra("something");

        if (!TextUtils.isEmpty(url)) {
            Picasso.with(this)
                    .load("http://a1.zassets.com/images/z/3/0/2/7/2/8/3027286-p-MULTIVIEW.jpg")
                    .into(mImageView);
            mThumbnail.setImageBitmap(null);
        } else {
            initThumbnail();
        }

        Log.d(TAG, "onCreate Finish");
    }

    public void initThumbnail() {
        Log.d(TAG, "loadin from picasso");
        Picasso.with(this)
                .load("http://a1.zassets.com/images/z/3/0/2/7/2/8/3027286-p-MULTIVIEW.jpg")
                .into(mThumbnail);
    }

    @OnClick(R.id.button)
    public void clicked(){
        Log.d(TAG, "onClicked");
        Intent intent = new Intent(this, ComplexSceneTransitionActivity.class);

        intent.putExtra("something", "http://a1.zassets.com/images/z/3/0/2/7/2/8/3027286-p-MULTIVIEW.jpg");

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, mThumbnail, "image");
        ActivityCompat.startActivity(this, intent, options.toBundle());
        Log.d(TAG, "onClick finished");
    }
}
