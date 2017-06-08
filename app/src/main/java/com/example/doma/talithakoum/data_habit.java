package com.example.doma.talithakoum;

/**
 * Created by doma on 2017-04-27.
 */

public class data_habit {

    public data_habit(String goal, int hour, int minute){

    }

    private String goal;
    private int hour;
    private int minute;



    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public String getGoal() {
        return goal;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }


    public void setGoal(String goal) {
        this.goal = goal;
    }
}
