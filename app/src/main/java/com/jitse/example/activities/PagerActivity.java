package com.jitse.example.activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;

import com.jitse.example.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PagerActivity extends ActionBarActivity implements ProductPagerAdapter.PagerListener{


    String urls [] = {
            "http://a2.zassets.com/images/z/2/9/6/6/0/4/2966044-p-2x.jpg",
            "http://a2.zassets.com/images/z/2/9/6/6/0/4/2966044-4-2x.jpg",
            "http://a2.zassets.com/images/z/2/9/6/6/0/4/2966044-3-2x.jpg",
            "http://a2.zassets.com/images/z/2/9/6/6/0/4/2966044-1-2x.jpg",
            "http://a2.zassets.com/images/z/2/9/6/6/0/4/2966044-2-2x.jpg",
            "http://a2.zassets.com/images/z/2/9/6/6/0/4/2966044-5-2x.jpg",
    };

    @InjectView(R.id.image_view)
    ImageView mImageView;

    @InjectView(R.id.view_pager)
    ViewPager mViewPager;

    ImageLoader mImageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);

        postponeEnterTransition();
        ButterKnife.inject(this);


        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .build();

        mImageLoader = ImageLoader.getInstance();
        mImageLoader.init(config);

        ProductPagerAdapter mPagerAdapter = new ProductPagerAdapter(Arrays.asList(urls), mImageLoader, this);
            mViewPager.setAdapter(mPagerAdapter);
    }

    @Override
    public void imageLoaded() {
        startPostponedEnterTransition();
    }

    private void loadImage() {
        mImageLoader.displayImage(urls[0], mImageView, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                PagerActivity.this.startPostponedEnterTransition();
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        });
    }
}
