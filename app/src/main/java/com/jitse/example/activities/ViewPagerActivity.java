package com.jitse.example.activities;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jitse.example.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ViewPagerActivity extends AppCompatActivity {

    List<String> mUrls;
    MyPagerAdapter mPagerAdapter;

    @InjectView(R.id.view_pager)
    ViewPager mViewPager;

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        ButterKnife.inject(this);
        setSupportActionBar(mToolbar);

        mUrls = new ArrayList<>();

        mUrls.add("http://a2.zassets.com/images/z/1/4/0/14078-p-MULTIVIEW.jpg");
        mUrls.add("http://a1.zassets.com/images/z/3/4/1/2/9/6/3412963-p-MULTIVIEW.jpg");
        mUrls.add("http://a2.zassets.com/images/z/3/4/6/8/1/3/3468131-p-MULTIVIEW.jpg");
        mUrls.add("http://a2.zassets.com/images/z/1/4/0/14079-p-MULTIVIEW.jpg");
        mUrls.add("http://a1.zassets.com/images/z/2/8/7/3/3/6/287336-p-MULTIVIEW.jpg");


        mPagerAdapter = new MyPagerAdapter(mUrls);
        mViewPager.setAdapter(mPagerAdapter);
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
            return ViewPagerActivity.this.mUrls == null ? 0 : ViewPagerActivity.this.mUrls.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
