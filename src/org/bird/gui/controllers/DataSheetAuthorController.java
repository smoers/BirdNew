package org.bird.gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.bird.db.models.Author;
import org.bird.gui.resources.images.ImageProvider;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class DataSheetAuthorController extends ProtectedController implements Initializable,DataSheetController<Author> {

    /**Labels**/
    @FXML
    private Label lbName;
    @FXML
    private Label lbBornName;
    @FXML
    private Label lbBornDate;
    @FXML
    private Label lbDeathDate;
    @FXML
    private Label lbBiography;
    @FXML
    private Label lbComment;
    /**Fields**/
    @FXML
    private Label flFullName;
    @FXML
    private Label flBornFullName;
    @FXML
    private Label flBornDate;
    @FXML
    private Label flDeathDate;
    @FXML
    private Label flBiography;
    @FXML
    private Label flComment;
    @FXML
    private ImageView flImage;

    public DataSheetAuthorController(){
        setInternationalizationBundle(internationalizationBuilder.getInternationalizationBundle(getClass()));
    }

    @Override
    public void setLanguage() {
        lbName.setText(getInternationalizationBundle().getString(lbName.getText()));
        lbBornName.setText(getInternationalizationBundle().getString(lbBornName.getText()));
        lbBornDate.setText(getInternationalizationBundle().getString(lbBornDate.getText()));
        lbDeathDate.setText(getInternationalizationBundle().getString(lbDeathDate.getText()));
        lbBiography.setText(getInternationalizationBundle().getString(lbBiography.getText()));
        lbComment.setText(getInternationalizationBundle().getString(lbComment.getText()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //set language
        setLanguage();
    }

    @Override
    public void update(Author item) {
        ImageProvider imageProvider = new ImageProvider(item.getPicture());
        SimpleDateFormat format = new SimpleDateFormat("dd MMMM YYY");
        flImage.setImage(imageProvider.getImage());
        flFullName.setText(item.getFullName());
        flBornFullName.setText(item.getBornFullName());
        flBornDate.setText(format.format(item.getBornDate()));
        flDeathDate.setText(format.format(item.getDeathDate()));
        flBiography.setText(item.getBiography());
        flComment.setText(item.getComment());
    }
}
