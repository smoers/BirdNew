package org.bird.sandbox;

import org.bird.configuration.Configuration;
import org.bird.configuration.ConfigurationBuilder;
import org.bird.configuration.exceptions.ConfigurationException;

public class _Config_Test_01 {

    public static void main(String[] args) {
        try {
            ConfigurationBuilder builder = ConfigurationBuilder.getInstance();
            Configuration configuration = builder.get("global");
            System.out.println(builder.getElement("global::global.database.name").getAsString());
        } catch (ConfigurationException e){
            System.out.println(e.getI18nMessage());
        }
    }
}
