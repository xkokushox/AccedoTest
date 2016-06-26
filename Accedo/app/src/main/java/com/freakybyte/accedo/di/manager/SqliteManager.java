package com.freakybyte.accedo.di.manager;

import android.content.Context;

import com.freakybyte.accedo.db.ScoreDAO;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Jose Torres in FreakyByte on 25/06/16.
 */
@Singleton
public class SqliteManager {

    private ScoreDAO mScoreDao;
    private Context mContext;

    @Inject
    public SqliteManager(Context context) {
        mContext = context;
    }

    public ScoreDAO getScoreDao() {
        if (mScoreDao == null)
            mScoreDao = new ScoreDAO(mContext);
        return mScoreDao;
    }

}
