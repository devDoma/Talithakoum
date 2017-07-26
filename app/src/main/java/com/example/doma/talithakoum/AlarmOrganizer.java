package com.example.doma.talithakoum;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;


/**
 * Created by doma on 2017-05-15.
 */

public class AlarmOrganizer {
    Context mContext;
    ArrayList<data_alarm> alarmArrayList = new ArrayList<>();

    int bookedhour;
    int bookedmin;


    public AlarmOrganizer(Context context) {
        mContext = context;
    }


    void setAlarm(String alarm_name, String alarm_content, int alarm_ex_day,
                  int hour, int minute) {
// 객체를 어레이리스트에 추가하는 메소드.

        data_alarm dataAlarm = new data_alarm();

        dataAlarm.setAlarm_content(alarm_content);
        dataAlarm.setAlarm_ex_theDay(alarm_ex_day);
        dataAlarm.setAlarm_hour(hour);
        dataAlarm.setAlarm_minute(minute);
        dataAlarm.setAlarm_name(alarm_name);

        alarmArrayList.add(dataAlarm);

    }


    void call_alarm(ArrayList<data_alarm> alarmArrayList) {
        // data_alarm의 리스트 중에서 가장 빠른 알람을 확인하고 알람 예약하는 메소드.
//다음에 울릴 알람이 무엇인지 리스트에서 확인해서 예약한다. 이번 포스팅과는 무관하다.

        int i = 0;
        int finalHour = -1;
        int finalMinute = -1;

        Calendar today = Calendar.getInstance();

        int CurrentHour =
                today.get(Calendar.HOUR_OF_DAY);
        int CurrentMinute = today.get(Calendar.MINUTE);
        int CurrentDay = today.get(Calendar.DAY_OF_WEEK);

        while (alarmArrayList.size() > i) {

            data_alarm dataAlarm = alarmArrayList.get(i);

            if (((CurrentDay != dataAlarm.getAlarm_ex_theDay())) && (CurrentHour <= dataAlarm.getAlarm_hour()) && (CurrentMinute < dataAlarm.getAlarm_minute())) {

                int hour = dataAlarm.getAlarm_hour();
                int minute = dataAlarm.getAlarm_minute();

                if ((finalHour < 0) || (finalMinute < 0)) {
                    finalHour = hour;
                    finalMinute = minute;
                } else if ((hour <= finalHour) && (minute <= finalMinute)) {
                    finalHour = hour;
                    finalMinute = minute;
                }
            }

            i++;

        }

        Intent mAlarmIntent = new Intent("com.example.doma.talithakoum");

        final PendingIntent mPendingIntent = PendingIntent.getBroadcast(mContext, 0, mAlarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        final android.app.AlarmManager alarmManager = (android.app.AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);

        java.util.Calendar mcalendar = java.util.Calendar.getInstance();
        mcalendar.set(java.util.Calendar.HOUR_OF_DAY, finalHour);
        mcalendar.set(java.util.Calendar.MINUTE, finalMinute);
        mcalendar.set(java.util.Calendar.SECOND, 0);


        bookedhour = finalHour;
        bookedmin = finalMinute;

        alarmManager.set(AlarmManager.RTC_WAKEUP, mcalendar.getTimeInMillis(), mPendingIntent);

    }

    //어레이리스트를 sharedPreferences에 저장하는 메소드.
    public void setArrayListPref(Context context, String key, ArrayList<data_alarm> values) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();

        String jArToStr = null;

        try {
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < values.size(); i++) {
                JSONObject sObj = new JSONObject();

                sObj.put("name", values.get(i).getAlarm_name());
                sObj.put("content", values.get(i).getAlarm_content());
                sObj.put("ex_theDay", values.get(i).getAlarm_ex_theDay());
                sObj.put("hour", values.get(i).getAlarm_hour());
                sObj.put("minute", values.get(i).getAlarm_minute());

                jsonArray.put(sObj);


            }
            jArToStr = jsonArray.toString();


        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (!values.isEmpty()) {
            editor.putString(key, jArToStr);
        } else {
            editor.putString(key, null);
        }

        editor.apply();
    }


    public ArrayList<data_alarm> getStringArrayPref(Context context, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String jsonStr = prefs.getString(key, null);

        ArrayList<data_alarm> data_alarmArrayList = new ArrayList<>();
        if (jsonStr != null) {
            try {
                JSONArray jsonArray = new JSONArray(jsonStr);
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject object = jsonArray.optJSONObject(i);
                    data_alarm dataAlarm = new data_alarm();
                    dataAlarm.setAlarm_name(String.valueOf(object.get("name")));
                    dataAlarm.setAlarm_content(String.valueOf(object.get("content")));
                    dataAlarm.setAlarm_ex_theDay((int) object.get("ex_theDay"));
                    dataAlarm.setAlarm_hour((int) object.get("hour"));
                    dataAlarm.setAlarm_minute((int) object.get("minute"));

                    data_alarmArrayList.add(dataAlarm);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return data_alarmArrayList;
    }


    public void removeAllPref(Context context, String key){
        SharedPreferences preferences = context.getSharedPreferences(key,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }
}