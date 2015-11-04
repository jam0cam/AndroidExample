package com.jitse.example.intern;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.widget.ImageView;

import com.jitse.example.R;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class HeroActivity extends Activity {

    private static final String TAG = HeroActivity.class.getName();
    @InjectView(R.id.iv)
    ImageView mIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero);

        ButterKnife.inject(this);

        byte[] b =  getIntent().getExtras().getByteArray("bmp");
        String brand =  getIntent().getExtras().getString("brand");

        getActionBar().setTitle(brand);

        Bitmap bmp = BitmapFactory.decodeByteArray(b, 0, b.length);
        mIv.setImageBitmap(bmp);


        Palette.generateAsync(bmp, new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {

                if (palette.getVibrantSwatch() != null) {
                    getActionBar().setBackgroundDrawable(new ColorDrawable(palette.getVibrantSwatch().getRgb()));
                    Log.d(TAG, "Using vibrant color");
                } else if (palette.getMutedSwatch() != null) {
                    Log.d(TAG, "Using muted color");
                    getActionBar().setBackgroundDrawable(new ColorDrawable(palette.getMutedSwatch().getRgb()));
                }
            }
        });
    }
}
