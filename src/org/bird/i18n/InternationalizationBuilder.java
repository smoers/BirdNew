package org.bird.i18n;

import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

public class InternationalizationBuilder {

    private HashMap<String, ResourceBundle> i18nMap = new HashMap<>();
    private Locale locale = Locale.getDefault();

    private static InternationalizationBuilder ourInstance = new InternationalizationBuilder();

    public static InternationalizationBuilder getInstance() {
        return ourInstance;
    }

    private InternationalizationBuilder() {
    }

    public void setLocale(Locale locale){
        this.locale = locale;
    }

    public Locale getLocale(){
        return locale;
    }

    public ResourceBundle getResourceBundle(String key){
        if(!i18nMap.containsKey(key)){
            i18nMap.put(key, ResourceBundle.getBundle("i18n." + key));
        }
        return i18nMap.get(key);
    }

}
