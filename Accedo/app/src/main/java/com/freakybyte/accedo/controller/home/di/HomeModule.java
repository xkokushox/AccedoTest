package com.freakybyte.accedo.controller.home.di;

import com.freakybyte.accedo.controller.home.constructors.HomeView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jose Torres in FreakyByte on 25/06/16.
 */
@Module
public class HomeModule {

    private final HomeView mView;

    public HomeModule(HomeView view) {
        mView = view;
    }

    @Provides
    HomeView provideTasksContractView() {
        return mView;
    }

}
