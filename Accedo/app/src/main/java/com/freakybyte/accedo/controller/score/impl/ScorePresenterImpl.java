package com.freakybyte.accedo.controller.score.impl;

import com.freakybyte.accedo.controller.score.constructors.ScorePresenter;
import com.freakybyte.accedo.controller.score.constructors.ScoreView;
import com.freakybyte.accedo.di.manager.SqliteManager;

import javax.inject.Inject;

/**
 * Created by Jose Torres in FreakyByte on 26/06/16.
 */
public class ScorePresenterImpl implements ScorePresenter {

    private ScoreView mScoreView;

    private SqliteManager mSqliteManager;


    @Inject
    public ScorePresenterImpl(SqliteManager mSqliteManager, ScoreView screenView) {
        mScoreView = screenView;
        this.mSqliteManager = mSqliteManager;
    }

    @Override
    public void getHighScore() {
        if (mScoreView == null)
            return;

        mScoreView.setItemsToAdapter(mSqliteManager.getScoreDao().getAllScores());
        mScoreView.refreshAdapter();
    }

    @Override
    public void onDestroy() {
        mScoreView = null;
    }
}
