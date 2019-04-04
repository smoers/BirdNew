package org.bird.gui.controllers.display;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import org.bird.configuration.exceptions.ConfigurationException;
import org.bird.db.models.Author;
import org.bird.gui.common.FXMLLoaderImpl;
import org.bird.gui.controllers.DataSheetAuthorController;

import java.io.IOException;
import java.util.function.Consumer;

public class DisplayDataSheet {

    private ObservableList<Node> container;
    private FXMLLoaderImpl fxmlLoader;
    private DataSheetAuthorController dataSheetAuthorController = null;

    public DisplayDataSheet(ObservableList<Node> container) throws ConfigurationException {
        this.container = container;
        this.fxmlLoader = new FXMLLoaderImpl();
    }

    public <T> void display(T item) throws IOException {
        if (item instanceof Author){
            display((Author) item);
        }
    }

    public void display(Author author) throws IOException {
        if (dataSheetAuthorController == null) {
            dataSheetAuthorController = new DataSheetAuthorController();
            FXMLLoader loader = fxmlLoader.getFXMLLoader("datasheetauthor");
            loader.setController(dataSheetAuthorController);
            Node node = loader.load();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    node.setId("datasheet");
                    container.add(node);
                }
            });
        }
        dataSheetAuthorController.update(author);
    }

    public void remove(){
        container.forEach(new Consumer<Node>() {
            @Override
            public void accept(Node node) {
                if (node.getId().equalsIgnoreCase("datasheet")){
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
