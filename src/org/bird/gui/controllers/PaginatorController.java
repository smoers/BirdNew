package org.bird.gui.controllers;

import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import org.bird.configuration.exceptions.ConfigurationException;
import org.bird.gui.events.OnLeftClickEvent;
import org.bird.gui.events.OnRightClickEvent;
import org.bird.gui.listeners.OnLeftClickListener;
import org.bird.gui.listeners.OnRightClickListener;

import java.net.URL;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.ResourceBundle;
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
    private int nbrPage;

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
        setLanguage();
        try {
            setText(paneContainer);
        } catch (ConfigurationException e) {
            e.printStackTrace();
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
}
