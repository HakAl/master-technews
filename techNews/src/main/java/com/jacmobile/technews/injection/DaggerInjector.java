package com.jacmobile.technews.injection;

import dagger.ObjectGraph;

/**
 * Created by dgaffey on 3/31/14.
 */
public interface DaggerInjector {

    void inject(Object object);

    ObjectGraph getObjectGraph();
}
