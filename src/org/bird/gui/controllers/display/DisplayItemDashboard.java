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
import org.bird.gui.events.OnProcessEvent;
import org.bird.gui.events.OnProgressChangeEvent;
import org.bird.gui.events.OnSelectedEvent;
import org.bird.gui.listeners.OnLeftClickListener;
import org.bird.gui.listeners.OnProcessListener;
import org.bird.gui.listeners.OnProgressChangeListener;
import org.bird.gui.listeners.OnSelectedListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Classe abstraite implémentant les méthodes de l'interface IOnDisplayItemDashboardChange
 * @param <T>
 */
public abstract class DisplayItemDashboard<T> implements IOnDisplayItemDashboardChange<T> {
    /**
     * Liste des listener
     */
    private ArrayList<OnProgressChangeListener> onProgressChangeListeners = new ArrayList<>();
    private ArrayList<OnProcessListener> onProcessListeners = new ArrayList<>();
    private ArrayList<OnSelectedListener<T>> onSelectedListeners = new ArrayList<>();
    /**
     * L'item sélectionné
     */
    private Pane selected = null;


    @Override
    public abstract void display(Paginator<T> paginator) throws IOException;

    /**
     * Ajoute un listener pour la gestion de la Waiting Bar
     * @param listener
     */
    @Override
    public void addOnProgressChangeListener(OnProgressChangeListener listener) {
        onProgressChangeListeners.add(listener);
    }

    /**
     * Ajoute un Listener pour la gestion de la Waiting Bar
     * @param listener
     */
    @Override
    public void addOnProcessListener(OnProcessListener listener) {
        onProcessListeners.add(listener);
    }

    /**
     * Ajoute un listener pour l'item qui est selectionner
     * @param listener
     */
    public void addOnSelectedListener(OnSelectedListener<T> listener) { onSelectedListeners.add(listener); }

    /**
     * Notifie les listeners
     * @param evt
     */
    protected void notifyOnProgressChangeListener(OnProgressChangeEvent evt){
        onProgressChangeListeners.forEach(new Consumer<OnProgressChangeListener>() {
            @Override
            public void accept(OnProgressChangeListener onProgressChangeListener) {
                onProgressChangeListener.onProcessChange(evt);
            }
        });
    }

    /**
     * Notifie les listeners
     * @param evt
     */
    protected void notifyOnProcessListener(OnProcessEvent evt){
        onProcessListeners.forEach(new Consumer<OnProcessListener>() {
            @Override
            public void accept(OnProcessListener listener) {
                listener.onProcess(evt);
            }
        });
    }

    /**
     * Notifie les listeners
     * @param evt
     */
    protected void notifyOnSelectedListener(OnSelectedEvent<T> evt){
        onSelectedListeners.forEach(new Consumer<OnSelectedListener<T>>() {
            @Override
            public void accept(OnSelectedListener<T> listener) {
                listener.OnSelected(evt);
            }
        });
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
                    notifyOnProcessListener(new OnProcessEvent(this, true));
                    for (T item : paginator.getList()) {
                        notifyOnProgressChangeListener(new OnProgressChangeEvent(this,value,size));
                        FXMLLoader _loader = new FXMLLoader();
                        _loader.setLocation(getClass().getResource("/org/bird/gui/resources/views/itemDashboard.fxml"));
                        Node _node = null;
                        try {
                            _node = _loader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        ItemDashboardController itemDashboardController = (ItemDashboardController) _loader.getController();
                        itemDashboardController.<T>setItem((T) item);
                        itemDashboardController.addOnLeftClickListener(new OnLeftClickListener() {
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
                                    notifyOnSelectedListener(new OnSelectedEvent(this, item));
                                }
                            }
                        });
                        list.add(_node);
                        final Node node = _node;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                pane.getChildren().add(node);
                            }
                        });
                        value++;
                    }
                    notifyOnProcessListener(new OnProcessEvent(this, false));
                    return null;
                }
            };
        }
    }

}
