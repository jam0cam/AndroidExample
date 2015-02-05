package com.jitse.example.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.ViewTreeObserver;

import com.jitse.example.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DetailActivity extends ActionBarActivity {

    @InjectView(R.id.pager)
    ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.inject(this);


        postponeEnterTransition();
        mPager.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
               mPager.getViewTreeObserver().removeOnPreDrawListener(this);
                startPostponedEnterTransition();
                return true;
            }
        });


        List<String> list = new ArrayList<String>();
        list.add("http://a1.zassets.com/images/z/3/0/2/7/2/8/3027286-p-MULTIVIEW.jpg");
        list.add("http://a1.zassets.com/images/z/2/7/2/7/3/3/2727332-p-MULTIVIEW.jpg");
        list.add("http://a3.zassets.com/images/z/2/7/3/4/1/0/2734106-p-MULTIVIEW.jpg");
        list.add("http://a3.zassets.com/images/z/2/8/1/4/5/4/2814541-p-MULTIVIEW.jpg");

        PhotoAdapter adapter = new PhotoAdapter(this, list);
        mPager.setAdapter(adapter);



    }

}
