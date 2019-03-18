package org.bird.gui.controllers.display;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import org.bird.db.query.Paginator;
import org.bird.gui.controllers.ItemDashboardController;
import org.bird.gui.events.OnLeftClickEvent;
import org.bird.gui.events.OnProgressChangeEvent;
import org.bird.gui.listeners.OnLeftClickListener;
import org.bird.gui.listeners.OnProgressChangeListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstraite implémentant les méthodes de l'interface IOnDisplayItemDashboardChange
 * @param <T>
 */
public abstract class DisplayItemDashboard<T> implements IOnDisplayItemDashboardChange<T> {
    /**
     * Liste des listener
     */
    private ArrayList<OnProgressChangeListener> onProgressChangeListeners = new ArrayList<>();
    /**
     * L'item sélectionné
     */
    private Pane selected = null;


    @Override
    public abstract void display(Paginator<T> paginator) throws IOException;

    /**
     * Ajoute un listener
     * @param listener
     */
    @Override
    public void addOnProgressChangeListener(OnProgressChangeListener listener) {
        onProgressChangeListeners.add(listener);
    }

    /**
     * Notifie les listeners
     * @param evt
     */
    protected void notifyOnProgressChangeListener(OnProgressChangeEvent evt){
        for (OnProgressChangeListener listener : onProgressChangeListeners){
            listener.onProcessChange(evt);
        }
    }

    /**
     * Ce service se charge de l'affichage dans un autre thread que le thread JavaFX
     * et notifie l'objet WaitingBar
     * @param <T>
     */
    protected class DisplayService<T> extends Service<Void> {

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
                        item.<T>setItem((T) author);
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
