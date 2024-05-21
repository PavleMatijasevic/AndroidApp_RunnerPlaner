package com.example.runnerplaner;

import android.provider.BaseColumns;

public final class TrainingContract {


    private TrainingContract() {}

    public static class TrainingEntry implements BaseColumns {
        public static final String TABLE_NAME = "trainings";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_DISTANCE = "distance";
        public static final String COLUMN_TIME = "time";
    }

}
