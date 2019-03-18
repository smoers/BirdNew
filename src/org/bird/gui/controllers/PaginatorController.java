package org.bird.gui.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import org.apache.commons.lang3.math.NumberUtils;
import org.bird.configuration.exceptions.ConfigurationException;
import org.bird.db.exceptions.DBException;
import org.bird.db.mapper.MapperFactory;
import org.bird.db.mapper.MapperPaginator;
import org.bird.db.query.Paginator;
import org.bird.gui.controllers.display.IOnDisplayItemDashboardChange;
import org.bird.gui.events.OnLeftClickEvent;
import org.bird.gui.events.OnPaginatorChangePageEvent;
import org.bird.gui.events.OnProcessEvent;
import org.bird.gui.events.OnRightClickEvent;
import org.bird.gui.listeners.OnLeftClickListener;
import org.bird.gui.listeners.OnPaginatorChangePageListener;
import org.bird.gui.listeners.OnProcessListener;
import org.bird.gui.listeners.OnRightClickListener;
import org.bird.gui.services.MapperPaginatorService;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Classe qui assure le visuel d'un système de pagination
 */
public class PaginatorController extends ProtectedController implements Initializable {

    /**
     * Container destiné à accueillir les items
     */
    private IOnDisplayItemDashboardChange displayItemDashboard;
    /**
     * Le paginateur à utiliser
     */
    private Paginator paginator = null;
    /**
     * Le mapper pour l'accès à la DB
     */
    private MapperPaginator mapper;
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
    private ArrayList<OnProcessListener> onProcessListeners = new ArrayList<>();

    /**
     * Contructeur
     * @param paginator
     * @param displayItemDashboard
     */
    public PaginatorController(Paginator paginator, IOnDisplayItemDashboardChange displayItemDashboard) {
        this.paginator = paginator;
        this.displayItemDashboard = displayItemDashboard;
        setInternationalizationBundle(internationalizationBuilder.getInternationalizationBundle(getClass()));
        //On crée une instance du mapper permettant l'interaction avec la base de données.
        mapper = new MapperPaginator();
        try {
            mapper = MapperFactory.getInstance().<MapperPaginator>getMapper(mapper);
        } catch (DBException e) {
            showException(e);
        }
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
            //Events
            /* bouton first */
            buttonFirst.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    paginator.setPage(1);
                    refresh();
                    notifyOnPaginatorChangePageListener(new OnPaginatorChangePageEvent(this, paginator));
                }
            });
            /*bouton previous*/
            buttonPrevious.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (paginator.getPage() > 1 ){
                        paginator.setPage(paginator.getPage()-1);
                        refresh();
                        notifyOnPaginatorChangePageListener(new OnPaginatorChangePageEvent(this,paginator));
                    }
                }
            });
            /*bouton next*/
            buttonNext.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if(paginator.getPage() < paginator.getPages()){
                        paginator.setPage(paginator.getPage()+1);
                        refresh();
                        notifyOnPaginatorChangePageListener(new OnPaginatorChangePageEvent(this,paginator));
                    }
                }
            });
            /*bouton last*/
            buttonLast.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    paginator.setPage(paginator.getPages());
                    refresh();
                    notifyOnPaginatorChangePageListener(new OnPaginatorChangePageEvent(this,paginator));
                }
            });
            /*champ page*/
            fieldPage.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                    if (t1){
                        fieldPage.setText(String.valueOf(paginator.getPage()));
                    } else {
                        if (NumberUtils.isParsable(fieldPage.getText())){
                            paginator.setPage(Integer.parseInt(fieldPage.getText()));
                            if (paginator.getPage() < 1){
                                //Si la page demandée est inférieur à 1
                                //on défini la page demandée comme égale à 1
                                paginator.setPage(1);
                            } else if (paginator.getPage() > paginator.getPages()) {
                                //Si la page demandée est supérieur au nombre de page total
                                //on défini la page demandée comme égale au nombre de page
                                paginator.setPage(paginator.getPages());
                            }
                            refresh();
                            notifyOnPaginatorChangePageListener(new OnPaginatorChangePageEvent(this,paginator));
                        }
                        showPageCounterFormatted();
                    }
                }
            });
        } catch (ConfigurationException e) {
            showException(e);
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
     * Ajoute  un listener sur le début ou la fin d'un processus
     * Dans ce cas la recherche des données suite à un changement de page
     * @param listener
     */
    public void addOnProcessListener(OnProcessListener listener) { onProcessListeners.add(listener); }

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
     * Notifie le listener du changement de page
     * @param evt
     */
    private void notifyOnPaginatorChangePageListener(OnPaginatorChangePageEvent evt){
        for (OnPaginatorChangePageListener listener : onPaginatorChangePageListeners){
            listener.onPaginatorChangePage(evt);
        }
    }

    /**
     * Notifie le listener de process
     * @param evt
     */
    private void notifyOnProcessListener(OnProcessEvent evt){
        for (OnProcessListener listener : onProcessListeners){
            listener.onProcess(evt);
        }
    }

    /**
     * Retourne l'objet Paginator
     * @return
     */
    public Paginator getPaginator() {
        return paginator;
    }

    /**
     * Se charge de rafraichir l'affichage au départ d'un objet Paginator
     */
    public void refresh(){
        //Notifie le début du processus
        notifyOnProcessListener(new OnProcessEvent(this, true));
        //Charge le paginator avec le résultat de la requete vers la db
        //paginator = mapper.loadPaginator(paginator);
        MapperPaginatorService service = new MapperPaginatorService(paginator);
        service.addOnProcessListener(new OnProcessListener() {
            @Override
            public void onProcess(OnProcessEvent evt) {
                if (evt.isStarted()) {
                    paginator = service.getValue();
                    //On affiche les Items
                    try {
                        displayItemDashboard.display(paginator);
                    } catch (IOException e) {
                        notifyOnProcessListener(new OnProcessEvent(this, false));
                        showException(e);
                    }
                    //On mets à jour le compteur de page
                    showPageCounterFormatted();
                    //notifie la fin du processus
                    notifyOnProcessListener(new OnProcessEvent(this, false));
                }
            }
        });
        service.start();
    }

    /**
     * Format l'affichage de la page actuelle et du nombre total de page
     */
    private void showPageCounterFormatted(){
        StringJoiner joiner = new StringJoiner(" / ");
        joiner.add(String.valueOf(paginator.getPage()));
        joiner.add(String.valueOf(paginator.getPages()));
        fieldPage.setText(joiner.toString());
    }

    public IOnDisplayItemDashboardChange getDisplayItemDashboard() {
        return displayItemDashboard;
    }

}
