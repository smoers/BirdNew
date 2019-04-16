package org.bird.gui.controllers.display;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import org.bird.configuration.exceptions.ConfigurationException;
import org.bird.db.models.Author;
import org.bird.gui.common.FXMLLoaderImpl;
import org.bird.gui.controllers.DataSheetAuthorController;
import org.bird.gui.controllers.DataSheetController;
import org.bird.gui.events.OnLeftClickEvent;
import org.bird.gui.listeners.OnLeftClickListener;

import java.io.IOException;
import java.util.function.Consumer;

/**
 * Se charge de l'affichage des informations d'un item (Auteurs ou Livres)
 */
public class DisplayDataSheet {

    private ObservableList<Node> container;
    private FXMLLoaderImpl fxmlLoader;
    private DataSheetController dataSheetController = null;

    /**
     * Constructeur
     * @param container
     * @throws ConfigurationException
     */
    public DisplayDataSheet(ObservableList<Node> container) throws ConfigurationException {
        this.container = container;
        this.fxmlLoader = new FXMLLoaderImpl();
    }

    /**
     * Affiche l'item
     * @param item
     * @param <T>
     * @throws IOException
     */
    public <T> void display(T item) throws IOException, ConfigurationException {
        if (dataSheetController == null) {
            dataSheetController = new DataSheetAuthorController();
            dataSheetController.addOnLeftClickListener(new OnLeftClickListener() {
                @Override
                public void onLeftClick(OnLeftClickEvent evt) {
                    remove();
                }
            });
            FXMLLoader loader = fxmlLoader.getFXMLLoader(DisplayDataSheet.class, Author.class);
            loader.setController(dataSheetController);
            Node node = loader.load();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    node.setId("datasheet");
                    container.add(node);
                }
            });
        }
        dataSheetController.update((T) item);
    }

    public void remove(){
        container.forEach(new Consumer<Node>() {
            @Override
            public void accept(Node node) {
                if (node.getId().equalsIgnoreCase("datasheet")){
                    //détruit l'instance du controller
                    dataSheetController = null;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            container.remove(node);
                        }
                    });
                }
            }
        });
    }
}
