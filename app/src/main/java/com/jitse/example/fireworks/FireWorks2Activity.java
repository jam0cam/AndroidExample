package com.jitse.example.fireworks;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.jitse.example.R;
import com.plattysoft.leonids.ParticleSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class FireWorks2Activity extends AppCompatActivity {

    @InjectView(R.id.btn)
    Button mButton;

    @InjectView(R.id.table_layout)
    TableLayout mTableLayout;

    @InjectView(R.id.btn_fireworks)
    Button mBtnFireworks;

    Fireworks fireworks;

    private List<Drawable> mDrawables;
    private List<Button> mButtons;
    private Random mRandom;

    Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_works2);
        ButterKnife.inject(this);

        mDrawables = new ArrayList<>();
        mButtons = new ArrayList<>();
        mHandler = new Handler();

//        mDrawables.add(ContextCompat.getDrawable(this, R.drawable.star_blue));
        mDrawables.add(ContextCompat.getDrawable(this, R.drawable.star_green));
        mDrawables.add(ContextCompat.getDrawable(this, R.drawable.star_pink));
//        mDrawables.add(ContextCompat.getDrawable(this, R.drawable.star_purple));
        mDrawables.add(ContextCompat.getDrawable(this, R.drawable.star_red));
        mDrawables.add(ContextCompat.getDrawable(this, R.drawable.star_white));
        mDrawables.add(ContextCompat.getDrawable(this, R.drawable.star_yellow));

        mRandom = new Random(System.currentTimeMillis());

        for (int i=0; i<11; i++) {
            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            row.setBackgroundColor(randomColor());

            row.addView(getNewButton());
            row.addView(getNewButton());
            row.addView(getNewButton());
            mTableLayout.addView(row);
        }
    }

    private Button getNewButton() {
        Button button = new Button(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myClick(v);
            }
        });
        button.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));

        mButtons.add(button);
        return button;
    }

    private Button getRandomButton() {
        return mButtons.get(mRandom.nextInt(mButtons.size()));
    }

    private int randomColor() {
        int color = Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256));
        return color;
    }

    @OnClick(R.id.btn_fireworks)
    public void fireworks() {
        for (int i=0; i<25; i++) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    myClick(getRandomButton());
                }
            }, mRandom.nextInt(5000));
        }
    }

    private Drawable getRandomDrawable() {
        return mDrawables.get(mRandom.nextInt(mDrawables.size()));
    }

    @OnClick(R.id.btn)
    public void myClick(View arg0) {
        Drawable d = getRandomDrawable();
        ParticleSystem ps = new ParticleSystem(this, 100, d, 800);
        ps.setScaleRange(0.7f, 1.3f);
        ps.setSpeedRange(0.1f, 0.25f);
        ps.setRotationSpeedRange(90, 180);
        ps.setFadeOut(200, new AccelerateInterpolator());
        ps.oneShot(arg0, 70);

        ParticleSystem ps2 = new ParticleSystem(this, 100, d, 800);
        ps2.setScaleRange(0.7f, 1.3f);
        ps2.setSpeedRange(0.1f, 0.25f);
        ps.setRotationSpeedRange(90, 180);
        ps2.setFadeOut(200, new AccelerateInterpolator());
        ps2.oneShot(arg0, 70);
    }
}
