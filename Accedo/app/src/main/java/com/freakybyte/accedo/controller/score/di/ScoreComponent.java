package com.freakybyte.accedo.controller.score.di;

import com.freakybyte.accedo.controller.score.ui.ScoreActivity;
import com.freakybyte.accedo.di.component.SqliteComponent;
import com.freakybyte.accedo.di.module.WidgetModule;
import com.freakybyte.accedo.di.scope.ActivityScoped;

import dagger.Component;

/**
 * Created by Jose Torres in FreakyByte on 26/06/16.
 */
@ActivityScoped
@Component(dependencies = SqliteComponent.class, modules = {ScoreModule.class, WidgetModule.class})
public interface ScoreComponent {
    void inject(ScoreActivity activity);
}
