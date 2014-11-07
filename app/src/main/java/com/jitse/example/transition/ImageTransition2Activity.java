package com.jitse.example.transition;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.jitse.example.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ImageTransition2Activity extends Activity {

    @InjectView(R.id.iv)
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_transition2);

        ButterKnife.inject(this);

        byte[] bytes = getIntent().getByteArrayExtra("bitmap");
        Bitmap imageBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

        mImageView.setImageBitmap(imageBitmap);
    }

}
