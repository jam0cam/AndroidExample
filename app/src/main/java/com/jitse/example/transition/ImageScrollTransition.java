package com.jitse.example.transition;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.jitse.example.R;

import java.io.ByteArrayOutputStream;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ImageScrollTransition extends ActionBarActivity implements View.OnClickListener{
    private static final String TAG = ImageScrollTransition.class.getName();
    @InjectView(R.id.img_product)
    ImageView mImgProduct;

    @InjectView(R.id.rv_styles)
    RecyclerView mRvStyles;
    LinearLayoutManager mLayoutManager;

    @InjectView(R.id.img_anodyne)
    ImageView mImgAnodyne;

    @InjectView(R.id.img_forever)
    ImageView mImgForever;

    @InjectView(R.id.img_lunar)
    ImageView mImgLunar;

    @InjectView(R.id.img_lite)
    ImageView mImgLite;

    @InjectView(R.id.img_glide)
    ImageView mImgGlide;

    private ProductRecyclerAdapter mAdapterStyles;
    private int mScrollX = -1;
    private int mScrollY = -1;
    private int mScrollPosition = -1;

    private String[] colors = {"Black/Dark Magnet Grey/Summit White/Volt",
            "Magnet Grey/Electric Green/Light Magnet Grey/Black",
            "Magnet Grey/Light Magnet Grey/Summit White/Hyper Crimson",
            "Wolf Grey/Dark Grey/Metallic Silver/Black",
            "Volt/Electric Green/Photo Blue/Black",
            "Hyper Crimson/Bright Citrus/Summit White/Photo Blue",
            "Deep Royal Blue/Black/Hyper Punch/White"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_scroll_transition);
        ButterKnife.inject(this);

        byte[] bytes = getIntent().getByteArrayExtra("bitmap");
        if (bytes != null) {
            Bitmap imageBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            mImgProduct.setImageBitmap(imageBitmap);
            mScrollX = getIntent().getIntExtra("scrollX", -1);
            mScrollY = getIntent().getIntExtra("scrollY", -1);
            mScrollPosition = getIntent().getIntExtra("scrollPosition", -1);
        }

        setupImages();
        setupStyles();

    }

    private void setupImages() {
        mImgAnodyne.setOnClickListener(this);
        mImgForever.setOnClickListener(this);
        mImgLite.setOnClickListener(this);
        mImgGlide.setOnClickListener(this);
        mImgLunar.setOnClickListener(this);
    }

    private void setupStyles() {
        mRvStyles.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvStyles.setLayoutManager(mLayoutManager);

        Drawable[] styles = {
                getResources().getDrawable(R.drawable.black_neon),
                getResources().getDrawable(R.drawable.gray_green),
                getResources().getDrawable(R.drawable.gray_orange),
                getResources().getDrawable(R.drawable.gray),
                getResources().getDrawable(R.drawable.neon_yellow),
                getResources().getDrawable(R.drawable.orange),
                getResources().getDrawable(R.drawable.royal_blue)
        };

        mAdapterStyles = new ProductRecyclerAdapter(colors, null, null, styles, getResources().getDimensionPixelSize(R.dimen.small_padding), new ProductRecyclerAdapter.RecyclerClickListener() {
            @Override
            public void onClicked(ImageView imageView) {
                startTransitionActivity(imageView);
            }
        });

        mRvStyles.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mScrollPosition = dx;
                Log.d(TAG, "mScrollPosition: " + mScrollPosition);
                Log.d(TAG, "Left: " + mRvStyles.getLeft());
                Log.d(TAG, "X: " + mRvStyles.getX());
                Log.d(TAG, "TranslationX: " + mRvStyles.getTranslationX());
                Log.d(TAG, "findFirstCompletelyVisibleItemPosition: " + mLayoutManager.findFirstCompletelyVisibleItemPosition());
            }
        });

        mRvStyles.setAdapter(mAdapterStyles);
    }

    private void startTransitionActivity(ImageView imageView) {
        Log.d(TAG, "startTransitionActivity");
        Intent intent = new Intent(this, ImageScrollTransition.class);

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, imageView, getString(R.string.transition_product_image));

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Bitmap bmp = ((BitmapDrawable)imageView.getDrawable()).getBitmap();

        mScrollX = mRvStyles.getScrollX();
        mScrollY = mRvStyles.getScrollY();

        if (bmp != null) {
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] bytes = stream.toByteArray();
            intent.putExtra("bitmap", bytes);
            intent.putExtra("scrollX", mScrollX);
            intent.putExtra("scrollY", mScrollY);
            Log.d(TAG, "first visible position: " + mLayoutManager.findFirstCompletelyVisibleItemPosition());

            intent.putExtra("scrollPosition", mLayoutManager.findFirstCompletelyVisibleItemPosition());
            startActivity(intent, options.toBundle());
        }

    }

    @Override
    public void onClick(View v) {
        startTransitionActivity((ImageView) v);
    }


}
