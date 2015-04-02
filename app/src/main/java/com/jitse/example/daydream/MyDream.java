package com.jitse.example.daydream;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.service.dreams.DreamService;
import android.widget.TextView;

/**
 * Created by jitse on 4/2/15.
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
public class MyDream extends DreamService {
    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setInteractive(true);
        setFullscreen(true);

        TextView tv = new TextView(this);
        setContentView(tv);
        tv.setText("This is your own Dream Hurray !!");
        tv.setTextColor(Color.rgb(184, 245, 0));
        tv.setTextSize(30);

    }
}
