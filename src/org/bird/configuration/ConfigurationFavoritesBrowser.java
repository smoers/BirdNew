package org.bird.configuration;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.bird.configuration.exceptions.ConfigurationException;

import java.io.IOException;
import java.nio.file.Path;

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

    public JsonArray getFavorites() throws ConfigurationException {
        return get(Paths.FAVORITES_BROWSER).getAsJsonArray();
    }

    public void addFavorites(JsonObject jsonObject) throws ConfigurationException, IOException {
        JsonArray jsonArray = getFavorites();
        jsonArray.add(jsonObject);
        edit(Paths.FAVORITES_BROWSER,jsonArray);
        write();
    }

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

    public void removeFavorites(JsonObject jsonObject) throws ConfigurationException, IOException {
        JsonArray jsonArray = getFavorites();
        jsonArray.remove(jsonObject);
        edit(Paths.FAVORITES_BROWSER,jsonArray);
        write();
    }
}
