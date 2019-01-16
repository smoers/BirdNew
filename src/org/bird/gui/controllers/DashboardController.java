package org.bird.gui.controllers;

import javafx.application.Platform;
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
import org.bird.i18n.InternationalizationController;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class DashboardController extends InternationalizationController implements Initializable {

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
        System.out.println(getClass().getSimpleName());
        setLanguage();
        menuExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ButtonType buttonTypeYes = new ButtonType(languages.get("yes"), ButtonBar.ButtonData.YES);
                ButtonType buttonTypeNo = new ButtonType(languages.get("no"), ButtonBar.ButtonData.NO);
                Image image = new Image(getClass().getResource("/images/blue/high-importance-32.png").toExternalForm());
                ImageView imageView = new ImageView(image);
                Dialog<ButtonType> dialog = new Dialog<>();
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

                ButtonType response = dialog.showAndWait().get();
                System.out.println(response);
                Platform.exit();
            }
        });

    }

    public void setLanguage(){
        //Ne defini le texte que si l'objet InternationalizationBundle est non null
        if (internationalizationBundle != null) {
            menuFile.setText(internationalizationBundle.getString("Dashboard"));
            menuExit.setText(internationalizationBundle.getString("Exit"));
        }

        languages.put("yes","Oui");
        languages.put("no", "Non");
        languages.put("message","Voulez-vous quitter l'application ?");
    }
}
