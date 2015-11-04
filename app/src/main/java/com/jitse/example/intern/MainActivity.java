package com.jitse.example.intern;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.jitse.example.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends Activity implements RecyclerAdapter.ClickListener {

    private static final String TAG = MainActivity.class.getName();
    @InjectView(R.id.rv_main)
    RecyclerView mRecyclerView;


    private RecyclerView.LayoutManager mLayoutManager;

    String[] mUrls;
    String[] mBrands;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .build();

        ImageLoader imgLoader = ImageLoader.getInstance();
        imgLoader.init(config);

        mUrls = getResources().getStringArray(R.array.images_list);
        mBrands = getResources().getStringArray(R.array.shoe_brands);


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);

        RecyclerAdapter mAdapter = new RecyclerAdapter(this, imgLoader, getDrawable(R.drawable.spinner_1), mUrls, mBrands);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onClicked(ImageView imageView, byte[] bytes) {

        Log.d(TAG, "onClicked");
        Intent intent = new Intent(this, HeroActivity.class);

        intent.putExtra("bmp", bytes);
        intent.putExtra("brand", imageView.getTag().toString());

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, imageView, "hero");
        startActivity(intent, options.toBundle());
    }
}
