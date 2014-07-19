package com.jacmobile.technews.injection.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by User on 7/19/2014.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationScope { }