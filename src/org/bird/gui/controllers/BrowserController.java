package org.bird.gui.controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.bird.configuration.exceptions.ConfigurationException;
import org.bird.gui.common.FXMLLoaderImpl;
import org.bird.gui.common.ShowException;
import org.bird.gui.common.dialog.DialogPrompt;
import org.bird.gui.resources.controls.DefaultAnchorPaneZero;
import org.bird.gui.resources.controls.Favorite;
import org.bird.gui.resources.images.ImageProvider;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
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

    private Window owner;
    private JsonArray favorites;
    private String home;
    private String search;
    private WebView webView = new WebView();
    private WebEngine webEngine;
    private Stage stage = new Stage();
    private String title = "Browser";


    public BrowserController(Window owner) throws ConfigurationException {
        this.owner = owner;
        favorites = getConfigurationLayout().get("layout.browser.favorites").getAsJsonArray();
        home = getConfigurationLayout().get("layout.browser.home").getAsString();
        search = getConfigurationLayout().get("layout.browser.search").getAsString();
        webEngine = webView.getEngine();
        setInternationalizationBundle(internationalizationBuilder.getInternationalizationBundle(getClass()));
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
            favorites.forEach(new Consumer<JsonElement>() {
                @Override
                public void accept(JsonElement jsonElement) {
                    if (jsonElement.isJsonObject()) {
                        //On crée un item favorite pour chaque entrée dans la liste
                        try {
                            addMenuItemFavorite(jsonElement.getAsJsonObject().get("name").getAsString(), jsonElement.getAsJsonObject().get("url").getAsString());
                        } catch (MalformedURLException e) {
                            loggers.error(logger, loggers.messageFactory.newMessage("Malformed URL", this));
                        }
                    }
                }
            });
            //Permet d'ajouter un favoris
            miAddFavorite.setOnAction(actionEvent -> {
                DialogPrompt dialogPrompt = new DialogPrompt();
                dialogPrompt.setTitle(getInternationalizationBundle().getString("Add favorite"));
                dialogPrompt.setHeaderText(getInternationalizationBundle().getString("Enter your favorite name"));
                dialogPrompt.setContentText(getInternationalizationBundle().getString("Name"));
                Optional<String> result = dialogPrompt.showAndWait();
                result.ifPresent(name -> {
                    //S'il y a un résultat on l'ajoute dans le menu et le sauve
                    try {
                        addMenuItemFavorite(result.get(), flURL.getText());
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("name", result.get());
                        jsonObject.addProperty("url", flURL.getText());
                        getConfigurationLayout().edit("layout.browser.favorites",jsonObject);
                        getConfigurationLayout().write();
                    } catch (ConfigurationException | IOException e) {
                        ShowException showException = new ShowException(e);
                        showException.show(getInternationalizationBundle());
                    }
                });
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
     * Ajoute une entrée dans le menu des favoris
     * @param name
     * @param url
     * @throws MalformedURLException
     */
    private void addMenuItemFavorite(String name, String url) throws MalformedURLException {
        Favorite favorite = new Favorite(
                name,
                url
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
