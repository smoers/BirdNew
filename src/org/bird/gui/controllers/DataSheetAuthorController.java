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
import org.bird.db.models.Author;
import org.bird.gui.events.OnLeftClickEvent;
import org.bird.gui.listeners.OnLeftClickListener;
import org.bird.gui.resources.images.ImageProvider;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Consumer;

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
    private TextArea flBiography;
    @FXML
    private Label flComment;
    @FXML
    private ImageView flImage;
    /**Others**/
    @FXML
    private TitledPane titledPane;
    @FXML
    private Button buttonClose;
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

    /**
     * Ajoute un écouteur sur les boutons
     * @param listener
     */
    @Override
    public void addOnLeftClickListener(OnLeftClickListener listener){
        onLeftClickListeners.add(listener);
    }

    /**
     * Notifie les évouteurs qu'un bouton a été pressé
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
