package com.jitse.example.activities;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by jitse on 2/4/15.
 */
public class PhotoAdapter extends PagerAdapter {
    private Context mContext;
    private List<String> mUrls;


    public PhotoAdapter(Context context, List<String> mUrls) {
        this.mContext = context;
        this.mUrls = mUrls;
    }

    @Override
    public int getCount() {
        return mUrls.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ImageView imageView = new ImageView(mContext);
        if (position == 0) {
            imageView.setTransitionName("image");
        }
        Picasso.with(mContext)
                .load(mUrls.get(position))
                .into(imageView);

        container.addView(imageView);

        return imageView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
