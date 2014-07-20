package com.jacmobile.technews.injection.Modules;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.jacmobile.technews.activities.MainActivity;
import com.jacmobile.technews.injection.annotations.ApplicationScope;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by User on 7/19/2014.
 */

@Module(
        complete = true, //Enables object graph validation
        library = true,
        addsTo = TechNewsModule.class, //Needed for object graph validation at runtime
        injects = {
//                MainActivity.class
        }
)
public class ApplicationScopeModule
{

    @Provides
    @Singleton
    RequestQueue providesNetworkRequestQueue(@ApplicationScope Context context)
    {
        return Volley.newRequestQueue(context);
    }


    @Provides
    @Singleton
    Serializer provideXmlSerializer()
    {
        return new Persister();
    }
}