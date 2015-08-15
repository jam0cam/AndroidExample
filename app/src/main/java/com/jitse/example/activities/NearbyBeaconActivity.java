package com.jitse.example.activities;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.nearby.messages.MessageListener;
import com.jitse.example.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class NearbyBeaconActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = NearbyBeaconActivity.class.getSimpleName();

    private GoogleApiClient mGoogleApiClient;
    private Message mMessage;

    private static final int REQUEST_RESOLVE_ERROR = 5;

    @InjectView(R.id.btn_publisher)
    Button mPublisher;

    @InjectView(R.id.btn_listener)
    Button mListener;

    @InjectView(R.id.txt_received_msg)
    TextView mTxtReceiver;
    private boolean mResolvingError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_beacon);

        mMessage = new Message("Jia Tse".getBytes());

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Nearby.MESSAGES_API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        ButterKnife.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (!mGoogleApiClient.isConnected()) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onStop() {
        if (mGoogleApiClient.isConnected()) {
            // Clean up when the user leaves the activity.
            Nearby.Messages.unpublish(mGoogleApiClient, mMessage);
            Nearby.Messages.unsubscribe(mGoogleApiClient, new MessageListener() {
                @Override
                public void onFound(Message message) {

                }
            });
        }
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @OnClick(R.id.btn_publisher)
    public void publishMessage() {
        if (mGoogleApiClient.isConnected()) {
            Log.d(TAG, "publishing message: " + new String(mMessage.getContent()));
            Nearby.Messages.publish(mGoogleApiClient, mMessage).setResultCallback(new ErrorCheckingCallback("publish"));
        }
    }

    @OnClick(R.id.btn_listener)
    public void listenForMessage() {
        if (mGoogleApiClient.isConnected()) {
            // Subscribe to receive
            Log.d(TAG, "listening for messages");
            Nearby.Messages.subscribe(mGoogleApiClient, new MessageListener() {
                @Override
                public void onFound(Message message) {
                    final String msg = new String(message.getContent());
                    Log.d(TAG, "onFound:" + msg);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTxtReceiver.setText(mTxtReceiver.getText() + "\n" + msg);
                        }
                    });
                }
            }).setResultCallback(new ErrorCheckingCallback("listen"));
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d(TAG, "Google Api onConnected");
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "Google Api onConnectionSuspended");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "Google Api onConnectionFailed");
    }


    // This is called in response to a button tap in the Nearby permission dialog.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_RESOLVE_ERROR) {
            mResolvingError = false;
            if (resultCode == RESULT_OK) {
                // Permission granted or error resolved successfully then we proceed
                // with publish and subscribe..
                Log.d(TAG, "permission granted");
            } else {
                // This may mean that user had rejected to grant nearby permission.
                Log.d(TAG, "permission denied");
            }
        }
    }

    /**
     * A simple ResultCallback that displays a toast when errors occur.
     * It also displays the Nearby opt-in dialog when necessary.
     */
    private class ErrorCheckingCallback implements ResultCallback<Status> {
        private final String method;
        private final Runnable runOnSuccess;

        private ErrorCheckingCallback(String method) {
            this(method, null);
        }

        private ErrorCheckingCallback(String method, @Nullable Runnable runOnSuccess) {
            this.method = method;
            this.runOnSuccess = runOnSuccess;
        }

        @Override
        public void onResult(@NonNull Status status) {
            if (status.isSuccess()) {
                Log.i(TAG, method + " succeeded.");
                if (runOnSuccess != null) {
                    runOnSuccess.run();
                }
            } else {
                // Currently, the only resolvable error is that the device is not opted
                // in to Nearby. Starting the resolution displays an opt-in dialog.
                if (status.hasResolution()) {
                    if (!mResolvingError) {
                        try {
                            Log.d(TAG, "permission not enabled, requesting permission now");
                            status.startResolutionForResult(NearbyBeaconActivity.this, REQUEST_RESOLVE_ERROR);
                            mResolvingError = true;
                        } catch (IntentSender.SendIntentException e) {
                        }
                    } else {
                        // This will be encountered on initial startup because we do
                        // both publish and subscribe together.  So having a toast while
                        // resolving dialog is in progress is confusing, so just log it.
                        Log.i(TAG, method + " failed with status: " + status
                                + " while resolving error.");
                    }
                } else {
                }
            }
        }
    }

}
