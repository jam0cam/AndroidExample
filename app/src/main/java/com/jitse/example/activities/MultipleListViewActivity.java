package com.jitse.example.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jitse.example.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MultipleListViewActivity extends ActionBarActivity {

    private static final String TAG = MultipleListViewActivity.class.getName();
    @InjectView(R.id.list_view)
    ListView mListView;
    private MyListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_list_view);

        ButterKnife.inject(this);

        List<String> names = new ArrayList<String>();
        names.add("Hello");
        names.add("World");
        names.add("This");
        names.add("Test");
        mAdapter = new MyListAdapter(this, names);
        mListView.setAdapter(mAdapter);

        mListView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "item long clicked: " + position);

                mListView.setItemChecked(position, true);
                return true;
            }
        });
    }
}
