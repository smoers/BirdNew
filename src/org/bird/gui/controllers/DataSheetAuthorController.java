package org.bird.gui.controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import org.bird.configuration.exceptions.ConfigurationException;
import org.bird.db.models.Author;
import org.bird.gui.events.OnLeftClickEvent;
import org.bird.gui.listeners.OnLeftClickListener;
import org.bird.gui.resources.controls.TextLong;
import org.bird.gui.resources.images.ImageProvider;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class DataSheetAuthorController extends ProtectedController implements Initializable, IDataSheetController<Author> {

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
    private TextArea flBiography;
    @FXML
    private ImageView flImage;
    /**Others**/
    @FXML
    private TitledPane titledPane;
    @FXML
    private Button buttonClose;
    @FXML
    private GridPane gridPane;
    private TextLong fltextLong;
    private TextLong flComment;
    private ArrayList<OnLeftClickListener> onLeftClickListeners = new ArrayList<>();


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
        titledPane.setText(getInternationalizationBundle().getString(titledPane.getText()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //set language
        setLanguage();
        buttonClose.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.isPrimaryButtonDown()){
                    notifyOnLeftClickListener(new OnLeftClickEvent(this, mouseEvent.getClickCount()));
                }
            }
        });
        fltextLong = new TextLong();
        flComment = new TextLong();
        /*Event Biography*/
        fltextLong.addOnLeftClickListener(new OnLeftClickListener() {
            @Override
            public void onLeftClick(OnLeftClickEvent evt) {
                try {
                    DataViewController textLongController = new DataViewController(titledPane.getScene().getWindow());
                    textLongController.setShowSave(false);
                    textLongController.setTitle(lbBiography.getText());
                    textLongController.setText(fltextLong.getLabel().getOriginalText());
                    textLongController.addOnLeftClickListener(new OnLeftClickListener() {
                        @Override
                        public void onLeftClick(OnLeftClickEvent evt) {
                            if (evt.getId().equalsIgnoreCase("buttonCancel")){
                                textLongController.close();
                            }
                        }
                    });
                    textLongController.show();
                } catch (ConfigurationException | IOException e) {
                    showException(e);
                }
            }
        });
        /*Event Biography*/
        flComment.addOnLeftClickListener(new OnLeftClickListener() {
            @Override
            public void onLeftClick(OnLeftClickEvent evt) {
                try {
                    DataViewController dataViewController = new DataViewController(titledPane.getScene().getWindow());
                    dataViewController.setShowSave(false);
                    dataViewController.setTitle(lbComment.getText());
                    dataViewController.setText(flComment.getLabel().getOriginalText());
                    dataViewController.addOnLeftClickListener(new OnLeftClickListener() {
                        @Override
                        public void onLeftClick(OnLeftClickEvent evt) {
                            if (evt.getId().equalsIgnoreCase("buttonCancel")){
                                dataViewController.close();
                            }
                        }
                    });
                    dataViewController.show();
                } catch (ConfigurationException | IOException e) {
                    showException(e);
                }
            }
        });

        gridPane.add(fltextLong.getHbox(),1,5);
        gridPane.add(flComment.getHbox(),1,6);
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
        fltextLong.getLabel().setLimitedText(item.getBiography());
        flComment.getLabel().setLimitedText(item.getComment());
    }

    /**
     * Ajoute un écouteur sur les boutons
     * @param listener
     */
    @Override
    public void addOnLeftClickListener(OnLeftClickListener listener){
        onLeftClickListeners.add(listener);
    }

    /**
     * Notifie les écouteurs qu'un bouton a été pressé
     * @param evt
     */
    private void notifyOnLeftClickListener(OnLeftClickEvent evt){
        onLeftClickListeners.forEach(new Consumer<OnLeftClickListener>() {
            @Override
            public void accept(OnLeftClickListener listener) {
                listener.onLeftClick(evt);
            }
        });
    }
}
