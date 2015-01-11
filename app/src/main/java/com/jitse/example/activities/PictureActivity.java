package com.jitse.example.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;

import com.jitse.example.ExampleApplication;
import com.jitse.example.R;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PictureActivity extends ActionBarActivity {

    @InjectView(R.id.image_view)
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        ButterKnife.inject(this);
        mImageView.setTransitionName("transition_image");
//
//
//        byte[] bytes = getIntent().getByteArrayExtra("bitmap");
//        Bitmap imageBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//
//        mImageView.setImageBitmap(imageBitmap);

        mImageView.setImageBitmap(((ExampleApplication)getApplication()).mBitmap);

        Uri uri = getIntent().getParcelableExtra("file");

        Picasso.with(this)
                .load(uri)
                .fit()
                .into(mImageView);
    }

}
