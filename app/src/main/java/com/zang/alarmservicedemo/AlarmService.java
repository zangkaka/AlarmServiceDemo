package com.zang.alarmservicedemo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

/**
 * Created by Rang on 28-Apr-20.
 */
public class AlarmService extends Service {
    MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //B3
        Log.d("==aa", "onStartCommand: ");
        String str = intent.getStringExtra(Common.KEY_ALARM_SERVICE);
        if (TextUtils.equals(str, Common.VALUE_ON_MUSIC)) {
            mediaPlayer = MediaPlayer.create(this, R.raw.ring);
            mediaPlayer.start();
            Toast.makeText(this, "Ring Ring", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.equals(str, Common.VALUE_OFF_MUSIC)) {
            mediaPlayer.stop();
            mediaPlayer.reset();
        }

        return START_NOT_STICKY;
    }
}
