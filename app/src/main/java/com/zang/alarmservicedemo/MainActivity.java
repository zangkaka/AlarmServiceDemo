package com.zang.alarmservicedemo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

/**
 * Created by Rang on 28-Apr-20.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TimePicker mTimePicker;
    private Button mSetTimeBtn;
    private Button mCancelBtn;
    private TextView mResultTxt;
    private Calendar mCalendar;
    private AlarmManager mAlarmManager;
    private PendingIntent mPendingIntent;
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init view
        initViews();

        mAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        mIntent = new Intent(this, AlarmReceiver.class);
    }

    private void initViews() {
        mTimePicker = findViewById(R.id.main_time_picker);
        mSetTimeBtn = findViewById(R.id.main_set_time);
        mCancelBtn = findViewById(R.id.main_cancel_time);
        mResultTxt = findViewById(R.id.main_result_txt);
        mCalendar = Calendar.getInstance();

        mSetTimeBtn.setOnClickListener(this);
        mCancelBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_set_time:
                mCalendar.set(Calendar.HOUR_OF_DAY, mTimePicker.getCurrentHour());
                mCalendar.set(Calendar.MINUTE, mTimePicker.getCurrentMinute());
                String str_minute = String.valueOf(mTimePicker.getCurrentMinute());

                if (mTimePicker.getCurrentMinute() < 10) {
                    str_minute = "0" + mTimePicker.getCurrentMinute();
                }
                //B1
                mIntent.putExtra(Common.KEY_ALARM, Common.VALUE_ON_MUSIC);
                mPendingIntent = PendingIntent.getBroadcast(
                        MainActivity.this, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                mAlarmManager.set(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), mPendingIntent);

                mResultTxt.setText("Alarm: " + mTimePicker.getCurrentHour() + ":" + str_minute);
                break;
            case R.id.main_cancel_time:
                mResultTxt.setText("Stop Alarm");
                mAlarmManager.cancel(mPendingIntent);
                mIntent.putExtra(Common.KEY_ALARM, Common.VALUE_OFF_MUSIC);
                sendBroadcast(mIntent);
                break;
            default:
                break;
        }
    }
}
