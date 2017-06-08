package com.example.doma.talithakoum;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by doma on 2017-05-11.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent mServiceIntent = new Intent(context, AlarmSoundService.class);
        context.startService(mServiceIntent);

    }
}
