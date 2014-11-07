package com.jitse.example.transition;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.jitse.example.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.ByteArrayOutputStream;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ImageTransition1Activity extends Activity {

    private static final String TAG = ImageTransition1Activity.class.getName();
    @InjectView(R.id.iv)
    ImageView mImageView;

    @InjectView(R.id.btn)
    Button mButton;

    String url = "http://a1.zassets.com/images/z/2/8/3/6/4/0/2836409-p-MULTIVIEW.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_transition1);

        ButterKnife.inject(this);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
        .build();

        ImageLoader imgLoader = ImageLoader.getInstance();
        imgLoader.init(config);

        imgLoader.displayImage(url, mImageView);
    }

    @OnClick(R.id.btn)
    public void onClick() {
        Log.d(TAG, "onClicked");
        Intent intent = new Intent(this, ImageTransition2Activity.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, mImageView, "image");

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Bitmap bmp = ((BitmapDrawable)mImageView.getDrawable()).getBitmap();

        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bytes = stream.toByteArray();
        intent.putExtra("bitmap", bytes);

        startActivity(intent, options.toBundle());
    }
}
