package org.bird.gui.controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.bird.configuration.ConfigurationFavoritesBrowser;
import org.bird.configuration.exceptions.ConfigurationException;
import org.bird.gui.common.ColumnFactoryValue;
import org.bird.gui.common.ConverterTableViewColumn;
import org.bird.gui.common.FXMLLoaderImpl;
import org.bird.gui.common.ShowException;
import org.bird.gui.common.dialog.DialogPrompt;
import org.bird.gui.common.tableviewextended.*;
import org.bird.gui.events.OnLeftClickEvent;
import org.bird.gui.listeners.OnLeftClickListener;
import org.bird.gui.resources.controls.DefaultAnchorPaneZero;
import org.bird.gui.resources.controls.Favorite;
import org.bird.gui.resources.images.ImageProvider;
import org.bird.logger.ELoggers;
import org.bird.logger.Loggers;
import org.bird.utils.GsonUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.function.Consumer;

/**
 * Simple browser
 */
public class BrowserController extends ProtectedController implements Initializable {

    @FXML
    private TextField flURL;
    @FXML
    private Button buttonBrowserHome;
    @FXML
    private MenuButton buttonBrowserFavorite;
    @FXML
    private BorderPane borderPaneContainer;
    @FXML
    private MenuItem miAddFavorite;
    @FXML
    private MenuItem miEditDeleteFavorites;

    private Window owner;
    private JsonArray favorites;
    private String home;
    private String search;
    private WebView webView = new WebView();
    private WebEngine webEngine;
    private Stage stage = new Stage();
    private String title = "Browser";
    private ConfigurationFavoritesBrowser configurationBrowser;
    private Loggers loggers = Loggers.getInstance();


    public BrowserController(Window owner) throws ConfigurationException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.owner = owner;
        configurationBrowser = configurationBuilder.<ConfigurationFavoritesBrowser>get("layout",ConfigurationFavoritesBrowser.class);
        favorites = configurationBrowser.getFavorites();
        home = getConfigurationLayout().get("layout.browser.home").getAsString();
        search = getConfigurationLayout().get("layout.browser.search").getAsString();
        webEngine = webView.getEngine();
        setInternationalizationBundle(internationalizationBuilder.getInternationalizationBundle(getClass()));
        loggers.setDefaultLogger(ELoggers.GUI);
    }

    /**
     * affiche la fenetre
     * @throws ConfigurationException
     * @throws IOException
     */
    public void show() throws ConfigurationException, IOException {
        FXMLLoaderImpl loaderImpl = new FXMLLoaderImpl();
        FXMLLoader loader = loaderImpl.getFXMLLoader("browser");
        loader.setController(this);
        stage.setScene(new Scene(loader.load()));
        stage.getIcons().add(ImageProvider.getLogoImage());
        stage.setTitle(title);
        stage.initModality(Modality.NONE);
        stage.initOwner(owner);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            setLanguage();
            //Crée le container pour l'objet WebView
            AnchorPane node = new DefaultAnchorPaneZero(webView);
            node.setPadding(new Insets(5.0));
            borderPaneContainer.setCenter(node);
            //permet de mettre a jour le champ url après un charge depuis le moteur de recherche
            webEngine.getLoadWorker().stateProperty().addListener((obs, oldstate, newstate) -> {
                if (newstate == Worker.State.SUCCEEDED) {
                    flURL.setText(webEngine.getLocation());
                }
            });
            //Action la touche Enter
            flURL.setOnKeyPressed(keyEvent -> {
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    load(flURL.getText());
                }
            });
            //Action suite à la perte du focus
            flURL.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                    if (!newValue) {
                        load(flURL.getText());
                    }
                }
            });
            //Bouton pour revenir à la home page
            buttonBrowserHome.setOnMousePressed(mouveEvent -> {
                if (mouveEvent.isPrimaryButtonDown()) {
                    load(home);
                }
            });
            //Charge les favoris dans le menu
            createMenuItemFavorite();

            //Permet d'ajouter un favoris
            miAddFavorite.setOnAction(actionEvent -> {
                System.out.println(actionEvent.getEventType().getName());
                DialogPrompt dialogPrompt = new DialogPrompt();
                dialogPrompt.setTitle(getInternationalizationBundle().getString("Add favorite"));
                dialogPrompt.setHeaderText(getInternationalizationBundle().getString("Enter your favorite name"));
                dialogPrompt.setContentText(getInternationalizationBundle().getString("Name"));
                Optional<String> result = dialogPrompt.showAndWait();
                result.ifPresent(name -> {
                    //S'il y a un résultat on l'ajoute dans le menu et le sauve
                    try {
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("id", UUID.randomUUID().toString());
                        jsonObject.addProperty("name", result.get());
                        jsonObject.addProperty("url", flURL.getText());
                        addMenuItemFavorite(jsonObject);
                        configurationBrowser.addFavorites(jsonObject);
                    } catch (ConfigurationException | IOException e) {
                        ShowException showException = new ShowException(e);
                        showException.show(getInternationalizationBundle());
                    }
                });
            });

            miEditDeleteFavorites.setOnAction(actionEvent -> {
                //Objet DataView
                DataViewTableController dataView = new DataViewTableController(borderPaneContainer.getScene().getWindow());
                dataView.setShowCancel(true);
                dataView.setShowSave(true);
                dataView.setEditable(true);
                dataView.addOnLeftClickListener(new OnLeftClickListener() {
                    @Override
                    public void onLeftClick(OnLeftClickEvent evt) {
                        if (evt.getId().equalsIgnoreCase("buttonSave")){
                            dataView.getData().forEach(new Consumer<ConverterTableViewColumn>() {
                                @Override
                                public void accept(ConverterTableViewColumn converterTableViewColumn) {
                                    JsonObject jsonObject = (JsonObject) converterTableViewColumn.getSource();
                                    jsonObject.addProperty("name",converterTableViewColumn.<String>get("name").getValue());
                                    jsonObject.addProperty("url",converterTableViewColumn.<String>get("url").getValue());
                                    try {
                                        configurationBrowser.editFavorites(jsonObject);
                                    } catch (ConfigurationException e) {
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                        dataView.close();
                    }
                });
                //Crée les colonnes
                List<ITableColumnExtended> listColumn = new ArrayList<>();
                listColumn.add(new TableColumnStringExtended("Name","name", TransposerJsonObjectToString.class));
                listColumn.add(new TableColumnStringExtended("Url","url",TransposerJsonObjectToString.class));
                try {
                    List<JsonElement> listData = GsonUtils.ConvertJsonArrayToList(configurationBrowser.getFavorites());
                    TableViewColumDataFactory factory = new TableViewColumDataFactory(listColumn, listData);
                    dataView.setContent(factory.getTableColumn());
                    dataView.setData(factory.getTableData());
                    dataView.show();
                } catch (ConfigurationException | IOException e) {
                    loggers.error(loggers.messageFactory.newMessage(e.getMessage(),this));
                }

            });
            //On affiche l'url home
            flURL.setText(home);
            //on charge la page home
            load(home);
        } catch (Exception e){
            showException(e);
        }
    }

    @Override
    public void setLanguage() {
        getTranslator(getInternationalizationBundle(),"button","mi").translate(borderPaneContainer);
    }

    /**
     * Controle la validité de l'url string et charge la page
     * @param strURL
     */
    public void load(String strURL){
        //on evite de recharger la page si l'url est identique à celle déjà chargée
        if (!strURL.equalsIgnoreCase(webEngine.getLocation())) {
            try {
                URL url = new URL(strURL);
                LoadWebPage loadWebPage = new LoadWebPage(webEngine, url);
                loadWebPage.start();

            } catch (MalformedURLException e) {
                load(search.replace("%%1%%", strURL));
            }
        }
    }

    /**
     * Supprime les favoris
     */
    private void cleanupMenuItemFavorite(){
        buttonBrowserFavorite.getItems().forEach(new Consumer<MenuItem>() {
            @Override
            public void accept(MenuItem menuItem) {
                if (menuItem instanceof Favorite){
                    buttonBrowserFavorite.getItems().remove(menuItem);
                }
            }
        });
    }

    /**
     * Création du Menu avec les favoris
     * @throws ConfigurationException
     */
    private void createMenuItemFavorite() throws ConfigurationException {
        favorites = configurationBrowser.getFavorites();
        favorites.forEach(new Consumer<JsonElement>() {
            @Override
            public void accept(JsonElement jsonElement) {
                if (jsonElement.isJsonObject()) {
                    //On crée un item favorite pour chaque entrée dans la liste
                    try {
                        addMenuItemFavorite(jsonElement.getAsJsonObject());
                    } catch (MalformedURLException e) {
                        loggers.error(logger, loggers.messageFactory.newMessage("Malformed URL", this));
                    }
                }
            }
        });
    }

    /**
     * Ajoute une entrée dans le menu des favoris
     * @param jsonObject
     * @throws MalformedURLException
     */
    private void addMenuItemFavorite(JsonObject jsonObject) throws MalformedURLException {
        Favorite favorite = new Favorite(
                jsonObject.get("id").getAsString(),
                jsonObject.get("name").getAsString(),
                jsonObject.get("url").getAsString()
        );
        favorite.setOnAction(actionEvent -> {
            Favorite item = (Favorite) actionEvent.getSource();
            load(item.getUrl().toExternalForm());
        });
        buttonBrowserFavorite.getItems().add(favorite);
    }

    private class LoadWebPage extends Service<WebEngine>{

        private WebEngine webEngine;
        private URL url;

        public LoadWebPage(WebEngine webEngine, URL url) {
            this.webEngine = webEngine;
            this.url = url;
        }

        @Override
        protected Task<WebEngine> createTask() {
            return new Task<WebEngine>() {
                @Override
                protected WebEngine call() throws Exception {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            webEngine.load(url.toExternalForm());
                        }
                    });
                    return null;
                }
            };
        }
    }

}
