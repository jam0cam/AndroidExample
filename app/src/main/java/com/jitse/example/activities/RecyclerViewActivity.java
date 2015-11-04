package com.jitse.example.activities;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jitse.example.R;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class RecyclerViewActivity extends Activity {

    @InjectView(R.id.rv)
    RecyclerView mRv;

    private RecyclerView.LayoutManager mLayoutManager;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        ButterKnife.inject(this);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRv.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);

        mRv.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        String[] myDataset = {"This", "Is", "A", "Test"};
        Drawable[] drawables = {
                getResources().getDrawable(R.drawable.one),
                getResources().getDrawable(R.drawable.two),
                getResources().getDrawable(R.drawable.three),
                getResources().getDrawable(R.drawable.four),
        };

        MyRecyclerAdapter mAdapter = new MyRecyclerAdapter(myDataset, drawables);
        mRv.setAdapter(mAdapter);
    }
}
