package org.bird.configuration;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.bird.configuration.exceptions.ConfigurationException;
import org.bird.utils.Utils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Cette classe représente un fichier de configuration json
 * Elle est instancifiée par la class ConfigurationBuilder
 */
public class Configuration {

    public enum Paths {
        DASHBOARD_DISPLAY_DEFAULT_TYPE("layout.dashboard_display_default.type"),
        DASHBOARD_DISPLAY_DEFAULT_MODE("layout.dashboard_display_default.mode"),
        FAVORITES_BROWSER("layout.browser.favorites");

        private String path = "";

        Paths(String path) {
            this.path = path;
        }
        public String getPath(){
            return path;
        }
    }

    private JsonElement jsonElement;
    private Path pathFileName;
    private JsonElement parentJsonElement;
    private final String property_regex = "(\\w+$)";

    /**
     * Contructeur
     * @param jsonElement
     */
    public Configuration(JsonElement jsonElement, Path pathFileName) {
        this.jsonElement = jsonElement;
        this.pathFileName = pathFileName;
    }

    /**
     * Retourne l'objet JsonElement représentant le fichier de configuration dans son entièreté
     * @return
     */
    public JsonElement get(){
        return jsonElement;
    }

    /**
     * Retourne une objet JsonElement représentant une partie du fichier de condiguration et ce au départ d'un enum
     * @param paths
     * @return
     * @throws ConfigurationException
     */
    public JsonElement get(Configuration.Paths paths) throws ConfigurationException {
        return get(paths.getPath());
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
        try {
            String delimiter = "\\.";
            ArrayList<String> keys = new ArrayList<String>(Arrays.asList(path.split(delimiter)));
            Iterator<String> iterator = keys.iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                if (!jsonElement.isJsonNull() && jsonElement.isJsonObject()) {
                    parentJsonElement = jsonElement;
                    jsonElement = jsonElement.getAsJsonObject().get(key);
                    response = jsonElement;
                } else if (!jsonElement.isJsonNull() && jsonElement.isJsonArray()){
                    Iterator<JsonElement> it = jsonElement.getAsJsonArray().iterator();
                    while (it.hasNext()){
                        response = it.next();
                        if (response.isJsonObject() && response.getAsJsonObject().get("id").getAsString().equalsIgnoreCase(key)){
                            break;
                        }
                    }
                }
            }
        } catch (Exception e){
            throw new ConfigurationException(8003);
        }
        if (response == null){
            throw new ConfigurationException(8003);
        }
        return response;
    }



    /**
     * Edite ou Ajoute un JsonElement
     * @param path
     * @param jsonElement
     * @throws ConfigurationException
     */
    public void edit(String path, JsonElement jsonElement) throws ConfigurationException {
        JsonObject jsonObject = getParent(path);
        if (jsonObject != null){
            String property = Utils.groupOneString(property_regex, path);
            jsonObject.add(property, jsonElement);
        }
    }

    /**
     * Edite ou Ajoute un JsonElement
     * @param paths
     * @param jsonElement
     * @throws ConfigurationException
     */
    public void edit(Configuration.Paths paths, JsonElement jsonElement) throws ConfigurationException { edit(paths.getPath(),jsonElement);}

    /**
     * Edite une propriété String
     * @param path
     * @param value
     * @throws ConfigurationException
     */
    public void edit(String path, String value) throws ConfigurationException {
        JsonObject jsonObject = getParent(path);
        if (jsonObject != null) {
            String property = Utils.groupOneString(property_regex, path);
            jsonObject.addProperty(property, value);
        }
    }

    /**
     * Edite une propriété String
     * @param paths
     * @param value
     * @throws ConfigurationException
     */
    public void edit(Configuration.Paths paths, String value) throws ConfigurationException { edit(paths.getPath(),value);}

    /**
     * Edite une propriété Number
     * @param path
     * @param value
     * @throws ConfigurationException
     */
    public void edit(String path, Number value) throws ConfigurationException {
        JsonObject jsonObject = getParent(path);
        if (jsonObject != null) {
            String property = Utils.groupOneString(property_regex, path);
            jsonObject.addProperty(property, value);
        }
    }

    /**
     * Edite une propriété Number
     * @param paths
     * @param value
     * @throws ConfigurationException
     */
    public void edit(Configuration.Paths paths, Number value) throws ConfigurationException { edit(paths.getPath(),value);}

    /**
     * Edite une propriété Character
     * @param path
     * @param value
     * @throws ConfigurationException
     */
    public void edit(String path, Character value) throws ConfigurationException {
        JsonObject jsonObject = getParent(path);
        if (jsonObject != null) {
            String property = Utils.groupOneString(property_regex, path);
            jsonObject.addProperty(property, value);
        }
    }

    /**
     * Edite une propriété Character
     * @param paths
     * @param value
     * @throws ConfigurationException
     */
    public void edit(Configuration.Paths paths, Character value) throws ConfigurationException { edit(paths.getPath(),value);}

    /**
     * Edite une propriété Character
     * @param path
     * @param value
     * @throws ConfigurationException
     */
    public void edit(String path, Boolean value) throws ConfigurationException {
        JsonObject jsonObject = getParent(path);
        if (jsonObject != null) {
            String property = Utils.groupOneString(property_regex, path);
            jsonObject.addProperty(property, value);
        }
    }

    /**
     * Edite une propriété Boolean
     * @param paths
     * @param value
     * @throws ConfigurationException
     */
    public void edit(Configuration.Paths paths, Boolean value) throws ConfigurationException { edit(paths.getPath(),value);}

    /**
     * Edite un tableau d'objet de type JsonObject
     * ATTENTION : Les objets Json doivent avoir un propriété nommée "id" dont la valeur est un String
     * @param path
     * @param properties
     * @throws ConfigurationException
     */
    public void edit(String path, ConfigurationProperty ... properties) throws ConfigurationException {
        JsonObject jsonObject = converterPropertiesToJsonObject(properties);
        if (null != jsonObject){
            String id = jsonObject.get("id").getAsString();
            JsonElement element = get(path);
            if (element.isJsonArray()){
                JsonElement removeElement = null;
                JsonArray jsonArray = element.getAsJsonArray();
                Iterator<JsonElement> it = jsonArray.iterator();
                while (it.hasNext()){
                    removeElement = it.next();
                    if (removeElement.isJsonObject() && (removeElement.getAsJsonObject().get("id").getAsString().equalsIgnoreCase(id))){
                        break;
                    }
                    removeElement = null;
                }
                if (null != removeElement){
                    jsonArray.remove(removeElement);
                }
                jsonArray.add(jsonObject);
            }
        }
    }

    /**
     * Edite un tableau d'objet de type JsonObject
     * ATTENTION : Les objets Json doivent avoir un propriété nommée "id" dont la valeur est un String
     * @param paths
     * @param properties
     * @throws ConfigurationException
     */
    public void edit(Paths paths, ConfigurationProperty ... properties) throws ConfigurationException {
        edit(paths.getPath(),properties);
    }

    /**
     * Retourne l'objet parent de la clé passé en paramètre
     * @param path
     * @return
     * @throws ConfigurationException
     */
    public JsonObject getParent(String path) throws ConfigurationException {
        JsonObject jsonObject = null;
        get(path);
        if (!parentJsonElement.isJsonNull() && parentJsonElement.isJsonObject()){
            jsonObject = parentJsonElement.getAsJsonObject();
        }
        if (jsonObject == null){
            throw new ConfigurationException(8004);
        }
        return jsonObject;
    }

    /**
     * Permet de sauvegarder la configuration
     * @throws IOException
     */
    public void write() throws IOException {
        ConfigurationBuilder builder = ConfigurationBuilder.getInstance();
        builder.write(this);
    }

    /**
     * Retourne le Path de cette configuration
     * @return
     */
    public Path getPathFileName() {
        return pathFileName;
    }

    public JsonObject converterPropertiesToJsonObject(ConfigurationProperty ... properties){
        JsonObject jsonObject = null;
        if (properties.length > 0){
            jsonObject = new JsonObject();
            for (ConfigurationProperty property : properties ) {
                if (property.getJsonPrimitive().isString()) {
                    jsonObject.addProperty(property.getName(),property.getJsonPrimitive().getAsString());
                } else if (property.getJsonPrimitive().isBoolean()){
                    jsonObject.addProperty(property.getName(),property.getJsonPrimitive().getAsBoolean());
                } else if (property.getJsonPrimitive().isNumber()){
                    jsonObject.addProperty(property.getName(),property.getJsonPrimitive().getAsNumber());
                } else {
                    jsonObject.addProperty(property.getName(),property.getJsonPrimitive().getAsCharacter());
                }
            }
        }
        return jsonObject;
    }


}
