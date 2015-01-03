package com.jitse.example.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.jitse.example.R;
import com.jitse.example.receivers.AlarmReceiver;

import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class AlarmActivity extends ActionBarActivity {
    private static final String TAG = AlarmActivity.class.getName();
    private PendingIntent pendingIntent;

    @InjectView(R.id.tv)
    TextView mTextView;

    @InjectView(R.id.btn_cancel)
    Button mBtnCancel;

    int requestCode = 343;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        ButterKnife.inject(this);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 10);

        Intent myIntent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, requestCode, myIntent, 0);

        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);

        Log.d(TAG, "setting alarm for time: " + calendar.getTime());
        mTextView.setText("setting alarm for time: " + calendar.getTime());

    } //end onCreate

    @OnClick(R.id.btn_cancel)
    public void deleteAlarm() {
        AlarmManager alarmManager = (AlarmManager) this
                .getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, requestCode, intent, 0);
        alarmManager.cancel(pendingIntent);
        pendingIntent.cancel();
    }
}
