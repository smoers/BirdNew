package org.bird.gui.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import org.apache.commons.lang3.math.NumberUtils;
import org.bird.configuration.exceptions.ConfigurationException;
import org.bird.gui.events.OnLeftClickEvent;
import org.bird.gui.events.OnRightClickEvent;
import org.bird.gui.listeners.OnLeftClickListener;
import org.bird.gui.listeners.OnRightClickListener;

import java.net.URL;
import java.util.*;
import java.util.function.Predicate;

/**
 * Classe qui assure le visuel d'un système de pagination
 */
public class PaginatorController extends ProtectedController implements Initializable {

    private Pane itemsContainer;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setLanguage();
            setText(paneContainer);
            showPageCounterFormatted();
            fieldPage.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                    if (t1){
                        fieldPage.setText(String.valueOf(getPageCounter()));
                    } else {
                        if (NumberUtils.isParsable(fieldPage.getText())){
                            setPageCounter(Integer.parseInt(fieldPage.getText()));
                            //Si la page demandée est supérieur au nombre de page total
                            //on défini la page demandée comme égale au nombre de page
                            if (getPageCounter() > getNbrPage())
                                setPageCounter(getNbrPage());
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

    public void setItemsContainer(Pane itemsContainer) {
        this.itemsContainer = itemsContainer;
    }

    public int getPageCounter() {
        return pageCounter;
    }

    public void setPageCounter(int pageCounter) {
        this.pageCounter = pageCounter;
    }

    public int getItemByPage() {
        return itemByPage;
    }

    public void setItemByPage(int itemByPage) {
        this.itemByPage = itemByPage;
    }

    public int getNbrPage() {
        return nbrPage;
    }

    public void setNbrPage(int nbrPage) {
        this.nbrPage = nbrPage;
    }

    private void showPageCounterFormatted(){
        StringJoiner joiner = new StringJoiner(" / ");
        joiner.add(String.valueOf(getPageCounter()));
        joiner.add(String.valueOf(getNbrPage()));
        fieldPage.setText(joiner.toString());
    }
}
