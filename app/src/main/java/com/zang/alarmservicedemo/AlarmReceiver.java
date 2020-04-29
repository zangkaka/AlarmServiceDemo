package com.zang.alarmservicedemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Rang on 28-Apr-20.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //B2
        String str = intent.getStringExtra(Common.KEY_ALARM);
        Intent intentService = new Intent(context, AlarmService.class);
        intentService.putExtra(Common.KEY_ALARM_SERVICE, str);
        context.startService(intentService);

    }
}
