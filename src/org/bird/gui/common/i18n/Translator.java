package org.bird.gui.common.i18n;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Labeled;
import javafx.scene.layout.Pane;
import org.bird.i18n.InternationalizationBundle;
import org.bird.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Se charge de traduire les textes des objets Label contenu dans un objet Panel
 */
public class Translator {

    private String[] prefix;
    private InternationalizationBundle internationalizationBundle;

    /**
     * Contructeur
     * @param prefix
     * @param internationalizationBundle
     */
    public Translator(InternationalizationBundle internationalizationBundle,String ... prefix) {
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
        List<String> list = new ArrayList<>(Arrays.asList(prefix));

        nodes.forEach(new Consumer<Node>() {
            @Override
            public void accept(Node node) {
                list.forEach(new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        String pattern = "^"+s;
                        if (Utils.findString(pattern,node.getId()) && node instanceof Labeled){
                            Labeled labeled = (Labeled) node;
                            labeled.setText(internationalizationBundle.getString(labeled.getText()));
                        }
                    }
                });
            }
        });
    }
}
