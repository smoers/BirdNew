package org.bird.sandbox;

import com.google.gson.JsonObject;
import org.bird.configuration.Configuration;
import org.bird.configuration.ConfigurationBuilder;
import org.bird.configuration.exceptions.ConfigurationException;

public class _Config_Test_01 {

    public static void main(String[] args) {
        try {
            ConfigurationBuilder builder = ConfigurationBuilder.getInstance();
            Configuration configuration = builder.get("layout");
            System.out.println(configuration.get("layout.item_by_page.value").getAsInt());
            JsonObject jsonObject2 = configuration.get().getAsJsonObject();
            JsonObject jsonObject = configuration.get("layout.item_by_page").getAsJsonObject();
            System.out.println(jsonObject.toString());
            jsonObject.addProperty("value",14);
            System.out.println(jsonObject.toString());
            System.out.println(jsonObject2.toString());
        } catch (ConfigurationException e){
            System.out.println(e.getI18nMessage());
        }
    }
}
