package org.bird.configuration;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.bird.configuration.exceptions.ConfigurationException;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Etend la classe Configuration pour y ajouter les méthodes de gestion des Favoris
 */
public class ConfigurationFavoritesBrowser extends Configuration {
    /**
     * Contructeur
     *
     * @param jsonElement
     * @param pathFileName
     */
    public ConfigurationFavoritesBrowser(JsonElement jsonElement, Path pathFileName) {
        super(jsonElement, pathFileName);
    }

    /**
     * Liste des favoris
     * @return
     * @throws ConfigurationException
     */
    public JsonArray getFavorites() throws ConfigurationException {
        return get(Paths.FAVORITES_BROWSER).getAsJsonArray();
    }

    /**
     * Ajoute un favori
     * @param jsonObject
     * @throws ConfigurationException
     * @throws IOException
     */
    public void addFavorites(JsonObject jsonObject) throws ConfigurationException, IOException {
        JsonArray jsonArray = getFavorites();
        jsonArray.add(jsonObject);
        edit(Paths.FAVORITES_BROWSER,jsonArray);
        write();
    }

    /**
     * Edite un favori
     * @param jsonObject
     * @throws ConfigurationException
     * @throws IOException
     */
    public void editFavorites(JsonObject jsonObject) throws ConfigurationException, IOException {
        JsonArray jsonArray = getFavorites();
        jsonArray.forEach(action -> {
            if (action.isJsonObject()){
                JsonObject object = action.getAsJsonObject();
                if(jsonObject.get("id").getAsString().equalsIgnoreCase(object.get("id").getAsString())){
                    object.addProperty("name",jsonObject.get("name").getAsString());
                    object.addProperty("url", jsonObject.get("url").getAsString());
                }
            }
        });
        edit(Paths.FAVORITES_BROWSER,jsonArray);
        write();
    }

    /**
     * Supprime un favori
     * @param jsonObject
     * @throws ConfigurationException
     * @throws IOException
     */
    public void removeFavorites(JsonObject jsonObject) throws ConfigurationException, IOException {
        JsonArray jsonArray = getFavorites();
        jsonArray.remove(jsonObject);
        edit(Paths.FAVORITES_BROWSER,jsonArray);
        write();
    }
}
