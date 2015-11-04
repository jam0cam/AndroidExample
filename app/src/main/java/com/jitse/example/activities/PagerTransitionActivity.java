package com.jitse.example.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.jitse.example.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PagerTransitionActivity extends ActionBarActivity {

    @InjectView(R.id.rv_similar_items)
    RecyclerView mRvSimilarItems;

    @InjectView(R.id.image_view)
    ImageView mImageView;

    private SimilarItemsRecyclerAdapter mAdapterSimilarItems;

    String urls [] = {
            "http://a1.zassets.com/images/z/2/5/9/0/0/7/2590074-p-MULTIVIEW.jpg",
            "http://a1.zassets.com/images/z/2/6/3/1/4/3/2631430-p-MULTIVIEW.jpg",
            "http://a1.zassets.com/images/z/2/8/7/6/1/5/2876156-p-MULTIVIEW.jpg",
            "http://a2.zassets.com/images/z/2/9/6/6/0/4/2966044-p-MULTIVIEW.jpg",
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

        imgLoader.displayImage(urls[3], mImageView);
    }

    private void startProductTransitionActivity(ImageView imageView, String url, int position) {
        Intent intent = new Intent(this, PagerActivity.class);

        imageView.setTransitionName("tpi");
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, imageView, "tpi");
        intent.putExtra("url", url);

        startActivity(intent, options.toBundle());
    }
}
