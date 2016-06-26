package com.freakybyte.accedo.db;

import android.content.Context;

/**
 * Created by Jose Torres in FreakyByte on 25/06/16.
 */
public class ScoreDAO {

    private DBAdapter dbAdapter;
    private boolean success = true;

    public final static String TABLE_NAME = "table_score";
    public final static String ID_PK = "score_pk";
    private String conditional = "";
    private boolean empty = true;


    public static final String QUERY_TABLE = "" + "CREATE TABLE " + TABLE_NAME
            + " (" + ID_PK + " INTEGER PRIMARY KEY autoincrement, " + "NAME TEXT, " + "SCORE INTEGER);";


    public ScoreDAO(Context context) {
        dbAdapter = new DBAdapter(context);
    }

}
