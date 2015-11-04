package com.jitse.example.authentication;

import android.util.Log;

import java.util.UUID;

/**
 * Created by jitse on 4/4/15.
 */
public class FakeAuthenticationServer {

    private static final String TAG = FakeAuthenticationServer.class.getName();

    public static String login() {
        Log.d(TAG, "fake login server returning fake token");
        return UUID.randomUUID().toString();
    }
}
