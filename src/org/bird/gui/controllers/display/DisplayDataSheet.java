package org.bird.gui.controllers.display;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import org.bird.configuration.exceptions.ConfigurationException;
import org.bird.db.models.Author;
import org.bird.db.models.Book;
import org.bird.gui.common.FXMLLoaderImpl;
import org.bird.gui.controllers.DataSheetAuthorController;
import org.bird.gui.controllers.DataSheetBookController;
import org.bird.gui.controllers.IDataSheetController;
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
    private IDataSheetController IDataSheetController = null;

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
        if (IDataSheetController == null) {
            FXMLLoader loader = null;
            if (item instanceof Author) {
                IDataSheetController = new DataSheetAuthorController();
                loader = fxmlLoader.getFXMLLoader(DisplayDataSheet.class, Author.class);
            } else if (item instanceof Book){
                IDataSheetController = new DataSheetBookController();
                loader = fxmlLoader.getFXMLLoader(DisplayDataSheet.class, Book.class);
            }
            IDataSheetController.addOnLeftClickListener(new OnLeftClickListener() {
                @Override
                public void onLeftClick(OnLeftClickEvent evt) {
                    remove();
                }
            });
            loader.setController(IDataSheetController);
            Node node = loader.load();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    node.setId("datasheet");
                    container.add(node);
                }
            });
        }
        IDataSheetController.update((T) item);
    }

    public void remove(){
        container.forEach(new Consumer<Node>() {
            @Override
            public void accept(Node node) {
                if (node.getId() != null && node.getId().equalsIgnoreCase("datasheet")){
                    //détruit l'instance du controller
                    IDataSheetController = null;
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
