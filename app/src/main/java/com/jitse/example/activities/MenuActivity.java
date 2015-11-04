package com.jitse.example.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.jitse.example.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MenuActivity extends AppCompatActivity {

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;

    @InjectView(R.id.button)
    Button mButton;

    @InjectView(R.id.btn_toggle)
    Button mBtnHide;

    boolean mShouldHide = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ButterKnife.inject(this);

        setSupportActionBar(mToolbar);
    }

    @OnClick(R.id.button)
    public void clicked() {
        startActivity(new Intent(this, TestActivity.class));
    }

    @OnClick(R.id.btn_toggle)
    public void hideMenuItems() {
        mShouldHide = !mShouldHide;
        invalidateOptionsMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);

        MenuItem item1 = menu.findItem(R.id.action_add);
        item1.setVisible(!mShouldHide);

        MenuItem item2 = menu.findItem(R.id.action_camera);
        item2.setVisible(!mShouldHide);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
