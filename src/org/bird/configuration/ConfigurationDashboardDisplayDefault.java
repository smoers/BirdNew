package org.bird.configuration;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.bird.configuration.exceptions.ConfigurationException;
import org.bird.utils.Utils;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Cette classe étend la classe Configuration
 * Permet d'ajouter des tâches sur des paramêtres spécifiques des fichiers de configuration.
 * Ici permet de placer par défaut le type de container charger dans le dashboard
 */
public class ConfigurationDashboardDisplayDefault extends Configuration {
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
     * @param path
     * @throws ConfigurationException
     * @throws IOException
     */
    public void setDefault(String path) throws ConfigurationException, IOException {
        JsonObject parent = getParent(path);
        if (parent.isJsonArray()){
            for (JsonElement jsonElement : parent.getAsJsonArray()) {
                if (Utils.findString(jsonElement.getAsString(), path)){
                    edit(path, true);
                } else {
                    edit(path, false);
                }
            }
            write();
        }
    }
}
