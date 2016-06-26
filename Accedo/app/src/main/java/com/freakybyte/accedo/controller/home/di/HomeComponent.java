package com.freakybyte.accedo.controller.home.di;

import com.freakybyte.accedo.controller.home.ui.HomeActivity;
import com.freakybyte.accedo.di.component.SqliteComponent;
import com.freakybyte.accedo.di.module.WidgetModule;
import com.freakybyte.accedo.di.scope.ActivityScoped;

import dagger.Component;

/**
 * Created by Jose Torres in FreakyByte on 25/06/16.
 */
@ActivityScoped
@Component(dependencies = SqliteComponent.class, modules = {HomeModule.class, WidgetModule.class})
public interface HomeComponent {
    void inject(HomeActivity activity);
}