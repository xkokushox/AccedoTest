package com.freakybyte.accedo.di.component;

import com.freakybyte.accedo.di.manager.SqliteManager;
import com.freakybyte.accedo.di.module.AppModule;
import com.freakybyte.accedo.di.module.SqliteModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Jose Torres in FreakyByte on 25/06/16.
 */
@Singleton
@Component(modules = {SqliteModule.class, AppModule.class})
public interface SqliteComponent {
    SqliteManager getSqliteManager();
}

