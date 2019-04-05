package org.bird.gui.controllers.display;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import org.bird.configuration.exceptions.ConfigurationException;
import org.bird.db.models.Author;
import org.bird.gui.common.FXMLLoaderImpl;
import org.bird.gui.controllers.DataSheetAuthorController;
import org.bird.gui.controllers.DataSheetController;

import java.io.IOException;
import java.util.function.Consumer;

public class DisplayDataSheet {

    private ObservableList<Node> container;
    private FXMLLoaderImpl fxmlLoader;
    private DataSheetController dataSheetController = null;

    public DisplayDataSheet(ObservableList<Node> container) throws ConfigurationException {
        this.container = container;
        this.fxmlLoader = new FXMLLoaderImpl();
    }

    public <T> void display(T item) throws IOException {
        if (dataSheetController == null) {
            dataSheetController = new DataSheetAuthorController();
            FXMLLoader loader = fxmlLoader.getFXMLLoader("datasheetauthor");
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
