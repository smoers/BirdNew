package org.bird.gui.controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.bird.configuration.exceptions.ConfigurationException;
import org.bird.gui.common.FXMLLoaderImpl;
import org.bird.gui.resources.images.ImageProvider;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddEditAuthorController extends ProtectedController implements Initializable {

    @FXML
    private ImageView imageAuthor;
    @FXML
    private TextArea flComment;

    private Window owner;
    private Stage stage = new Stage();
    private String title;

    public AddEditAuthorController(Window owner) {
        this.owner = owner;
    }

    @Override
    public void setLanguage() {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        imageAuthor.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                if (dragEvent.getDragboard().hasImage()) {
                    dragEvent.acceptTransferModes(TransferMode.ANY);
                }
                dragEvent.consume();
            }
        });

        imageAuthor.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                Image image = dragEvent.getDragboard().getImage();
                imageAuthor.setImage(image);
                dragEvent.consume();
            }
        });

        flComment.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                if (dragEvent.getDragboard().hasString()){
                    dragEvent.acceptTransferModes(TransferMode.ANY);
                }
                dragEvent.consume();
            }
        });

        flComment.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                String str = dragEvent.getDragboard().getString();
                flComment.setText(str);
                dragEvent.consume();
            }
        });

    }

    /**
     * affiche la fenetre
     * @throws ConfigurationException
     * @throws IOException
     */
    public void show() throws ConfigurationException, IOException {
        FXMLLoaderImpl loaderImpl = new FXMLLoaderImpl();
        FXMLLoader loader = loaderImpl.getFXMLLoader("addeditauthor");
        loader.setController(this);
        stage.setScene(new Scene(loader.load()));
        stage.getIcons().add(ImageProvider.getLogoImage());
        stage.setTitle(title);
        stage.initModality(Modality.NONE);
        stage.initOwner(owner);
        stage.show();
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
