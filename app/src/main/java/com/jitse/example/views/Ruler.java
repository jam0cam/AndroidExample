package com.jitse.example.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.jitse.example.R;

/**
 * Created by jitse on 11/12/15.
 */
public class Ruler extends View {
    private Paint mLinePaint;
    private Paint mTextPaint;
    private float mTextSize;
    private float mMacroIncrement;
    private float mMicroIncrement;

    private float mRulerWidth;
    float mOneInch;
    float mOneIncrement;

    public Ruler(Context context) {
        super(context);

        init();
    }

    public Ruler(Context context, AttributeSet attrs) {
        super(context, attrs);


        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.Ruler,
                0, 0);

        try {
            mMacroIncrement = a.getDimension(R.styleable.Ruler_macro_increment_height, 100.0f);
            mMicroIncrement = a.getDimension(R.styleable.Ruler_micro_increment_height, 50.0f);
            mTextSize = a.getDimension(R.styleable.Ruler_text_size, 25.0f);
        } finally {
            a.recycle();
        }

        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // Account for padding
        float xpad = (float)(getPaddingLeft() + getPaddingRight());

        mRulerWidth = (float)w - xpad;

        DisplayMetrics dm = getResources().getDisplayMetrics();
        mOneInch = dm.xdpi;
        mOneIncrement = mOneInch/16;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float currentLocation = 0;
        int inchTracker = 0;
        int inchCounter = 0;
        while (currentLocation < mRulerWidth) {
            currentLocation += mOneIncrement;
            inchTracker ++;

            if (inchTracker != 16) {
                //draw small line
                canvas.drawLine(currentLocation, 0, currentLocation, mMicroIncrement, mLinePaint);
            } else {
                //draw long line
                inchTracker = 0;
                canvas.drawLine(currentLocation, 0, currentLocation, mMacroIncrement, mLinePaint);
                inchCounter++;

                String toDraw = String.valueOf(inchCounter);
                float textWidth = mTextPaint.measureText(toDraw)/2;
                float smallBuffer = 15;
                canvas.drawText(toDraw, currentLocation - textWidth, mMacroIncrement + mTextSize + smallBuffer, mTextPaint);
            }
        }

    }


    private void init() {
        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setColor(ContextCompat.getColor(getContext(), R.color.black));

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(ContextCompat.getColor(getContext(), R.color.black));
        mTextPaint.setTextSize(mTextSize);
    }
}
