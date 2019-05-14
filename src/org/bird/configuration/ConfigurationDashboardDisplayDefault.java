package org.bird.configuration;

import com.google.common.base.Joiner;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.bird.configuration.exceptions.ConfigurationException;
import org.bird.utils.Utils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Map;
import java.util.StringJoiner;

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
     * @param key
     * @throws ConfigurationException
     * @throws IOException
     */
    public void setDefault(Paths path, String key) throws ConfigurationException, IOException {
        JsonObject parent = getParent(path.getPath()+"."+key);
        if (parent.isJsonObject()){
            Iterator<Map.Entry<String, JsonElement>> iterator = parent.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<String, JsonElement> entry = iterator.next();
                if (Utils.findString(entry.getKey(), key)){
                    edit(path.getPath()+"."+entry.getKey(), true);
                } else {
                    edit(path.getPath()+"."+entry.getKey(), false);
                }
            }
            write();
        }
    }

    /**
     * Retourne la valeur depuis le path et la key
     * @param path
     * @param key
     * @return
     * @throws ConfigurationException
     */
    public Boolean getDefault(Paths path,String key) throws ConfigurationException {
        return get(path.getPath()+"."+key).getAsBoolean();

    }

    /**
     * Retourne une chaine de catractères sous le format item_book, list_book, item_author, list_author
     * @return
     * @throws ConfigurationException
     */
    public String getDefault() throws ConfigurationException {
        StringJoiner joiner = new StringJoiner("_");
        joiner.add(getDefault(Paths.DASHBOARD_DISPLAY_DEFAULT_MODE));
        joiner.add(getDefault(Paths.DASHBOARD_DISPLAY_DEFAULT_TYPE));
        return joiner.toString();
    }

    /**
     * Retourne la clé qui est true depuis le peth
     * @param path
     * @return
     * @throws ConfigurationException
     */
    public String getDefault(Paths path) throws ConfigurationException {
        JsonElement element = get(path.getPath());
        if (element.isJsonObject()){
            Iterator<Map.Entry<String, JsonElement>> iterator = element.getAsJsonObject().entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<String, JsonElement> entry = iterator.next();
                if (entry.getValue().getAsBoolean()){
                    return entry.getKey();
                }
            }
        }
        return "";
    }
}
