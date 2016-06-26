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

    @Inject
    public SqliteManager(Context context) {
        mScoreDao = new ScoreDAO(context);
    }
}
