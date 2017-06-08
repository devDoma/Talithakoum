package com.example.doma.talithakoum;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;

import java.util.Date;

/**
 * Created by doma on 2017-05-15.
 */

public class AlarmSetter {
   Context mContext;
    public AlarmSetter(Context context){
        mContext = context;
    }


    void setAlarm(int hour, int minute){
        Intent mAlarmIntent = new Intent("com.example.doma.talithakoum");

        java.util.Calendar mcalendar = java.util.Calendar.getInstance();
        mcalendar.set(java.util.Calendar.HOUR_OF_DAY, hour);
        mcalendar.set(java.util.Calendar.MINUTE, minute);
        mcalendar.set(java.util.Calendar.SECOND, 0);





        final PendingIntent mPendingIntent = PendingIntent.getBroadcast(mContext, 0, mAlarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        final AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,mcalendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, mPendingIntent);
            alarmManager.setExact(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+5000, mPendingIntent);
        }
    }
}
