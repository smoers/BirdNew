package org.bird.gui.common.i18n;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Labeled;
import javafx.scene.control.MenuItem;
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
        ArrayList<Object> nodes = getChildrenRecursive(pane, new ArrayList<Object>());
        List<String> list = new ArrayList<>(Arrays.asList(prefix));

        nodes.forEach(new Consumer<Object>() {
            @Override
            public void accept(Object node) {
                list.forEach(new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        String pattern = "^"+s;
                        if (node instanceof Labeled){
                            //Si le node est une instance de Labeled on le cast et lui applique la condition
                            Labeled labeled = (Labeled) node;
                            if (labeled.getId() != null && Utils.findString(pattern,labeled.getId())) {
                                labeled.setText(internationalizationBundle.getString(labeled.getText()));
                            }
                        } else if(node instanceof MenuItem){
                            //Si le node est une instance de MenuItem on le cast et lui applique la condition
                            MenuItem menuItem = (MenuItem) node;
                            if (menuItem.getId() != null && Utils.findString(pattern,menuItem.getId())) {
                                menuItem.setText(internationalizationBundle.getString(menuItem.getText()));
                            }
                        }
                    }
                });
            }
        });
    }

    /**
     * Permet de parcourir les Nodes de manière récursive
     * @param node
     * @param list
     * @return
     */
    private ArrayList<Object> getChildrenRecursive(Object node,ArrayList<Object> list){

        TranslatorControlBuilder translatorControlBuilder = TranslatorControlBuilder.getInstance();
        ITranslatorControl translatorControl = translatorControlBuilder.getTranslatorControl(node);
        if (translatorControl != null) {
            ObservableList<Object> nodes = translatorControl.getNodes();
            nodes.forEach(new Consumer<Object>() {
                @Override
                public void accept(Object node) {
                    list.add(node);
                    if (node instanceof Node) {
                        getChildrenRecursive(node, list);
                    }
                }
            });
        }
        return list;
    }


}
