package com.jacmobile.technews.injection.Modules;

import android.content.Context;

import com.jacmobile.technews.activities.MainActivity;
import com.jacmobile.technews.injection.annotations.ActivityScope;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by User on 7/19/2014.
 */
@Module(
        complete = true, //Enable object graph validation
        library = true,
        addsTo = ApplicationScopeModule.class,
        injects = {
        }
)
public class ActivityScopeModule
{
    private final MainActivity mainActivity;

    public ActivityScopeModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @Singleton
    @ActivityScope
    Context providesActivityContext() {
        return this.mainActivity;
    }
}
