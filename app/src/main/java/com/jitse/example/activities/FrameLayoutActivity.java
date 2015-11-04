package com.jitse.example.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.jitse.example.R;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class FrameLayoutActivity extends Activity {

    @InjectView(R.id.fl_main)
    FrameLayout mFlMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_layout);
        ButterKnife.inject(this);

        String option = getIntent().getStringExtra("option");
        if (option.equals("1")) {
            mFlMain.setLayoutParams(new RelativeLayout.LayoutParams(20, 20));
        } else {
            mFlMain.setLayoutParams(new RelativeLayout.LayoutParams(300, 300));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.frame_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
