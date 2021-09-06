package com.project.imdb_clone_app;

import android.provider.BaseColumns;

public class Contract_Achievement {
    // Tables and Column names
    public static final String TABLE_NAME = "achievement";
    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_ACTOR_NAME = "actor_name";
    public static final String COLUMN_AWARD = "award";
    public static final String COLUMN_YEAR = "year";

    // SQL queries
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "(" +
                    COLUMN_ID + " INTEGER, " +
                    COLUMN_ACTOR_NAME + " TEXT, "+
                    COLUMN_AWARD + " TEXT, " +
                    COLUMN_YEAR + " TEXT, "+
                     "PRIMARY KEY("+COLUMN_ID+", "+COLUMN_ACTOR_NAME+", "+COLUMN_AWARD+"))";

    public static final String DELETE_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static final String READ_ALL_TABLE = "SELECT * FROM "+ TABLE_NAME;
}
