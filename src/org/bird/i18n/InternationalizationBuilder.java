package org.bird.i18n;

import java.util.HashMap;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Cette classe est destinée à fournir les ResourceBundle nécessaire
 * à l'internationalisation de l'application
 */
public class InternationalizationBuilder {

    /*
    Liste des ResourceBundle déjà chargé
     */
    private HashMap<String, ResourceBundle> i18nMap = new HashMap<>();
    /*
    Locale setting
     */
    private Locale locale = Locale.getDefault();
    /*
    Package de base pour les fichiers ressources
     */
    private final String basePackage = "i18n.";
    /*
    Instance de la classe singleton
     */
    private static InternationalizationBuilder ourInstance = new InternationalizationBuilder();

    /**
     * Instance
     * @return
     */
    public static InternationalizationBuilder getInstance() {
        return ourInstance;
    }

    /**
     * Cosntructeur privé
     */
    private InternationalizationBuilder() {
    }

    /**
     * Charge l'objet Locale
     * @param locale
     */
    public void setLocale(Locale locale){
        this.locale = locale;
    }

    /**
     * Retourne l'objet Locale
     * @return
     */
    public Locale getLocale(){
        return locale;
    }

    /**
     * Retourne l'objetResourceBundle correspondant à la clé
     * Si l'objet ResourceBundle n'est pas dans l'objet Map, il sera placé dans celui-ci avant d'être retoourné
     * @param key
     * @return
     * @throws MissingResourceException
     */
    public ResourceBundle getResourceBundle(String key) throws MissingResourceException{
        if(!i18nMap.containsKey(key)){
            i18nMap.put(key, ResourceBundle.getBundle(getBaseName(key)));
        }
        return i18nMap.get(key);
    }

    /**
     * Construit le BaseName
     * @param key
     * @return
     */
    protected String getBaseName(String key){
        return basePackage + key;
    }

}
