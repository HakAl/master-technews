package com.jacmobile.technews.injection;

import android.app.Application;

import com.jacmobile.technews.injection.Modules.TechNewsModule;

import java.util.ArrayList;
import java.util.List;

import dagger.ObjectGraph;

/**
 * Created by User on 7/19/2014.
 */
public abstract class DaggerApplication extends Application implements DaggerInjector {

    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        TechNewsModule techNewsModule = new TechNewsModule();

        // bootstrap. So that it allows no-arg constructor in techNewsModule
        techNewsModule.applicationContext = this.getApplicationContext();

        List<Object> modules = new ArrayList<Object>();
        modules.add(techNewsModule);
        modules.addAll(getAppModules());

        this.objectGraph = ObjectGraph.create(modules.toArray());
        this.objectGraph.inject(this);
    }

    protected abstract List<Object> getAppModules();

    public void inject(Object object) {
        this.objectGraph.inject(object);
    }

    public ObjectGraph getObjectGraph() {
        return this.objectGraph;
    }
}