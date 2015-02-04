package com.jitse.example.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.jitse.example.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class SceneTransitionActivity extends ActionBarActivity {

    private static final String TAG = SceneTransitionActivity.class.getName();
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
        setContentView(R.layout.activity_scene_transition);
        ButterKnife.inject(this);

        String something = getIntent().getStringExtra("something");

        if ("hello".equals(something)) {
            mImageView.setImageDrawable(getResources().getDrawable(R.drawable.adidas));
            mThumbnail.setImageBitmap(null);
        }

        Log.d(TAG, "onCreate Finish");
    }

    @OnClick(R.id.button)
    public void clicked(){
        Log.d(TAG, "onClicked");
        Intent intent = new Intent(this, SceneTransitionActivity.class);

        intent.putExtra("something","hello");

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, mThumbnail, "image");
        ActivityCompat.startActivity(this, intent, options.toBundle());
        Log.d(TAG, "onClick finished");
    }

}
