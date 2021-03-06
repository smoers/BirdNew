package org.bird.gui.controllers;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.bird.configuration.exceptions.ConfigurationException;
import org.bird.db.models.Book;
import org.bird.db.models.Illustrator;
import org.bird.gui.events.OnLeftClickEvent;
import org.bird.gui.listeners.OnLeftClickListener;
import org.bird.gui.resources.controls.ShowList;
import org.bird.gui.resources.controls.TextLong;
import org.bird.gui.resources.images.ImageProvider;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.function.Consumer;

public class DataSheetBookController extends DataSheetController<Book> implements Initializable{

    @FXML
    private Button buttonClose;
    @FXML
    private GridPane gridPane;
    @FXML
    private AnchorPane rootPane;
    /** Label **/
    @FXML
    private Label lbCycleTitle;
    @FXML
    private Label lbBookTitle;
    @FXML
    private Label lbVolumeNumber;
    @FXML
    private Label lbPresentation;
    @FXML
    private Label lbEditor;
    @FXML
    private Label lbCollection;
    @FXML
    private Label lbISBN;
    @FXML
    private Label lbIllustrator;
    /** Field **/
    @FXML
    private ImageView flImage;
    @FXML
    private Label flCycleTitle;
    @FXML
    private Label flBookTitle;
    @FXML
    private Label flVolumeNumber;
    private TextLong flPresentation;
    @FXML
    private Label flEditor;
    @FXML
    private Label flCollection;
    private ShowList<String> flISBN;
    private ShowList<String> flIllustrator;

    @Override
    public void setLanguage() {
        getTranslator( getInternationalizationBundle(),"lb","titled").translate(rootPane);
    }

    @Override
    public void update(Book item) {
        ImageProvider imageProvider = new ImageProvider(item.getPicture());
        flImage.setImage(imageProvider.getImage());
        flCycleTitle.setText(item.getCycle().getTitle());
        flBookTitle.setText(item.getTitle());
        flVolumeNumber.setText(String.valueOf(item.getVolume()));
        flPresentation.getLabel().setLimitedText(item.getPresentation());
        flEditor.setText(item.getEditor().getName());
        flCollection.setText(item.getCollection().getName());
        flISBN.setObservableList(FXCollections.<String>observableArrayList(item.getIsbn_10(), item.getIsbn_13()));
        List<String> illustratorList = new ArrayList();
        item.getIllustrators().forEach(new Consumer<Illustrator>() {
            @Override
            public void accept(Illustrator illustrator) {
                illustratorList.add(illustrator.getLastName()+" "+illustrator.getFirstName());
            }
        });
        flIllustrator.setObservableList(FXCollections.<String>observableArrayList(illustratorList));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Set la langue
        setLanguage();
        //Bouton pour fermer la fenetre
        buttonClose.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.isPrimaryButtonDown()){
                    notifyOnLeftClickListener(new OnLeftClickEvent(this, mouseEvent.getClickCount()));
                }
            }
        });
        flPresentation = new TextLong();
        flISBN = new ShowList();
        flIllustrator = new ShowList();
        flPresentation.addOnLeftClickListener(new OnLeftClickListener() {
            @Override
            public void onLeftClick(OnLeftClickEvent evt) {
                try {
                    DataViewController textLongController = new DataViewTextController(lbPresentation.getScene().getWindow());
                    textLongController.setShowSave(false);
                    textLongController.setTitle(lbPresentation.getText());
                    textLongController.setContent(flPresentation.getLabel().getOriginalText());
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
        flISBN.addOnLeftClickListener(new OnLeftClickListener() {
            @Override
            public void onLeftClick(OnLeftClickEvent evt) {
                try {
                    DataViewController textLongController = new DataViewObservableListController(lbISBN.getScene().getWindow());
                    textLongController.setShowSave(false);
                    textLongController.setTitle(lbISBN.getText());
                    textLongController.setContent(flISBN.getObservableList());
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
        flIllustrator.addOnLeftClickListener(new OnLeftClickListener() {
            @Override
            public void onLeftClick(OnLeftClickEvent evt) {
                try {
                    DataViewController textLongController = new DataViewObservableListController(lbIllustrator.getScene().getWindow());
                    textLongController.setShowSave(false);
                    textLongController.setTitle(lbIllustrator.getText());
                    textLongController.setContent(flIllustrator.getObservableList());
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
        gridPane.add(flPresentation.getHbox(),1,4);
        gridPane.add(flISBN.getHbox(),1,7);
        gridPane.add(flIllustrator.getHbox(),1,8);
    }

}
