package com.freakybyte.accedo;

import android.app.Application;

import com.freakybyte.accedo.di.component.DaggerSqliteComponent;
import com.freakybyte.accedo.di.component.SqliteComponent;
import com.freakybyte.accedo.di.module.AppModule;
import com.freakybyte.accedo.di.module.SqliteModule;

/**
 * Created by Jose Torres in FreakyByte on 25/06/16.
 */
public class AccedoApplication extends Application {

    private SqliteComponent mSqliteComponent;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    public SqliteComponent getSqliteComponent() {
        if (mSqliteComponent == null)
            mSqliteComponent = DaggerSqliteComponent.builder().appModule(new AppModule(this)).sqliteModule(new SqliteModule(this)).build();
        return mSqliteComponent;
    }

}
