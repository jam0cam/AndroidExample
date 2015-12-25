package com.jitse.example.activities;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jitse.example.R;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ImageActivity extends AppCompatActivity {

    private static final String TAG = ImageActivity.class.getName();

    @InjectView(R.id.image_view1)
    ImageView mImageView1;

    @InjectView(R.id.image_view2)
    ImageView mImageView2;

    String mUrl = "http://www.6pm.com/images/z/3/4/8/8/1/4/3488140-p-MULTIVIEW.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ButterKnife.inject(this);

        Picasso.with(this)
                .load(mUrl)
                .into(mImageView1);

        Glide.with(this)
                .load(mUrl)
                .into(mImageView2);


    }
}
