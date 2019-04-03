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

public class DisplayDataSheet {

    private ObservableList<Node> container;
    private FXMLLoaderImpl fxmlLoader;

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
        DataSheetAuthorController controller = new DataSheetAuthorController(author);
        FXMLLoader loader = fxmlLoader.getFXMLLoader("datasheetauthor");
        loader.setController(controller);
        Node node = loader.load();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                 container.add(node);
            }
        });
    }
}
