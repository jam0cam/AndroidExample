package com.jitse.example;

import android.app.Activity;
import android.os.Bundle;


public class DialogFragmentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_fragment);

        FrameFragment f = new FrameFragment();
        f.show(getFragmentManager(), FrameFragment.class.getName());

    }

}
