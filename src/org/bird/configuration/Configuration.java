package org.bird.configuration;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.bird.configuration.exceptions.ConfigurationException;
import org.bird.utils.Utils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Cette classe représente un fichier de configuration json
 * Elle est instancifiée par la class ConfigurationBuilder
 */
public class Configuration {

    public enum Paths {
        DASHBOARD_DISPLAY_DEFAULT_TYPE("layout.dashboard_display_default.type"),
        DASHBOARD_DISPLAY_DEFAULT_MODE("layout.dashboard_display_default.mode");

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


}
