package com.project.imdb_clone_app;

import android.provider.BaseColumns;

public class Contract_Favourite {
    // Tables and Column names
    public static final String TABLE_NAME = "favourites";
    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_USER_NAME = "user";
    public static final String COLUMN_MOVIE_NAME = "movie";

    // SQL queries
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "(" +
                    COLUMN_ID + " INTEGER, " +
                    COLUMN_USER_NAME + " TEXT, " +
                    COLUMN_MOVIE_NAME + " TEXT, "+
                    "PRIMARY KEY("+COLUMN_USER_NAME+", "+COLUMN_MOVIE_NAME+"))";

    public static final String DELETE_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static final String READ_ALL_TABLE = "SELECT * FROM "+ TABLE_NAME;
}
