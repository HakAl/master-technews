package com.jacmobile.technews.injection.Modules;

/**
 * Created by User on 7/19/2014.
 */

import android.content.Context;

import com.jacmobile.technews.injection.annotations.ApplicationScope;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(complete = false, library = true, injects = {})

public class TechNewsModule
{
    public static Context applicationContext = null;

    @Provides
    @Singleton
    @ApplicationScope
    Context provideApplicationContext()
    {
        return applicationContext;
    }
}
