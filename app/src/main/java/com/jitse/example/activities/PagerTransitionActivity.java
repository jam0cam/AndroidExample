package com.jitse.example.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.jitse.example.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PagerTransitionActivity extends ActionBarActivity {

    @InjectView(R.id.rv_similar_items)
    RecyclerView mRvSimilarItems;

    @InjectView(R.id.mViewPager)
    ViewPager mViewPager;

    @InjectView(R.id.image_view)
    ImageView mImageView;

    private ProductPagerAdapter mPagerAdapter;

    private SimilarItemsRecyclerAdapter mAdapterSimilarItems;

    String urls [] = {
            "http://a1.zassets.com/images/z/2/5/9/0/0/7/2590074-p-MULTIVIEW.jpg",
            "http://a1.zassets.com/images/z/2/6/3/1/4/3/2631430-p-MULTIVIEW.jpg",
            "http://a1.zassets.com/images/z/2/8/7/6/1/5/2876156-p-MULTIVIEW.jpg",
            "http://a1.zassets.com/images/z/2/7/2/5/1/8/2725180-p-MULTIVIEW.jpg",
            "http://a1.zassets.com/images/z/2/7/2/5/1/8/2725182-p-MULTIVIEW.jpg",
            "http://a1.zassets.com/images/z/2/7/2/5/1/8/2725181-p-MULTIVIEW.jpg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_transition);
        ButterKnife.inject(this);

        mRvSimilarItems.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvSimilarItems.setLayoutManager(mLayoutManager);


        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .build();

        ImageLoader imgLoader = ImageLoader.getInstance();
        imgLoader.init(config);

        mAdapterSimilarItems = new SimilarItemsRecyclerAdapter(Arrays.asList(urls), new SimilarItemsRecyclerAdapter.RecyclerClickListener() {
            @Override
            public void onSimilarItemsClicked(ImageView imageView, String style, int position) {
                startProductTransitionActivity(imageView, style, position);
            }
        }, imgLoader);

        mRvSimilarItems.setAdapter(mAdapterSimilarItems);

        String imageUrl = "http://www.zappos.com/images/z/2/5/9/2590074-p-2x.jpg";

        List<String> purls = new ArrayList<String>();
        purls.add(imageUrl);
        purls.add(imageUrl);
        purls.add(imageUrl);
        purls.add(imageUrl);
        purls.add(imageUrl);

        String url = getIntent().getStringExtra("url");

        if (!TextUtils.isEmpty(url)) {
            mViewPager.setVisibility(View.GONE);
            mImageView.setVisibility(View.VISIBLE);
            imgLoader.displayImage(imageUrl, mImageView);
        } else {
//            mPagerAdapter = new ProductPagerAdapter(purls, imgLoader);
//            mViewPager.setAdapter(mPagerAdapter);
//
//            mViewPager.setVisibility(View.VISIBLE);
//            mImageView.setVisibility(View.GONE);
        }


    }

    private void startProductTransitionActivity(ImageView imageView, String url, int position) {
        Intent intent = new Intent(this, PagerTransitionActivity.class);

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, imageView, "tpi");
        intent.putExtra("url", url);

        startActivity(intent, options.toBundle());
    }


}
