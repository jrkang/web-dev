package kr.web.dev.common.properties;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

public class ResourceControl extends Control
{
    public ResourceBundle newBundle(
        String baseName,
        Locale locale,
        String format,
        ClassLoader loader,
        boolean reload)
    throws IllegalAccessException, InstantiationException, IOException
    {
        // The below is a copy of the default implementation.
        String bundleName = toBundleName(baseName, locale);
        System.out.println("############# bundleName["+bundleName+"]");
        String resourceName = toResourceName(bundleName, "properties");
        System.out.println("############# resourceName["+resourceName+"]");
        ResourceBundle bundle = null;
        InputStream stream = null;

        // FORCE RELOAD because needsReload doesn't work and reload is always false
        reload = true;

        if (reload) {
            URL url = loader.getResource(resourceName);
            if (url != null) {
                URLConnection connection = url.openConnection();
                if (connection != null) {
                	//System.out.println("############# connection["+connection+"]");
                    connection.setUseCaches(false);
                    stream = connection.getInputStream();
                }
            }
        }
        else {
            stream = loader.getResourceAsStream(resourceName);
        }

        if (stream != null) {
            try {
                // Only this line is changed to make it to read properties files as UTF-8.
                bundle = new PropertyResourceBundle(new InputStreamReader(stream, "UTF-8"));
            }
            finally {
                stream.close();
            }
        }
        return bundle;
    }

    // ASK NOT TO CACHE
    public long getTimeToLive(String arg0, Locale arg1) {
        return TTL_DONT_CACHE;
    }
}
