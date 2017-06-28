package com.example.doma.talithakoum;
/**
 * Created by doma on 2017-06-11.
 */

public class data_alarm {
    private String alarm_name;
    private String alarm_content;
    private String alarm_hour;
    private String alarm_minute;
    private String alarm_ex_theDay;


    public int getAlarm_ex_theDay() {
        return Integer.valueOf(alarm_ex_theDay);
    }

    public int getAlarm_hour() {
        return Integer.valueOf(alarm_hour);
    }

    public int getAlarm_minute() {
        return Integer.valueOf(alarm_minute);
    }

    public String getAlarm_content() {
        return alarm_content;
    }

    public String getAlarm_name() {
        return alarm_name;
    }

    public void setAlarm_content(String alarm_content) {
        this.alarm_content = alarm_content;
    }

    public void setAlarm_ex_theDay(int alarm_ex_theDay) {
        this.alarm_ex_theDay = String.valueOf(alarm_ex_theDay);
    }

    public void setAlarm_hour(int alarm_hour) {
        this.alarm_hour =  String.valueOf(alarm_hour);
    }

    public void setAlarm_minute(int alarm_minute) {
        this.alarm_minute = String.valueOf(alarm_minute);
    }

    public void setAlarm_name(String alarm_name) {
        this.alarm_name = alarm_name;
    }

}