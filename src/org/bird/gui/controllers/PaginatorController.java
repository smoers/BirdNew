package org.bird.gui.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import org.apache.commons.lang3.math.NumberUtils;
import org.bird.configuration.exceptions.ConfigurationException;
import org.bird.db.query.Paginator;
import org.bird.gui.events.OnLeftClickEvent;
import org.bird.gui.events.OnPaginatorChangePageEvent;
import org.bird.gui.events.OnRightClickEvent;
import org.bird.gui.listeners.OnLeftClickListener;
import org.bird.gui.listeners.OnPaginatorChangePageListener;
import org.bird.gui.listeners.OnRightClickListener;

import java.net.URL;
import java.util.*;
import java.util.function.Predicate;

/**
 * Classe qui assure le visuel d'un système de pagination
 */
public class PaginatorController<T> extends ProtectedController implements Initializable {

    private Pane itemsContainer;
    private Paginator<T> paginator = null;
    @FXML
    private FlowPane paneContainer;
    @FXML
    private Button buttonFirst;
    @FXML
    private Button buttonPrevious;
    @FXML
    private Button buttonNext;
    @FXML
    private Button buttonLast;
    @FXML
    private TextField fieldPage;
    @FXML
    private ComboBox<Integer> comboNbrItems;
    /**
     * Event
     */
    private ArrayList<OnRightClickListener> onRightClickListeners = new ArrayList<>();
    private ArrayList<OnLeftClickListener> onLeftClickListeners = new ArrayList<>();
    private ArrayList<OnPaginatorChangePageListener> onPaginatorChangePageListeners = new ArrayList<>();
    /**
     * Page actuelle
     */
    private int pageCounter = 1;
    /**
     * Nombre d'items par page
     * Dans le fichier de configuration clé item_by_page
     */
    private int itemByPage;
    private int nbrPage = 1;

    /**
     * Contructeur
     */
    public PaginatorController() {
        setInternationalizationBundle(internationalizationBuilder.getInternationalizationBundle(getClass()));
    }

    /**
     * Traduit les textes
     */
    @Override
    public void setLanguage() {
        buttonFirst.setText(getInternationalizationBundle().getString(buttonFirst.getText()));
        buttonPrevious.setText(getInternationalizationBundle().getString(buttonPrevious.getText()));
        buttonLast.setText(getInternationalizationBundle().getString(buttonLast.getText()));
        buttonNext.setText(getInternationalizationBundle().getString(buttonNext.getText()));
    }

    /**
     * Initialise l'objet
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            //Le texte dans la bonne langue
            setLanguage();
            //Affichage des textes dans le boutons
            setText(paneContainer);
            //Affiche le champs avec la page en cours et le nombre de page
            showPageCounterFormatted();
            //je m'inscris dans l'écouteur de changement de page
            addOnPaginatorChangePageListener(new OnPaginatorChangePageListener() {
                @Override
                public void onPaginatorChangePage(OnPaginatorChangePageEvent evt) {

                }
            });
            //Events
            /* bouton first */
            buttonFirst.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    paginator.setPage(1);
                    notifyOnPaginatorChangePageListener(new OnPaginatorChangePageEvent(this, paginator));
                }
            });

            fieldPage.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                    if (t1){
                        fieldPage.setText(String.valueOf(pageCounter));
                    } else {
                        if (NumberUtils.isParsable(fieldPage.getText())){
                            pageCounter = (Integer.parseInt(fieldPage.getText()));
                            //Si la page demandée est supérieur au nombre de page total
                            //on défini la page demandée comme égale au nombre de page
                            if (pageCounter > nbrPage)
                                pageCounter = nbrPage;
                        }
                        showPageCounterFormatted();
                    }
                }
            });
        } catch (ConfigurationException e) {
            shoxException(e);
        }
    }

    /**
     * Ajoute un listener sur le click droit de la souris
     * @param listener
     */
    public void addOnRightClickListener(OnRightClickListener listener){
        onRightClickListeners.add(listener);
    }

    /**
     * Ajoute un listener sur le click gauche de la souris
     * @param listener
     */
    public void addOnLeftClickListener(OnLeftClickListener listener){
        onLeftClickListeners.add(listener);
    }

    /**
     * Ajoute  un listener sur le changement de page
     * @param listener
     */
    public void addOnPaginatorChangePageListener(OnPaginatorChangePageListener listener) { onPaginatorChangePageListeners.add(listener); }

    /**
     * Notifie le listener du click droit
     * @param evt
     */
    private void notifyOnRightClickListener(OnRightClickEvent evt){
        for (OnRightClickListener listener : onRightClickListeners){
            listener.onRightClick(evt);
        }
    }

    /**
     * Notifie le listener du click gauche
     * @param evt
     */
    private void notifyOnLeftClickListener(OnLeftClickEvent evt){
        for (OnLeftClickListener listener : onLeftClickListeners){
            listener.onLeftClick(evt);
        }
    }

    /**
     * Notifie le listner du changement de page
     * @param evt
     */
    private void notifyOnPaginatorChangePageListener(OnPaginatorChangePageEvent evt){
        for (OnPaginatorChangePageListener listener : onPaginatorChangePageListeners){
            listener.onPaginatorChangePage(evt);
        }
    }

    /**
     * Panneau dans lequel les items doivent être affichés
     * @param itemsContainer
     */
    public void setItemsContainer(Pane itemsContainer) {
        this.itemsContainer = itemsContainer;
    }

    /**
     * Retourne l'objet Paginator
     * @return
     */
    public Paginator<T> getPaginator() {
        return paginator;
    }

    /**
     * Defini l'objet Paginator à utiliser
     * @param paginator
     */
    public void setPaginator(Paginator<T> paginator) {
        this.paginator = paginator;

    }

    /**
     * Format l'affichage de la page en actuelle et du nombre total de page
     */
    private void showPageCounterFormatted(){
        StringJoiner joiner = new StringJoiner(" / ");
        joiner.add(String.valueOf(pageCounter));
        joiner.add(String.valueOf(nbrPage));
        fieldPage.setText(joiner.toString());
    }
}
