package com.example.runnerplaner;

import androidx.annotation.NonNull;

public class OneTraining {

    private int time, distance;
    private String type, date;

    public OneTraining(int time, int distance, String type, String date){
        this.date=date;
        this.distance=distance;
        this.time=time;
        this.type=type;
    }
    public int getTime(){
        return time;
    }

    public int getDistance() {
        return distance;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }


    @Override
    public String toString() {
        return "Training { "+ "date: " + date + ", type: "+type + ", distance: " + distance + ", time: " + time+" }";
    }
}
