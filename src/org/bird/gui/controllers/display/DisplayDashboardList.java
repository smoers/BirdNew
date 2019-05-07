package org.bird.gui.controllers.display;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import org.bird.configuration.exceptions.ConfigurationException;
import org.bird.db.query.Paginator;
import org.bird.gui.common.ConverterTableViewColumn;
import org.bird.gui.events.OnProcessEvent;
import org.bird.gui.events.OnProgressChangeEvent;
import org.bird.gui.events.OnSelectedEvent;
import org.bird.gui.listeners.OnProcessListener;
import org.bird.gui.listeners.OnProgressChangeListener;
import org.bird.gui.listeners.OnSelectedListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * Cette classe abstraite fourni les éléments nécessaire à l'affichage des Auteurs ou des Livres
 * dans un objet de type TableView sur le dashboard de l'application
 * @param <T>
 */
public abstract class DisplayDashboardList<T> implements IDisplayDashboard<T> {
    /**
     * Liste des listener
     */
    private ArrayList<OnProgressChangeListener> onProgressChangeListeners = new ArrayList<>();
    private ArrayList<OnProcessListener> onProcessListeners = new ArrayList<>();
    private ArrayList<OnSelectedListener<T>> onSelectedListeners = new ArrayList<>();

    protected ObservableList itemsContainer;

    /**
     * Constructeur
     * @param itemsContainer
     */
    public DisplayDashboardList(ObservableList itemsContainer){
        this.itemsContainer = itemsContainer;
    }

    @Override
    public abstract void display(Paginator<T> paginator) throws IOException;

    /**
     * Permet au service d'obtenir le converter correctement charger avec les données de l'item
     * si c'est un Auteur ou un livre
     * @param item
     * @return
     */
    public abstract ConverterTableViewColumn<ImageView,Void,Void> getConverterTableViewColumn(T item) throws ConfigurationException;

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
    @Override
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

    protected class DisplayService extends Service<Void>{

        private TableView<ConverterTableViewColumn<ImageView,Void,Void>> tableView;
        private Paginator<T> paginator;

        public DisplayService(TableView<ConverterTableViewColumn<ImageView, Void, Void>> tableView, Paginator<T> paginator) {
            this.tableView = tableView;
            this.paginator = paginator;
        }

        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    double size = paginator.getItemsByPage();
                    double value = 1;
                    notifyOnProcessListener(new OnProcessEvent(this, true));
                    for (T item : paginator.getList()){
                        notifyOnProgressChangeListener(new OnProgressChangeEvent(this,value,size));
                        ConverterTableViewColumn column = getConverterTableViewColumn(item);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                tableView.getItems().add(column);
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
