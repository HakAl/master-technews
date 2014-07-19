package com.jacmobile.technews.networking;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by User on 7/19/2014.
 */
public class ServiceManager
{
    private ServiceManager()
    {
        throw new AssertionError();
    } // Prevents instantiation (Item 4)

    // Maps service names to services
    private static final Map<String, NetworkProvider> providers = new ConcurrentHashMap<String, NetworkProvider>();
    public static final String DEFAULT_PROVIDER_NAME = "<def>";

    // Provider registration API
    public static void registerDefaultProvider(NetworkProvider p)
    {
        registerProvider(DEFAULT_PROVIDER_NAME, p);
    }

    public static void registerProvider(String name, NetworkProvider p)
    {
        providers.put(name, p);
    }

    // Service access API
    public static NetworkService newInstance()
    {
        return newInstance(DEFAULT_PROVIDER_NAME);
    }

    public static NetworkService newInstance(String name)
    {
        NetworkProvider p = providers.get(name);
        if (p == null)
            throw new IllegalArgumentException(
                    "No provider registered with name: " + name);
        return p.newService();
    }
}