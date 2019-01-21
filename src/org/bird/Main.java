package org.bird;


import com.google.gson.JsonElement;
import javafx.fxml.FXMLLoader;
import org.bird.configuration.Configuration;
import org.bird.configuration.ConfigurationBuilder;
import org.bird.gui.GraphicalUserInterface;
import org.bird.i18n.InternationalizationBuilder;

import java.util.Locale;

public class Main {



    public static void main(String[] args) {
        Main main = new Main(args);
    }

    private Main(String[] args){

        //Initialisation
        ConfigurationBuilder configurationBuilder = ConfigurationBuilder.getInstance();
        configurationBuilder.autoLoad(ConfigurationBuilder.DEFAULT_CONFIGURATION_FOLDER);
        Configuration configuration = configurationBuilder.get("global");
        String  language = configuration.get("global.language").getAsString();

        InternationalizationBuilder internationalizationBuilder = InternationalizationBuilder.getInstance();
        internationalizationBuilder.setLocale(new Locale(language));

        GraphicalUserInterface.launch(GraphicalUserInterface.class,args);
    }

}
