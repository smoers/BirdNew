package org.bird.configuration;

import com.google.common.io.Files;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import org.bird.configuration.exceptions.ConfigurationException;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
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
        autoLoad(ConfigurationBuilder.DEFAULT_CONFIGURATION_FOLDER);
    }

    public void load(String key, Path pathFileName) throws FileNotFoundException {
        JsonReader jsonReader = new JsonReader(new InputStreamReader(new FileInputStream(pathFileName.toString())));
        Configuration configuration = new Configuration(new JsonParser().parse(jsonReader), pathFileName);
        configurations.put(key,configuration);
    }

    public void autoLoad(String folder) {
        Files.fileTreeTraverser().breadthFirstTraversal(new File(folder)).forEach(path -> {
            if(Files.getFileExtension(path.getPath()).equalsIgnoreCase("json")){
                String key = Files.getNameWithoutExtension(path.getPath());
                String pathFileName = path.getPath();
                try {
                    load(key,path.toPath());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     *
     * @param key
     * @return
     * @throws ConfigurationException
     */
    public Configuration get(String key) throws ConfigurationException {
        Configuration conf = null;
        if (configurations.containsKey(key)){
            conf = configurations.get(key);
        } else {
            throw new ConfigurationException(8002);
        }

        return conf;
    }

    public <T> T get(String key, Class configurationExtended) throws ConfigurationException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class[] cArg = new Class[2];
        cArg[0] = JsonElement.class;
        cArg[1] = Path.class;
        Configuration conf = get(key);
        T confExt = (T) configurationExtended.getClass().getDeclaredConstructor(cArg).newInstance(conf.get(), conf.getPathFileName());
        return confExt;
    }

    /**
     *
     * @param key
     * @return
     * @throws ConfigurationException
     */
    public JsonElement getElement(String key) throws ConfigurationException {
        JsonElement element = null;
        if (key.matches(".+::.+")){
            String[] split = key.split("::");
            Configuration conf = get(split[0]);
            element = conf.get(split[1]);
        } else {
            throw new ConfigurationException(8001);
        }
        return element;
    }

    public void write(Configuration configuration) throws IOException {
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(configuration.get());
        Files.write(json.getBytes(), configuration.getPathFileName().toFile());
    }


}
