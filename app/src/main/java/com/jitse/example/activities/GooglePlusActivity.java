package com.jitse.example.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.jitse.example.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class GooglePlusActivity extends Activity {

    private static final String TAG = GooglePlusActivity.class.getName();
    @InjectView(R.id.lv)
    ListView mLv;

    ImageView mImgHeader;
    View mHeaderView;
    View mStickyHeader;
    ImageButton mFab;
    boolean mIsAnimating = false;

    String[] values = new String[] { "Android List View",
            "Adapter implementation",
            "Simple List View In Android",
            "Create List View Android",
            "Android Example",
            "List View Source Code",
            "List View Array Adapter",
            "Simple List View In Android",
            "Create List View Android",
            "Android Example",
            "List View Source Code",
            "List View Array Adapter",
            "Simple List View In Android",
            "Create List View Android",
            "Android Example",
            "List View Source Code",
            "List View Array Adapter",
            "Simple List View In Android",
            "Create List View Android",
            "Android Example",
            "List View Source Code",
            "List View Array Adapter",
            "Android Example List View"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_plus);

        ButterKnife.inject(this);

        mStickyHeader = findViewById(R.id.sticky_header);
        mHeaderView = getLayoutInflater().inflate(R.layout.list_header, mLv, false);
        mHeaderView.setClickable(false);

        mImgHeader = (ImageView) mHeaderView.findViewById(R.id.header_imageview);

        mFab = (ImageButton) mStickyHeader.findViewById(R.id.fab);

        mLv.addHeaderView(mHeaderView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        mLv.setAdapter(adapter);

        mLv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(visibleItemCount == 0) return;


                //parallax effect
                mImgHeader.setTranslationY(-mLv.getChildAt(0).getTop() / 2);


                /* ***** This is to adjust the position of the sticky header  ***** */
                int top = mHeaderView.getTop();
                int tabsHeight = mStickyHeader.getMeasuredHeight();
                int headerViewHeight = mHeaderView.getMeasuredHeight();
                int delta = headerViewHeight - tabsHeight;

                if(firstVisibleItem == 0) {
                    mStickyHeader.setTranslationY(Math.max(0, delta + top));
                }


                /* ***** This is to adjust the position of the FAB ***** */
                int topFab = mFab.getTop();
                int topSticky = Math.max(0, delta + top);

                if (topFab != 0 && !mIsAnimating) {
                    if (topSticky + topFab < 0) {
                        Log.d(TAG, "animating down");
                        mFab.animate().translationY(tabsHeight).setDuration(250).setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationStart(Animator animation) {
                                mIsAnimating = true;
                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                mIsAnimating = false;
                            }
                        }).start();
                    } else {
                        Log.d(TAG, "animating up");
                        mFab.animate().translationY(0).setDuration(250).setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationStart(Animator animation) {
                                mIsAnimating = true;
                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                mIsAnimating = false;
                            }
                        }).start();
                    }
                }
            }
        });
    }
}
