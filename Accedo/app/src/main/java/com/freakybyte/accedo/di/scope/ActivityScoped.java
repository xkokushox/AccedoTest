package com.freakybyte.accedo.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Jose Torres in FreakyByte on 25/06/16.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScoped {
}
