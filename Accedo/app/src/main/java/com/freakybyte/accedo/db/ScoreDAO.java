package com.freakybyte.accedo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.freakybyte.accedo.model.ScoreModel;
import com.freakybyte.accedo.util.DebugUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jose Torres in FreakyByte on 25/06/16.
 */
public class ScoreDAO {
    public static final String TAG = "ScoreDAO";

    private DBAdapter dbAdapter;
    private boolean success = true;
    private String conditional = "";
    private boolean empty = true;

    public final static String TABLE_NAME = "table_score";
    public final static String ID_PK = "score_pk";
    public final static String ID_NAME = "NAME";
    public final static String ID_SCORE = "SCORE";


    public final static String[] FIELDS = {"NAME", "SCORE"};

    public static final String QUERY_TABLE = "" + "CREATE TABLE " + TABLE_NAME
            + " (" + ID_PK + " INTEGER PRIMARY KEY autoincrement, " + "NAME TEXT, " + "SCORE INTEGER);";


    public ScoreDAO(Context context) {
        dbAdapter = new DBAdapter(context);
    }

    /**
     * Method that insert the score in the db
     *
     * @param score The score that is going to be saved in the data base
     */

    public boolean insertScore(ScoreModel score) {

        dbAdapter.begginTransaction();

        ContentValues cValues = getContentValues(score);

        long idInsert = dbAdapter.insert(TABLE_NAME, cValues);
        success = idInsert != -1;

        DebugUtils.getSingleton().logDebug(TAG, "Inserted Score:: " + String.valueOf(success));

        dbAdapter.setTransacctionSuccesfull();

        return success;
    }

    /**
     * Retrieves a list of all the scores saved in the db
     *
     */
    public List<ScoreModel> getAllScores() {

        List<ScoreModel> aListScore = new ArrayList<>();
        String orderBy = ID_SCORE + " DESC";


        try {

            dbAdapter.begginTransaction();

            Cursor cursor = dbAdapter.getData(TABLE_NAME, FIELDS, null, orderBy);

            int _NAME = cursor.getColumnIndex(ID_NAME);
            int _SCORE = cursor.getColumnIndex(ID_SCORE);


            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                ScoreModel mScore = new ScoreModel();
                mScore.setPosition(i + 1);
                mScore.setName(cursor.getString(_NAME));
                mScore.setScore(cursor.getInt(_SCORE));
                aListScore.add(mScore);
                cursor.moveToNext();
            }

            cursor.close();

            dbAdapter.setTransacctionSuccesfull();

        } catch (Exception ex) {
            DebugUtils.getSingleton().logError(TAG, ex);
        }

        DebugUtils.getSingleton().logDebug(TAG, "Get All Scores: " + String.valueOf(aListScore.size()));

        return aListScore;
    }

    private ContentValues getContentValues(ScoreModel score) {
        ContentValues cValues = new ContentValues();
        cValues.put(ID_NAME, score.getName());
        cValues.put(ID_SCORE, score.getScore());
        return cValues;
    }

}
