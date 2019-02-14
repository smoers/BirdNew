package org.bird.configuration;

import com.google.gson.JsonElement;
import org.bird.configuration.exceptions.ConfigurationException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Cette classe représente un fichier de configuration json
 * Elle est instancifiée par la class ConfigurationBuilder
 */
public class Configuration {

    private JsonElement jsonElement;

    /**
     * Contructeur
     * @param jsonElement
     */
    public Configuration(JsonElement jsonElement) {
        this.jsonElement = jsonElement;
    }

    /**
     * Retourne l'objet JsonElement représentant le fichier de configuration dans son entièreté
     * @return
     */
    public JsonElement get(){
        return jsonElement;
    }

    /**
     * Retourne une objet JsonElement représentant une partie du fichier de condiguration et ce au départ d'un chemin
     *
     * @param path
     * @return
     */
    public JsonElement get(String path) throws ConfigurationException {
        JsonElement jsonElement = this.jsonElement;
        JsonElement response = null;
        String delimiter = "\\.";
        ArrayList<String> keys = new ArrayList<String>(Arrays.asList(path.split(delimiter)));
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            if(!jsonElement.isJsonNull() && jsonElement.isJsonObject()){
                jsonElement = jsonElement.getAsJsonObject().get(key);
                response = jsonElement;
            }
        }
        if (response == null){
            throw new ConfigurationException(8003);
        }
        return response;
    }
}
