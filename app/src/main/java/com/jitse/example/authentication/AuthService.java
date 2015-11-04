package com.jitse.example.authentication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by jitse on 4/7/15.
 */
public class AuthService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        AccountAuthenticator authenticator = new AccountAuthenticator(this);
        return authenticator.getIBinder();
    }
}
