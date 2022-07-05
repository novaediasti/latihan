package com.nova.hellowordapps;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class CatatanSQLHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "catatan_db";
    public static final int VERSION = 1;
    public static final String TASKS_TABLE = "customer";
    public static final String TASK_ID = "id";
    public static final String TASK_TANGGAL = "tanggal";
    public static final String TASK_JAM = "jam";
    public static final String TASK_CATATAN = "catatan";
    public static final String TASK_COMPLETE = "complete";
    public CatatanSQLHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db);
    }
    private void createTable(SQLiteDatabase db) {
        db.execSQL("create table " + TASKS_TABLE + " ( " +
                TASK_ID + " integer primary key autoincrement not null, " +
                TASK_TANGGAL + " text, " +
                TASK_JAM + " text, " +
                TASK_CATATAN + " text, " +
                TASK_COMPLETE + " text " +
                ");"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
    }
}