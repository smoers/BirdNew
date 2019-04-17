package org.bird.gui.resources.controls;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import org.bird.gui.events.OnLeftClickEvent;
import org.bird.gui.listeners.OnLeftClickListener;

import java.util.ArrayList;
import java.util.function.Consumer;

public class TextLong{

    protected HBox hbox;
    protected LabelLimited label;
    protected Button button;
    protected ArrayList<OnLeftClickListener> onLeftClickListeners = new ArrayList<>();

    public TextLong(){
        initialize();
    }

    private void initialize(){
        //Set label
        label = new LabelLimited();
        label.setLimitedSize(20);
        label.getStylesheets().add(getClass().getResource("/org/bird/gui/resources/css/fxcss.css").toExternalForm());
        label.getStyleClass().add("form_label_field");
        //Set bouton
        button = new Button();
        button.setId("buttonShow");
        button.getStylesheets().add(getClass().getResource("/org/bird/gui/resources/css/fxcss.css").toExternalForm());
        button.getStyleClass().add("smallButton");
        button.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.isPrimaryButtonDown()){
                    notifyOnLeftClickListener(new OnLeftClickEvent(this, mouseEvent.getClickCount()));
                }
            }
        });
        //Set Container
        hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.getStylesheets().add(getClass().getResource("/org/bird/gui/resources/css/fxcss.css").toExternalForm());
        HBox.setMargin(label, new Insets(5,5,5,5));
        HBox.setMargin(button, new Insets(5,5,5,5));
        hbox.getChildren().addAll(label, button);
    }

    public HBox getHbox() {
        return hbox;
    }

    public LabelLimited getLabel() {
        return label;
    }

    public Button getButton() {
        return button;
    }

    /**
     * Enregistre un listener
     * @param listener
     */
    public void addOnLeftClickListener(OnLeftClickListener listener){
        onLeftClickListeners.add(listener);
    }

    /**
     * Notifie les listeners
     * @param evt
     */
    protected void notifyOnLeftClickListener(OnLeftClickEvent evt){
        onLeftClickListeners.forEach(new Consumer<OnLeftClickListener>() {
            @Override
            public void accept(OnLeftClickListener listener) {
                listener.onLeftClick(evt);
            }
        });
    }
}
