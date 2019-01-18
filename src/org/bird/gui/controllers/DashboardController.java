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
import org.bird.gui.common.dialog.DialogAlert;
import org.bird.gui.common.dialog.DialogExtended;
import org.bird.i18n.InternationalizationBuilder;
import org.bird.i18n.InternationalizationBundle;
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
    private final InternationalizationBuilder internationalizationBuilder = InternationalizationBuilder.getInstance();
    private InternationalizationBundle internationalizationBundle;

    public DashboardController() {
        internationalizationBundle = internationalizationBuilder.getInternationalizationBundle(getClass());
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(getClass().getSimpleName());
        setLanguage();
        menuExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                InternationalizationBundle bundle = internationalizationBuilder.getInternationalizationBundle(DialogExtended.class);
                DialogAlert dialogAlert = new DialogAlert(DialogExtended.TYPE.BUTTON_YES_NO,"Titre", "Message Text","Context Text",bundle);
                ButtonType response = dialogAlert.showAndWait().get();

                System.out.println(response.getText());

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

    }
}
