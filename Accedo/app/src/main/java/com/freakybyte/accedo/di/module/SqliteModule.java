package com.freakybyte.accedo.di.module;

import android.content.Context;

import com.freakybyte.accedo.di.manager.SqliteManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jose Torres in FreakyByte on 25/06/16.
 */
@Module
public class SqliteModule {

    private final Context app;

    public SqliteModule(Context app) {
        this.app = app;
    }

    @Provides
    @Singleton
    SqliteManager provideSqliteManager() {
        return new SqliteManager(app);
    }
}
