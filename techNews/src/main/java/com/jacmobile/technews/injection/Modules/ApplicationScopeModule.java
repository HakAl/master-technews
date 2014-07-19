package com.jacmobile.technews.injection.Modules;

import dagger.Module;

/**
 * Created by User on 7/19/2014.
 */

@Module(
        complete = true, //Enables object graph validation
        library = true,
        addsTo = TechNewsModule.class, //Needed for object graph validation at runtime
        injects = {
//                TODO ADD ACTIVITIES
        }
)
public class ApplicationScopeModule
{

}