package org.bird.gui.common.tableviewextended;

import com.google.gson.JsonObject;

public class TransposerJsonObjectToString extends Transposer<String, JsonObject> {

    public TransposerJsonObjectToString(JsonObject object) {
        super(object);
    }

    @Override
    public String getValue(String propertyName) {
        return object.get(propertyName).getAsString();
    }
}
