package com.project.imdb_clone_app;

import android.provider.BaseColumns;

public class Contract_WatchList implements  BaseColumns{
    // Tables and Column names
    public static final String TABLE_NAME = "watchlist";
    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_MOVIES = "movies";
    public static final String COLUMN_USERNAME   = "username";

    // SQL queries
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "(" +
                    COLUMN_ID + " INTEGER, " +
                    COLUMN_USERNAME + " TEXT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_MOVIES + " TEXT, " +
                    COLUMN_DESCRIPTION + " TEXT, "+
                    "PRIMARY KEY("+COLUMN_USERNAME+", "+COLUMN_NAME+"))";

    public static final String DELETE_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static final String READ_ALL_TABLE = "SELECT * FROM "+ TABLE_NAME;
}
