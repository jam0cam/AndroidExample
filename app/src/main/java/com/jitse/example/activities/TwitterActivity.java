package com.jitse.example.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.jitse.example.R;
import com.twitter.sdk.android.tweetui.TweetView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class TwitterActivity extends ActionBarActivity {

    @InjectView(R.id.photo_default_tweet)
    TweetView mTweetView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter);

        ButterKnife.inject(this);
    }

}
