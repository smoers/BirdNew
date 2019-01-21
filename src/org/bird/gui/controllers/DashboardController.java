package org.bird.gui.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import org.bird.gui.common.dialog.DialogAlert;
import org.bird.gui.common.dialog.DialogExtended;
import org.bird.gui.resources.images.ImageProvider;
import org.bird.i18n.InternationalizationBuilder;
import org.bird.i18n.InternationalizationBundle;
import org.bird.i18n.InternationalizationController;

import java.io.IOException;
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
    @FXML
    private FlowPane itemsContainer;
    private final InternationalizationBuilder internationalizationBuilder = InternationalizationBuilder.getInstance();
    private InternationalizationBundle internationalizationBundle;

    public DashboardController() {
        internationalizationBundle = internationalizationBuilder.getInternationalizationBundle(getClass());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //charge le texte de l'interface
        setLanguage();
        //evenements
        menuExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                InternationalizationBundle bundle = internationalizationBuilder.getInternationalizationBundle(DialogExtended.class);
                String title = internationalizationBundle.getString("Attention");
                String message = internationalizationBundle.getString("Message001");
                DialogAlert dialogAlert = new DialogAlert(DialogExtended.TYPE.BUTTON_YES_NO,title, message,bundle);
                ButtonType response = dialogAlert.showAndWait().get();

                if (response.getButtonData() == ButtonBar.ButtonData.YES)
                    Platform.exit();
            }
        });

        //Charge le dashboard
        int ii = 4;
        for(int i=1;i<=ii;i++) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/org/bird/gui/resources/views/itemDashboard.fxml"));
                Node node = loader.load();
                ItemDashboardController item = (ItemDashboardController) loader.getController();
                item.setAuthor("Joe Abercrombie");
                item.setBook("livre " + i);
                ImageProvider imageProvider = new ImageProvider("/images/books/001.jpg");
                item.setImage(imageProvider.getImage());
                itemsContainer.getChildren().add(node);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setLanguage(){
        //Ne defini le texte que si l'objet InternationalizationBundle est non null
        if (internationalizationBundle != null) {
            menuFile.setText(internationalizationBundle.getString("Dashboard"));
            menuExit.setText(internationalizationBundle.getString("Exit"));
        }

    }
}
