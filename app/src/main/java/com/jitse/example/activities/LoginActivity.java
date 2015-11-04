package com.jitse.example.activities;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.jitse.example.R;
import com.jitse.example.authentication.FakeAuthenticationServer;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends AccountAuthenticatorActivity {
    private static final String TAG = LoginActivity.class.getName();

    @InjectView(R.id.btn_login)
    Button btnLogin;

    public static final String TOKEN_TYPE = "token-type";
    public static final String AUTH_TOKEN = "auth-token";

    public static final String ACCOUNT_TYPE = "account-type";
    public static final String EXAMPLE_ACCOUNT = "com.jitse.example";
    public static final String IS_ADDING_NEW_ACCOUNT = "IS_NEW_ACCOUNT";

    public String mAccountType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.inject(this);

        mAccountType = getIntent().getStringExtra(AccountManager.KEY_ACCOUNT_TYPE);

        if (mAccountType == null) {
            mAccountType = EXAMPLE_ACCOUNT;
        }


    }

    @OnClick(R.id.btn_login)
    public void login() {
        Log.d(TAG, "Logging in with user name and password");
        String accountName = "jitse";
        String accountPassword = "zappos101";
        final Account account = new Account(accountName, EXAMPLE_ACCOUNT);

        AccountManager mAccountManager = AccountManager.get(this);
        String authtoken = FakeAuthenticationServer.login();
        String authtokenType = AUTH_TOKEN;

        if (getIntent().getBooleanExtra(IS_ADDING_NEW_ACCOUNT, false)) {
            mAccountManager.addAccountExplicitly(account, accountPassword, null);
            mAccountManager.setAuthToken(account, authtokenType, authtoken);
        } else {
            mAccountManager.setPassword(account, accountPassword);
        }

        final Intent intent = new Intent();
        intent.putExtra(AccountManager.KEY_ACCOUNT_NAME, accountName);
        intent.putExtra(AccountManager.KEY_ACCOUNT_TYPE, EXAMPLE_ACCOUNT);
        intent.putExtra(AccountManager.KEY_AUTHTOKEN, authtoken);

        setAccountAuthenticatorResult(intent.getExtras());
        setResult(RESULT_OK, intent);
        finish();
    }
}
