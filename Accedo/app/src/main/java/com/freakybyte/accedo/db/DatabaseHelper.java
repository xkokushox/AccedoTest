package com.freakybyte.accedo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.freakybyte.accedo.util.DebugUtils;

/**
 * Created by Jose Torres in FreakyByte on 25/06/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context, String dbName, int dbVersion) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ScoreDAO.QUERY_TABLE);
        DebugUtils.getSingleton().logDebug("The Database is created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}