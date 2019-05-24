package org.bird.gui.common.i18n;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import org.bird.i18n.InternationalizationBundle;
import org.bird.utils.Utils;

import java.util.function.Consumer;

/**
 * Se charge de traduire les textes des objets Label contenu dans un objet Panel
 */
public class Translator {

    private String prefix;
    private InternationalizationBundle internationalizationBundle;

    /**
     * Contructeur
     * @param prefix
     * @param internationalizationBundle
     */
    public Translator(String prefix, InternationalizationBundle internationalizationBundle) {
        this.prefix = prefix;
        this.internationalizationBundle = internationalizationBundle;
    }

    /**
     * Traduit le texte des objets Label contenu dans l'objet Pane
     * Il faut que l'ID de l'objet Label commence par le préfix et soit une instance du type Label
     * @param pane
     */
    public void translate(Pane pane){
        ObservableList<Node> nodes = pane.getChildren();
        nodes.forEach(new Consumer<Node>() {
            @Override
            public void accept(Node node) {
                String pattern = "^"+prefix;
                if (Utils.findString(pattern,node.getId()) && node instanceof Label){
                    Label label = (Label) node;
                    label.setText(internationalizationBundle.getString(label.getText()));
                }
            }
        });
    }
}
