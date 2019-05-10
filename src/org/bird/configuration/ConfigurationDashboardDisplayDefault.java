package org.bird.configuration;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.bird.configuration.exceptions.ConfigurationException;
import org.bird.utils.Utils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Map;

/**
 * Cette classe étend la classe Configuration
 * Permet d'ajouter des tâches sur des paramêtres spécifiques des fichiers de configuration.
 * Ici permet de placer par défaut le type de container charger dans le dashboard
 */
public class ConfigurationDashboardDisplayDefault extends Configuration {

    public final String PATH_DASHBOARD_DISPLAY_DEFAULT = "layout.dashboard_display_default";

    /**
     * Contructeur
     *
     * @param jsonElement
     * @param pathFileName
     */
    public ConfigurationDashboardDisplayDefault(JsonElement jsonElement, Path pathFileName) {
        super(jsonElement, pathFileName);
    }

    /**
     * Va fixer par défaut le chemin passé en paramètre
     * @param key
     * @throws ConfigurationException
     * @throws IOException
     */
    public void setDefault(String key) throws ConfigurationException, IOException {
        JsonObject parent = getParent(PATH_DASHBOARD_DISPLAY_DEFAULT+"."+key);
        if (parent.isJsonObject()){
            Iterator<Map.Entry<String, JsonElement>> iterator = parent.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<String, JsonElement> entry = iterator.next();
                if (Utils.findString(entry.getKey(), key)){
                    edit(PATH_DASHBOARD_DISPLAY_DEFAULT+"."+entry.getKey(), true);
                } else {
                    edit(PATH_DASHBOARD_DISPLAY_DEFAULT+"."+entry.getKey(), false);
                }
            }
            write();
        }
    }
}
