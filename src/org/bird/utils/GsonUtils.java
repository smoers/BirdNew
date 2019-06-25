package org.bird.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class GsonUtils {

    /**
     * Permet de convertir un objet JsonArray vers un objet List
     * @param jsonArray
     * @return
     */
    public static List<JsonElement> ConvertJsonArrayToList(JsonArray jsonArray){
        List list = new ArrayList();
        jsonArray.forEach(new Consumer<JsonElement>() {
            @Override
            public void accept(JsonElement jsonElement) {
                list.add(jsonElement);
            }
        });
        return list;
    }

}
