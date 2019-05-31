package org.bird.gui.controllers;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.bird.configuration.exceptions.ConfigurationException;
import org.bird.gui.common.FXMLLoaderImpl;
import org.bird.gui.events.OnLeftClickEvent;
import org.bird.gui.listeners.OnLeftClickListener;
import org.bird.gui.resources.controls.DefaultAnchorPaneZero;
import org.bird.gui.resources.images.ImageProvider;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class DataViewController implements Initializable {

    @FXML
    private Button buttonSave;
    @FXML
    private Button buttonCancel;
    @FXML
    private BorderPane borderPane;

    private TextArea txText;
    private ListView<String> lvList;
    private WebView webView;
    private boolean showCancel = true;
    private boolean showSave = true;
    private String title;
    private String text = null;
    private ObservableList<String> list = null;
    private URL url = null;
    private Window owner;
    private Stage stage = new Stage();
    private ArrayList<OnLeftClickListener> onLeftClickListeners = new ArrayList<>();

    public DataViewController(Window owner){
        this.owner = owner;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonCancel.setVisible(isShowCancel());
        buttonSave.setVisible(isShowSave());
        /**
         * Si un texte est disponible il sera affiché en priorité
         * Dans le cas contraire si une list a été chargée, elle sera affichée.
        **/

        if (getText() != null){
            borderPane.setCenter(showText());
        } else if (getList() != null){
            borderPane.setCenter(showList());
        } else if (getUrl() != null){
            borderPane.setCenter(showWebView());
        }

        /** Events **/
        buttonSave.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.isPrimaryButtonDown()){
                    notifyOnLeftClickListener(new OnLeftClickEvent(this, mouseEvent.getClickCount(), buttonSave.getId()));
                }
            }
        });
        buttonCancel.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if ((mouseEvent.isPrimaryButtonDown())){
                    notifyOnLeftClickListener(new OnLeftClickEvent(this, mouseEvent.getClickCount(), buttonCancel.getId()));
                }
            }
        });
    }



    /**
     * affiche la fenetre
     * @throws ConfigurationException
     * @throws IOException
     */
    public void show() throws ConfigurationException, IOException {
        FXMLLoaderImpl loaderImpl = new FXMLLoaderImpl();
        FXMLLoader loader = loaderImpl.getFXMLLoader("dataview");
        loader.setController(this);
        stage.setScene(new Scene(loader.load()));
        stage.getIcons().add(ImageProvider.getLogoImage());
        stage.setTitle(title);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(owner);
        stage.show();
    }

    /**
     * Ferme la fenetre
     */
    public void close(){
        stage.close();
    }

    /**
     *
     * @return
     */
    public boolean isShowCancel() {
        return showCancel;
    }

    /**
     *
     * @param showCancel
     */
    public void setShowCancel(boolean showCancel) {
        this.showCancel = showCancel;
    }

    /**
     *
     * @return
     */
    public boolean isShowSave() {
        return showSave;
    }

    /**
     *
     * @param showSave
     */
    public void setShowSave(boolean showSave) {
        this.showSave = showSave;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     */
    public String getText() {
        return text;
    }

    /**
     *
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    public ObservableList<String> getList() {
        return list;
    }

    public void setList(ObservableList<String> list) {
        this.list = list;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    /**
     * Ajoute un listener
     * @param listener
     */
    public void addOnLeftClickListener(OnLeftClickListener listener){
        onLeftClickListeners.add(listener);
    }

    /**
     * Notifie les listener
     * @param evt
     */
    private void notifyOnLeftClickListener(OnLeftClickEvent evt){
        onLeftClickListeners.forEach(new Consumer<OnLeftClickListener>() {
            @Override
            public void accept(OnLeftClickListener listener) {
                listener.onLeftClick(evt);
            }
        });
    }

    /**
     * défini une TextArea si il y a un text de chargé
     * @return
     */
    private AnchorPane showText(){
        txText = new TextArea();
        txText.setWrapText(true);
        txText.setEditable(isShowSave());
        txText.setText(getText());
        AnchorPane node = new DefaultAnchorPaneZero(txText);
        node.setPadding(new Insets(5.0));
        return node;
    }

    /**
     * défini un ListView si il a une list de chargée
     * @return
     */
    private AnchorPane showList(){
        lvList = new ListView<>();
        lvList.setEditable(isShowSave());
        lvList.setItems(getList());
        AnchorPane node = new DefaultAnchorPaneZero(lvList);
        node.setPadding(new Insets(5.0));
        return node;
    }

    private AnchorPane showWebView(){
        webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.load(url.toExternalForm());
        AnchorPane node = new DefaultAnchorPaneZero(webView);
        node.setPadding(new Insets(5.0));
        return node;
    }
}
