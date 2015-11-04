package com.jitse.example.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jitse.example.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by jitse on 12/3/14.
 */
public class ReviewsFragment extends Fragment {

    private static final String TAG = ReviewsFragment.class.getName();
    @InjectView(R.id.rv)
    RecyclerView mRv;
    private ReviewsRecyclerAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_reviews, container, false);

        Log.d(TAG, "onCreateView reviews fragment");
        ButterKnife.inject(this, root);

        mRv.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRv.setLayoutManager(mLayoutManager);

        mAdapter = new ReviewsRecyclerAdapter();
        mRv.setAdapter(mAdapter);

        return root;
    }
}
