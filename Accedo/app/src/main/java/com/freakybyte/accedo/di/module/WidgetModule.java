package com.freakybyte.accedo.di.module;

import android.content.Context;

import com.freakybyte.accedo.di.manager.WidgetManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jose Torres in FreakyByte on 25/06/16.
 */
@Module
public class WidgetModule {
    private final Context app;

    public WidgetModule(Context app) {
        this.app = app;
    }

    @Provides
    WidgetManager provideWidgetManager() {
        return new WidgetManager(app);
    }
}
