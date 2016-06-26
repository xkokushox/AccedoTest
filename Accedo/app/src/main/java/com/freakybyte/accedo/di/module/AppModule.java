package com.freakybyte.accedo.di.module;

import android.app.Application;

import com.freakybyte.accedo.AccedoApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jose Torres in FreakyByte on 25/06/16.
 */
@Module
public class AppModule {

    private final AccedoApplication app;

    public AppModule(AccedoApplication app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public Application providesApplication() {
        return app;
    }
}
