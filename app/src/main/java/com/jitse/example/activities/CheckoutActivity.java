package com.jitse.example.activities;

import android.accounts.Account;
import android.os.Bundle;

import com.jitse.example.R;

public class CheckoutActivity extends BaseAuthenticatedActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
    }

    public Account getRegularAccount() {
        Account[] accounts = mAccountManager.getAccountsByType(LoginActivity.EXAMPLE_ACCOUNT);

        if (accounts.length > 0) {
            return accounts[0];
        } else {
            return null;
        }
    }
}
