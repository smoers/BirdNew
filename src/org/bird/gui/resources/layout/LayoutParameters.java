package org.bird.gui.resources.layout;

import java.util.HashMap;

public class LayoutParameters extends HashMap<String, Object> {

    public final static String SELECTOR = "selector";
    public final static String IFTEXT = "iftext";
    public final static String TEXT = "text";
    public final static String I18N = "i18n";
    public final static String CHILDREN = "children";

    public <T> T getParameter(String key, T defaultValue){
        T result = defaultValue;
        if (containsKey(key)){
            result = (T) get(key);
        }
        return result;
    }

}
