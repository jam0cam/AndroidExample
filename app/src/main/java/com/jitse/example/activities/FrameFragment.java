package com.jitse.example.activities;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.jitse.example.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by jitse on 9/19/14.
 */
public class FrameFragment extends DialogFragment {

    private static final String TAG = FrameFragment.class.getName();
    @InjectView(R.id.fl_main)
    FrameLayout mFlMain;
    private boolean alreadyMeasured = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_frame, container, false);

        ButterKnife.inject(this, root);

        return root;
    }

    private void measureTest() {
        Log.d(TAG, "Measuring");
        RelativeLayout.LayoutParams mParams;
        mParams = (RelativeLayout.LayoutParams) mFlMain.getLayoutParams();
        mParams.height = mFlMain.getWidth();
        mFlMain.setLayoutParams(mParams);
        mFlMain.postInvalidate();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);




//
//        mFlMain.getViewTreeObserver().addOnGlobalLayoutListener(new     ViewTreeObserver.OnGlobalLayoutListener() {
//            public void onGlobalLayout() {
//                if(alreadyMeasured)
//                    mFlMain.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                else {
//                    measureTest();
//                    alreadyMeasured = true;
//                }
//            }
//        });

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        // request a window without the title
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);



        return dialog;

    }
}
