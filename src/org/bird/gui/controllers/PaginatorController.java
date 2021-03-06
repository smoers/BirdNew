package org.bird.gui.controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
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
import org.bird.gui.controllers.display.IDisplayDashboard;
import org.bird.gui.events.OnPageChangeEvent;
import org.bird.gui.events.OnProcessEvent;
import org.bird.gui.listeners.OnPageChangeListener;
import org.bird.gui.listeners.OnProcessListener;
import org.bird.gui.services.MapperPaginatorService;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.function.Consumer;

/**
 * Classe qui assure le visuel d'un système de pagination
 */
public class PaginatorController extends ProtectedController implements Initializable {

    /**
     * Container destiné à accueillir les items
     */
    private IDisplayDashboard displayDashboard;
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

    private ArrayList<OnPageChangeListener> onPageChangeListeners = new ArrayList<>();

    /**
     * Contructeur
     * @param paginator
     * @param displayDashboard
     */
    public PaginatorController(Paginator paginator, IDisplayDashboard displayDashboard) {
        this.paginator = paginator;
        this.displayDashboard = displayDashboard;
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
            //Défini le combobox avec les valeurs
            JsonArray jsonArray = getConfigurationLayout().get("layout.item_by_page.large").getAsJsonArray();
            jsonArray.forEach(new Consumer<JsonElement>() {
                @Override
                public void accept(JsonElement jsonElement) {
                    comboNbrItems.getItems().add(jsonElement.getAsInt());
                }
            });
            paginator.setItemsByPage(getConfigurationLayout().get("layout.item_by_page.value").getAsInt());
            comboNbrItems.setValue(paginator.getItemsByPage());
            //Affiche le champs avec la page en cours et le nombre de page
            showPageCounterFormatted();
            //Events
            /* Combo avec le nombre d'item par page*/
            comboNbrItems.valueProperty().addListener(new ChangeListener<Integer>() {
                @Override
                public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                    //léve un événement de changement de page
                    notifyOnPageChangeListener(new OnPageChangeEvent(this,OnPageChangeEvent.TYPE.NUMBEROFITEMS,paginator.getPage()));
                    paginator.setItemsByPage(observableValue.getValue());
                    //Cela permet de sauvegarder le nombre d'items par page.
                    try {
                        getConfigurationLayout().edit("layout.item_by_page.value",paginator.getItemsByPage());
                        getConfigurationLayout().write();
                    } catch (IOException | ConfigurationException e) {
                        showException(e);
                    }
                    refresh();
                }
            });
            /* bouton first */
            buttonFirst.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    paginator.setPage(1);
                    //léve un événement de changement de page
                    notifyOnPageChangeListener(new OnPageChangeEvent(this,OnPageChangeEvent.TYPE.FIRST,paginator.getPage()));
                    refresh();
                }
            });
            /*bouton previous*/
            buttonPrevious.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (paginator.getPage() > 1 ){
                        paginator.setPage(paginator.getPage()-1);
                        //léve un événement de changement de page
                        notifyOnPageChangeListener(new OnPageChangeEvent(this,OnPageChangeEvent.TYPE.PREVIOUS,paginator.getPage()));
                        refresh();
                    }
                }
            });
            /*bouton next*/
            buttonNext.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if(paginator.getPage() < paginator.getPages()){
                        paginator.setPage(paginator.getPage()+1);
                        //léve un événement de changement de page
                        notifyOnPageChangeListener(new OnPageChangeEvent(this,OnPageChangeEvent.TYPE.NEXT,paginator.getPage()));
                        refresh();
                    }
                }
            });
            /*bouton last*/
            buttonLast.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    paginator.setPage(paginator.getPages());
                    //léve un événement de changement de page
                    notifyOnPageChangeListener(new OnPageChangeEvent(this,OnPageChangeEvent.TYPE.LAST,paginator.getPage()));
                    refresh();
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
                            //léve un événement de changement de page
                            notifyOnPageChangeListener(new OnPageChangeEvent(this,OnPageChangeEvent.TYPE.COUNTER,paginator.getPage()));
                            refresh();
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
                        //cette objet va éxecuter un service charger du cahrgempent des items
                        displayDashboard.display(paginator);
                    } catch (IOException e) {
                        showException(e);
                    }
                    //On mets à jour le compteur de page
                    showPageCounterFormatted();
                    //notifie la fin du processus
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

    public IDisplayDashboard getDisplayDashboard() {
        return displayDashboard;
    }

    /**
     * écouteur pour le changement de page
     * @param listener
     */
    public void addOnPageChangeListener(OnPageChangeListener listener){
        onPageChangeListeners.add(listener);
    }

    /**
     * Notifie le changement de page
     * @param evt
     */
    private void notifyOnPageChangeListener(OnPageChangeEvent evt){
        onPageChangeListeners.forEach(new Consumer<OnPageChangeListener>() {
            @Override
            public void accept(OnPageChangeListener listener) {
                listener.onChangePage(evt);
            }
        });
    }

}
