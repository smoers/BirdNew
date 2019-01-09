package org.bird.gui.controllers;

import javafx.beans.InvalidationListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private MenuItem menuExit;
    @FXML
    private Menu menuFile;
    @FXML
    private MenuBar menuBar;
    private HashMap<String, String> languages = new HashMap<>();

    public DashboardController() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("load");
        setLanguage(resourceBundle);
        menuExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ButtonType buttonTypeYes = new ButtonType(languages.get("yes"), ButtonBar.ButtonData.YES);
                ButtonType buttonTypeNo = new ButtonType(languages.get("no"), ButtonBar.ButtonData.NO);
                Image image = new Image(getClass().getResource("/images/blue/high-importance-32.png").toExternalForm());
                ImageView imageView = new ImageView(image);
                Dialog<String> dialog = new Dialog<>();
                dialog.getDialogPane().getButtonTypes().add(buttonTypeNo);
                dialog.getDialogPane().getButtonTypes().add(buttonTypeYes);
                dialog.setGraphic(imageView);
                dialog.setTitle("Attention");
                dialog.setResizable(true);
                TextArea textArea = new TextArea("lskdjfdhlqsksjhlfqksjdqhfdlk lksjqdhfl klqsjkdhlsk dlksdjh mskdjh slqjkdh kjqsd ksjdf msjkdjfh mqq sksdf qj");
                textArea.setEditable(false);
                textArea.setWrapText(true);

                textArea.setMaxWidth(Double.MAX_VALUE);
                textArea.setMaxHeight(Double.MAX_VALUE);
                GridPane.setVgrow(textArea, Priority.ALWAYS);
                GridPane.setHgrow(textArea, Priority.ALWAYS);
                GridPane expContent = new GridPane();

                expContent.setMaxWidth(Double.MAX_VALUE);
                expContent.add(textArea, 0, 0);

                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.initStyle(StageStyle.TRANSPARENT);
                dialog.getDialogPane().setExpandableContent(expContent);
                dialog.headerTextProperty().setValue("lskdjfdhlqsksjhlfqksjdqhfdlk lksjqdhfl klqsjkdhlsk dlksdjh mskdjh slqjkdh kjqsd ksjdf msjkdjfh mqq sksdf qj");
                dialog.setContentText(languages.get("message"));

                dialog.showAndWait();
            }
        });

    }

    public void setLanguage(ResourceBundle resourceBundle){
        //
        menuFile.setText("Dashboard");
        menuExit.setText("Quitter");

        languages.put("yes","Oui");
        languages.put("no", "Non");
        languages.put("message","Voulez-vous quitter l'application ?");
    }
}
