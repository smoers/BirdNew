package org.bird.gui.controllers.display;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import org.bird.db.models.Author;
import org.bird.db.query.Paginator;
import org.bird.gui.controllers.ItemDashboardController;
import org.bird.gui.events.OnLeftClickEvent;
import org.bird.gui.events.OnProgressChangeEvent;
import org.bird.gui.listeners.OnLeftClickListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DisplayItemDashboardAuthor extends DisplayItemDashboard<Author> {

    private Pane itemsContainer;
    private Pane selected = null;

    /**
     * Contructeur
     * @param itemsContainer
     */
    public DisplayItemDashboardAuthor(Pane itemsContainer){
        this.itemsContainer = itemsContainer;
    }

    /**
     * Affiche les items au depart d'un objet Paginator
     * @param paginator
     * @throws IOException
     */
    @Override
    public void display(Paginator<Author> paginator) throws IOException {
        //vide le conteneur
        itemsContainer.getChildren().clear();
        DisplayService<Author> service = new DisplayService<>(itemsContainer, paginator);
        service.start();
        //Charge le dashboard
    }

    private class DisplayService<T> extends Service<Void>{

        private Pane pane;
        private Paginator<T> paginator;

        public DisplayService(Pane pane, Paginator<T> paginator) {
            this.pane = pane;
            this.paginator = paginator;
        }

        @Override
        protected Task<Void> createTask() {
            return new Task<Void>(){
                @Override
                protected Void call() throws Exception {
                    List<Node> list = new ArrayList<>();
                    double size = paginator.getItemsByPage();
                    double value = 1;
                    for (T author : paginator.getList()) {
                        System.out.println(value);
                        notifyOnProgressChangeListener(new OnProgressChangeEvent(this,value,size));
                        FXMLLoader _loader = new FXMLLoader();
                        _loader.setLocation(getClass().getResource("/org/bird/gui/resources/views/itemDashboard.fxml"));
                        Node _node = null;
                        try {
                            _node = _loader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        ItemDashboardController item = (ItemDashboardController) _loader.getController();
                        item.setItem((Author) author);
                        item.addOnLeftClickListener(new OnLeftClickListener() {
                            @Override
                            public void onLeftClick(OnLeftClickEvent evt) {
                                Pane container = (Pane) evt.getSource();
                                if (evt.getClickCount() == 1) {
                                    if ((selected != null)) {
                                        selected.getStyleClass().clear();
                                        selected.getStyleClass().add("item_container");
                                    }
                                    container.getStyleClass().add("item_container_active");
                                    selected = container;
                                }
                            }
                        });
                        list.add(_node);
                        value++;
                    }
                    System.out.println(list.size());
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (Node node : list) {
                                pane.getChildren().add(node);
                            }
                        }
                    });

                    return null;
                }
            };
        }
    }

}
