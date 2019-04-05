package org.bird.gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.bird.db.models.Author;
import org.bird.gui.resources.images.ImageProvider;

import java.net.URL;
import java.util.ResourceBundle;

public class DataSheetAuthorController extends ProtectedController implements Initializable,DataSheetController<Author> {

    /**Labels**/
    @FXML
    private Label lbName;
    @FXML
    private Label lbBiography;
    /**Fields**/
    @FXML
    private Label flFullName;
    @FXML
    private Label flBiography;
    @FXML
    private ImageView flImage;

    public DataSheetAuthorController(){
        setInternationalizationBundle(internationalizationBuilder.getInternationalizationBundle(getClass()));
    }

    @Override
    public void setLanguage() {
        lbName.setText(getInternationalizationBundle().getString(lbName.getText()));
        lbBiography.setText(getInternationalizationBundle().getString(lbBiography.getText()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //set language
        setLanguage();
    }

    @Override
    public void update(Author item) {
        ImageProvider imageProvider = new ImageProvider(item.getPicture());

        //flImage.setImage(imageProvider.getImage());
        flFullName.setText(item.getFullName());
        flBiography.setText(item.getBiography());
    }
}
