package application;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.jacmobile.technews.injection.DaggerApplication;
import com.jacmobile.technews.injection.Modules.ApplicationScopeModule;

import java.util.Collections;
import java.util.List;

public class TechNews extends DaggerApplication
{
    public TechNews()
    {
        super();
    }

    @Override
    public void onLowMemory()
    {
        super.onLowMemory();
    }

    @Override
    public void onCreate() 
    {
        super.onCreate();
    }

    @Override
    protected List<Object> getAppModules()
    {
        return Collections.<Object>singletonList(new ApplicationScopeModule());
    }
}
