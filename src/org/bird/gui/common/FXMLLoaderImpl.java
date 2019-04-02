package org.bird.gui.common;

import javafx.fxml.FXMLLoader;
import org.bird.configuration.ConfigurationBuilder;
import org.bird.configuration.exceptions.ConfigurationException;
import org.bird.utils.Utils;

/**
 * Cette classe se charge de créer une instance de l'objet FXMLLoader
 * avec un fichier fxml défini
 */
public class FXMLLoaderImpl {

    private String packageName;

    public FXMLLoaderImpl() throws ConfigurationException {
        packageName = ConfigurationBuilder.getInstance().getElement("global::global.fxml.packagename").getAsString();
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
}
