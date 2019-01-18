package org.bird;


import javafx.fxml.FXMLLoader;
import org.bird.gui.GraphicalUserInterface;
import org.bird.i18n.InternationalizationBuilder;

import java.util.Locale;

public class Main {



    public static void main(String[] args) {
        Main main = new Main(args);
    }

    private Main(String[] args){
        InternationalizationBuilder internationalizationBuilder = InternationalizationBuilder.getInstance();
        internationalizationBuilder.setLocale(new Locale("fr"));
        GraphicalUserInterface.launch(GraphicalUserInterface.class,args);
    }
}
