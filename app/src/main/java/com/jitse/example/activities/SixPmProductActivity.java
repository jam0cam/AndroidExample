package com.jitse.example.activities;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jitse.example.ExampleApplication;
import com.jitse.example.R;
import com.jitse.example.model.ImageResponse;
import com.jitse.example.model.ProductImage;
import com.jitse.example.retrofit.SixPmService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SixPmProductActivity extends AppCompatActivity {
    private static final String TAG = SixPmProductActivity.class.getName();

    SixPmService mService;

    List<String> mUrls;

    @InjectView(R.id.view_pager)
    ViewPager mPager;

    MyPagerAdapter mPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_six_pm_product);
        ButterKnife.inject(this);

        mService = ((ExampleApplication)getApplication()).getSixService();


        mUrls = new ArrayList<>();
        mPagerAdapter = new MyPagerAdapter(mUrls);
        mPager.setAdapter(mPagerAdapter);

        mService.getImages("8671638", "5e807d689f3b332419317fe6a541dd26eca1bfb5", "[\"MULTIVIEW\"]", new Callback<ImageResponse>() {
            @Override
            public void success(ImageResponse imageResponse, Response response) {
                mUrls.clear();
                for (List<ProductImage> images : imageResponse.images.values()) {
                    for (ProductImage image : images) {
                        mUrls.add(image.filename);
                        Log.d(TAG, "url: " + image.filename);
                    }
                }

                mPagerAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }


    class MyPagerAdapter extends PagerAdapter {
        private final String TAG = MyPagerAdapter.class.getName();

        private List<String> mUrls;

        public MyPagerAdapter(List<String> urls) {
            mUrls = urls;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            LayoutInflater inflater = LayoutInflater.from(container.getContext());
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.view_pager_item, container, false);

            ImageView imageview = (ImageView) layout.findViewById(R.id.image_view);
            TextView textView = (TextView) layout.findViewById(R.id.txt_url);

            String url = mUrls.get(position);

            Log.d(TAG, "insantiating item:" + position);
            Picasso.with(container.getContext())
                    .load(url)
                    .noFade()
                    .fit()
                    .centerInside()
                    .into(imageview);

            textView.setText(url);

            container.addView(layout);

            return layout;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            Log.d(TAG, "destroying item:" + position);
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return mUrls == null ? 0 : mUrls.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
