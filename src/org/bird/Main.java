package org.bird;


import javafx.fxml.FXMLLoader;
import org.bird.gui.GraphicalUserInterface;

public class Main {



    public static void main(String[] args) {
        Main main = new Main(args);
    }

    private Main(String[] args){

        GraphicalUserInterface.launch(GraphicalUserInterface.class,args);
    }
}
