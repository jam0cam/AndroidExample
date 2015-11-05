package com.jitse.example.activities;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.CredentialRequest;
import com.google.android.gms.auth.api.credentials.CredentialRequestResult;
import com.google.android.gms.auth.api.credentials.IdentityProviders;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.jitse.example.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class SmartLockActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = SmartLockActivity.class.getName();
    private static final int RC_READ = 2001;
    private static final int RC_SAVE = 2002;

    private GoogleApiClient mCredentialsClient;
    private CredentialRequest mCredentialRequest;

    @InjectView(R.id.ll_password)
    LinearLayout mPasswordLayout;

    @InjectView(R.id.txt_username)
    EditText mTxtUsername;

    @InjectView(R.id.txt_password)
    EditText mTxtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_lock);
        ButterKnife.inject(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mCredentialsClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Auth.CREDENTIALS_API)
                .build();

    }

    @Override
    protected void onStart() {
        super.onStart();

        mCredentialsClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();

        mCredentialsClient.disconnect();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_READ) {
            if (resultCode == RESULT_OK) {
                Credential credential = data.getParcelableExtra(Credential.EXTRA_KEY);
                onCredentialRetrieved(credential);
            } else {
                Log.e(TAG, "Credential Read: NOT OK");
                Toast.makeText(this, "Credential Read Failed", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == RC_SAVE) {
            if (resultCode == RESULT_OK) {
                Log.d(TAG, "SAVE: OK");
                Toast.makeText(this, "Credentials saved", Toast.LENGTH_SHORT).show();
            } else {
                Log.e(TAG, "SAVE: Canceled by user");
            }
        }
    }

    private void resolveResult(Status status) {
        Log.d(TAG, "resolveResult");
        if (status.getStatusCode() == CommonStatusCodes.RESOLUTION_REQUIRED) {
            // Prompt the user to choose a saved credential; do not show the hint
            // selector.
            try {
                status.startResolutionForResult(this, RC_READ);
            } catch (IntentSender.SendIntentException e) {
                Log.e(TAG, "STATUS: Failed to send resolution.", e);
            }
        } else {
            // The user must create an account or sign in manually.
            Log.e(TAG, "STATUS: Unsuccessful credential request.");
            mPasswordLayout.setVisibility(View.VISIBLE);
        }
    }

    private void onCredentialRetrieved(Credential credential) {
        Log.d(TAG, "onCredentialRetrieved");

        if (credential.getAccountType() == null) {
            Log.d(TAG, "successfully received credentials!!!");
            mPasswordLayout.setVisibility(View.VISIBLE);
            mTxtPassword.setText(credential.getPassword());
            mTxtUsername.setText(credential.getId());
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d(TAG, "onConnected");
        requestLogin();
    }

    private void requestLogin() {
        mCredentialRequest = new CredentialRequest.Builder()
                .setSupportsPasswordLogin(true)
                .build();

        Auth.CredentialsApi.request(mCredentialsClient, mCredentialRequest).setResultCallback(
                new ResultCallback<CredentialRequestResult>() {
                    @Override
                    public void onResult(CredentialRequestResult credentialRequestResult) {
                        if (credentialRequestResult.getStatus().isSuccess()) {
                            // See "Handle successful credential requests"
                            onCredentialRetrieved(credentialRequestResult.getCredential());
                        } else {
                            // See "Handle unsuccessful and incomplete credential requests"
                            resolveResult(credentialRequestResult.getStatus());
                        }
                    }
                });
    }

    @OnClick(R.id.btn_submit)
    public void savePassword() {
        Credential credential = new Credential.Builder(mTxtUsername.getText().toString().trim())
                .setPassword(mTxtPassword.getText().toString().trim())
                .build();

        Auth.CredentialsApi.save(mCredentialsClient, credential).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status result) {
                        Status status = result.getStatus();
                        if (status.isSuccess()) {
                            Log.d(TAG, "SAVE: OK");
                            Toast.makeText(SmartLockActivity.this, "Credentials saved", Toast.LENGTH_SHORT).show();
                        } else {
                            if (status.hasResolution()) {
                                // Try to resolve the save request. This will prompt the user if
                                // the credential is new.
                                try {
                                    status.startResolutionForResult(SmartLockActivity.this, RC_SAVE);
                                } catch (IntentSender.SendIntentException e) {
                                    // Could not resolve the request
                                    Log.e(TAG, "STATUS: Failed to send resolution.", e);
                                    Toast.makeText(SmartLockActivity.this, "Save failed", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                // Request has no resolution
                                Toast.makeText(SmartLockActivity.this, "Save failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "onConnectionSuspended");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed");
    }
}
