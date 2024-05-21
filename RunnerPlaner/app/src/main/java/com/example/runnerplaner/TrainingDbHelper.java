package com.example.runnerplaner;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class TrainingDbHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_TRAINING  = "CREATE TABLE " + TrainingContract.TrainingEntry.TABLE_NAME +
            " (" + TrainingContract.TrainingEntry._ID + "INTEGER PRIMARY KEY, " +
            TrainingContract.TrainingEntry.COLUMN_TIME + " TEXT," + TrainingContract.TrainingEntry.COLUMN_DATE + " TEXT,"+
            TrainingContract.TrainingEntry.COLUMN_TYPE + " TEXT," + TrainingContract.TrainingEntry.COLUMN_DISTANCE + " TEXT" + ")";

    private static final String SQL_DELETE_TRAINING = "DROP TABLE IF EXISTS " +   TrainingContract.TrainingEntry.TABLE_NAME;

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "training.db";

    public TrainingDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TrainingContract.TrainingEntry.TABLE_NAME);
        Log.d("Database", "Creating new database");
        sqLiteDatabase.execSQL(SQL_CREATE_TRAINING);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TRAINING);
        onCreate(db);
    }






}
