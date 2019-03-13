package org.bird.gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.bird.gui.resources.images.ImageProvider;

import java.net.URL;
import java.util.ResourceBundle;

public class WaitingBarAnimatedController extends ProtectedController implements Initializable  {

    @FXML
    private ImageView animatedImage;
    @FXML
    private AnchorPane mainPane;
    private ImageProvider provider;

    public WaitingBarAnimatedController(ImageProvider provider) {
        this.provider = provider;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        animatedImage.setImage(provider.getImage());
        animatedImage.fitWidthProperty().bind(mainPane.widthProperty());
        animatedImage.fitHeightProperty().bind(mainPane.heightProperty());
        animatedImage.setPreserveRatio(false);
    }

    @Override
    public void setLanguage() {

    }
}
