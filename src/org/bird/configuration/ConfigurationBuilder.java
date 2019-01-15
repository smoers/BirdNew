package org.bird.configuration;

import com.google.common.io.Files;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;


import java.io.*;
import java.util.HashMap;

public class ConfigurationBuilder {

    public static final String DEFAULT_CONFIGURATION_FOLDER  = "config/";
    private HashMap<String, Configuration> configurations;
    private static ConfigurationBuilder ourInstance = new ConfigurationBuilder();

    public static ConfigurationBuilder getInstance() {
        return ourInstance;
    }

    private ConfigurationBuilder() {
        configurations = new HashMap<String, Configuration>();
    }

    public void load(String key, String pathFileName) throws FileNotFoundException {
        JsonReader jsonReader = new JsonReader(new InputStreamReader(new FileInputStream(pathFileName)));
        Configuration configuration = new Configuration(new JsonParser().parse(jsonReader));
        configurations.put(key,configuration);
    }

    public void autoLoad(String folder) {
        Files.fileTreeTraverser().breadthFirstTraversal(new File(folder)).forEach(path -> {
            if(Files.getFileExtension(path.getPath()).equalsIgnoreCase("json")){
                String key = Files.getNameWithoutExtension(path.getPath());
                String pathFileName = path.getPath();
                try {
                    load(key,pathFileName);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public Configuration get(String key) {
        return configurations.get(key);
    }


}
