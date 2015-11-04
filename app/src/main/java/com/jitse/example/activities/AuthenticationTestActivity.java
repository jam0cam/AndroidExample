package com.jitse.example.activities;

import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jitse.example.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class AuthenticationTestActivity extends BaseAuthenticatedActivity {

    private static final String TAG = AuthenticationTestActivity.class.getName();
    @InjectView(R.id.btn_launch_activity)
    Button btn;

    @InjectView(R.id.btn_log_out)
    Button btnLogOut;

    @InjectView(R.id.txt_login_status)
    TextView txtLoginStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication_test);

        ButterKnife.inject(this);
        mAccountManager = AccountManager.get(this);

        mAccount = getRegularAccount();


        if (mAccount != null) {
            mAuthToken = mAccountManager.peekAuthToken(mAccount, LoginActivity.AUTH_TOKEN);
            if (mAuthToken != null) {
                txtLoginStatus.setText("User logged in: " + mAuthToken);
                btnLogOut.setVisibility(View.VISIBLE);
            } else {
                txtLoginStatus.setText("User not logged in.");
                btnLogOut.setVisibility(View.GONE);
            }
        } else {
            txtLoginStatus.setText("User not logged in.");
            btnLogOut.setVisibility(View.GONE);
        }
    }

    /**
     * We are moving forward to an activity that requires authentication. We won't allow the user to go without
     * a valid access token
     */
    @OnClick(R.id.btn_launch_activity)
    public void launchActivity() {
        if (mAuthToken != null) {
            Log.d(TAG, "Auth token exists, starting CircularActivity");
            startActivity(new Intent(this, CircularActivity.class));
        } else {
            Log.d(TAG, "Auth token doesn't exist, redirecting to Login");

            Intent login = new Intent(this, LoginActivity.class);
            login.putExtra(LoginActivity.IS_ADDING_NEW_ACCOUNT, true);
            startActivity(login);
        }
    }

    @OnClick(R.id.btn_log_out)
    public void logout() {
        mAccountManager.invalidateAuthToken(LoginActivity.EXAMPLE_ACCOUNT, mAuthToken);
    }

}
