package com.jitse.example.activities;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by jitse on 4/7/15.
 */
public class BaseAuthenticatedActivity extends ActionBarActivity{


    protected AccountManager mAccountManager;
    protected String mAuthToken;
    protected Account mAccount;

    public Account getRegularAccount() {
        Account[] accounts = mAccountManager.getAccountsByType(LoginActivity.EXAMPLE_ACCOUNT);

        if (accounts.length > 0) {
            return accounts[0];
        } else {
            return null;
        }

    }
}
