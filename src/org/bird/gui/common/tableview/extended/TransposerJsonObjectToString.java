package org.bird.gui.common.tableview.extended;

import com.google.gson.JsonObject;

public class TransposerJsonObjectToString extends Transposer<String> {

    public TransposerJsonObjectToString(Object object) {
        super(object);
    }

    @Override
    public String getValue(String propertyName) {
        JsonObject jsonObject = (JsonObject) object;
        return jsonObject.get(propertyName).getAsString();
    }
}
