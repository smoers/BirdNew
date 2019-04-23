package org.bird.gui.controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.bird.configuration.exceptions.ConfigurationException;
import org.bird.gui.common.FXMLLoaderImpl;
import org.bird.gui.events.OnLeftClickEvent;
import org.bird.gui.listeners.OnLeftClickListener;
import org.bird.gui.resources.images.ImageProvider;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class TextLongController implements Initializable {

    @FXML
    private Button buttonSave;
    @FXML
    private Button buttonCancel;
    @FXML
    private TextArea txText;

    private boolean showCancel = true;
    private boolean showSave = true;
    private String title;
    private String text;
    private Window owner;
    private Stage stage = new Stage();
    private ArrayList<OnLeftClickListener> onLeftClickListeners = new ArrayList<>();

    public TextLongController(Window owner){
        this.owner = owner;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonCancel.setVisible(isShowCancel());
        buttonSave.setVisible(isShowSave());
        txText.setEditable(isShowSave());
        txText.setWrapText(true);
        txText.setText(getText());
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
        FXMLLoader loader = loaderImpl.getFXMLLoader("textlong");
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
}
