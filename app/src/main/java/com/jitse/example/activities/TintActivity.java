package com.jitse.example.activities;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.jitse.example.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class TintActivity extends ActionBarActivity {

    @InjectView(R.id.img_style)
    ImageView mImgStyle;

    @InjectView(R.id.fl_main)
    FrameLayout mFlMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tint);

        ButterKnife.inject(this);

        Drawable d = getDrawable(R.drawable.royal_blue);
        d.setColorFilter(getResources().getColor(R.color.green), PorterDuff.Mode.MULTIPLY);

        mImgStyle.setImageDrawable(d);


        RippleDrawable ripple = (RippleDrawable) mFlMain.getForeground();

//        ripple.setColorFilter(getResources().getColor(R.color.green), PorterDuff.Mode.MULTIPLY);
//        ripple.setTint(getResources().getColor(R.color.green));

        ColorStateList myColorStateList = new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_pressed},
                        new int[]{}
                },
                new int[] {
                        getResources().getColor(R.color.white),
                        getResources().getColor(R.color.black),
                }
        );

        ripple.setColor(myColorStateList);


    }

}
