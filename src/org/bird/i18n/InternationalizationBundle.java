package org.bird.i18n;

import java.util.*;

/**
 * Cette classe permet de s'assurer de l'existence de la clé
 */
public class InternationalizationBundle {

    private ResourceBundle resourceBundle;

    /**
     * Constructeur
     * @param resourceBundle
     */
    public InternationalizationBundle(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    /**
     * Retourne l'objet ResourceBundle
     * @return
     */
    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    /**
     * Retourne la valeur (traduction).  Si la clé n'existe pas
     * c'est la clé qui est retournée comme valeur( Traduction)
     * @param key
     * @return
     */
    public String getString(String key){
        String value = key;
        if (resourceBundle.containsKey(key)){
            value = resourceBundle.getString(key);
        }
        return value;
    }
}
