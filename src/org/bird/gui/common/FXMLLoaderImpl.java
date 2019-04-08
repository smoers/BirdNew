package org.bird.gui.common;

import javafx.fxml.FXMLLoader;
import org.bird.configuration.Configuration;
import org.bird.configuration.ConfigurationBuilder;
import org.bird.configuration.exceptions.ConfigurationException;
import org.bird.utils.Utils;

import java.util.StringJoiner;

/**
 * Cette classe se charge de créer une instance de l'objet FXMLLoader
 * avec un fichier fxml défini
 */
public class FXMLLoaderImpl {

    private String packageName;
    private Configuration configuration;

    public FXMLLoaderImpl() throws ConfigurationException {
        configuration = ConfigurationBuilder.getInstance().get("global");
        packageName = configuration.get("global.fxml.packagename").getAsString();
    }

    /**
     * Retourne le FXMLLoader pour le fichier fxml passé en paramêtre
     * @param fileName
     * @return
     */
    public FXMLLoader getFXMLLoader(String fileName){
        if ((!Utils.findString(".fxml$", fileName))){
            fileName = fileName + ".fxml";
        }
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(packageName + fileName));
        return loader;
    }

    /**
     * Retourne le FXMLLoader pour le fichier fxml passé en paramêtre
     * @param clazz
     * @return
     * @throws ConfigurationException
     */
    public FXMLLoader getFXMLLoader(Class ... clazz) throws ConfigurationException {
        String fileName = configuration.get(getPath(clazz)).getAsString();
        return getFXMLLoader(fileName);
    }

    /**
     * Construit la clé sur base des classes
     * @param clazz
     * @return
     */
    protected String getPath(Class ... clazz){
        StringJoiner joiner = new StringJoiner(".");
        joiner.add("global");
        joiner.add("fxml");
        joiner.add("fxmlloader");
        for (Class aClass : clazz) {
            joiner.add(aClass.getSimpleName());
        }
        return joiner.toString();
    }
}
