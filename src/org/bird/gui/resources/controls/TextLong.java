package org.bird.gui.resources.controls;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class TextLong {

    protected HBox hbox;
    protected LabelLimited label;
    protected Button button;

    public TextLong(){
        initialize();
    }

    private void initialize(){
        hbox = new HBox();
        label = new LabelLimited();
        button = new Button();
        button.setId("buttonShow");
        hbox.getStyleClass().add(getClass().getResource("org/bird/gui/resources/css/fxcss.css").toExternalForm());
        hbox.setStyle("root");
        label.getStyleClass().add(getClass().getResource("org/bird/gui/resources/css/fxcss.css").toExternalForm());
        label.setStyle("form_label_field");
        button.getStyleClass().add(getClass().getResource("org/bird/gui/resources/css/fxcss.css").toExternalForm());
        button.setStyle("smallButton");
        HBox.setMargin(label, new Insets(5,5,5,5));
        HBox.setMargin(button, new Insets(5,5,5,5));
        hbox.getChildren().addAll(label,button);
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
}
