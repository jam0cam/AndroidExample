package com.jitse.example.activities;

import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jitse on 12/2/14.
 */

public class ProductPagerAdapter extends PagerAdapter {

    private static final String TAG = ProductPagerAdapter.class.getName();
    private List<String> urls;
    private List<ImageView> mImageViews;

    public interface PagerListener {
        void imageLoaded();
    }

    ImageLoader mImageLoader;
    PagerListener mListener;

    public ProductPagerAdapter(List<String> Strings,  ImageLoader imageLoader, PagerListener listener) {
        urls = Strings;
        mImageViews = new ArrayList<ImageView>(urls.size());
        mImageLoader = imageLoader;
        mListener = listener;
    }

    @Override
    public int getCount() {
        return urls == null ? 0 : urls.size();
    }

    @Override
    public Object instantiateItem(final ViewGroup container, int position) {
        final ImageView imageView = new ImageView(container.getContext());


        if (position == 0) {
            imageView.setTransitionName("tpi");
        }

        mImageViews.add(Math.min(mImageViews.size(), position), imageView);

        Log.d(TAG, "Product Pager url:" + urls.get(position));


        if (position == 0) {
            mImageLoader.displayImage(urls.get(position), imageView, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {

                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    if (mListener != null) {
                        mListener.imageLoaded();
                    }
                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {

                }
            });
        } else {
            mImageLoader.displayImage(urls.get(position), imageView);
        }



        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(final ViewGroup container, int position, final Object object) {
        if (position >= 0 && position < mImageViews.size()) {
            mImageViews.remove(position);
        }

        container.post(new Runnable() {
            @Override
            public void run() {
                container.removeView((View) object);
            }
        });
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}