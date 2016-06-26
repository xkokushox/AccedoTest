package com.freakybyte.accedo.controller.score.di;

import com.freakybyte.accedo.controller.score.constructors.ScoreView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jose Torres in FreakyByte on 26/06/16.
 */
@Module
public class ScoreModule {

    private final ScoreView mView;

    public ScoreModule(ScoreView view) {
        mView = view;
    }

    @Provides
    ScoreView provideTasksContractView() {
        return mView;
    }
}
