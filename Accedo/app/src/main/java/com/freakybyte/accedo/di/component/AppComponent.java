package com.freakybyte.accedo.di.component;

import android.app.Application;

import com.freakybyte.accedo.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Jose Torres in FreakyByte on 25/06/16.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(Application application);

}