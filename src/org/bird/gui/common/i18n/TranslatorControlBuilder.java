package org.bird.gui.common.i18n;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.lang.reflect.InvocationTargetException;

/**
 * Cette classe permet de disposer de la bonne classe permettant
 * de lister les enfants d'un node de type Control.  Les nodes de type Control
 * ne dispose pas d'une méthode getChildren().
 *
 */
public class TranslatorControlBuilder {
    private static TranslatorControlBuilder ourInstance = new TranslatorControlBuilder();

    public static TranslatorControlBuilder getInstance() {
        return ourInstance;
    }

    private TranslatorControlBuilder() {
    }

    /**
     * Retourne l'instance correspondant au type de Node
     * @param node
     * @return
     */
    public ITranslatorControl getTranslatorControl(Node node){
        ITranslatorControl translatorControl = null;
        if (node instanceof Pane){
            translatorControl = new TranslatorControlPane(node);
        } else {
            String simpleName = node.getClass().getSimpleName();
            try {
                Class<TranslatorControl> aClass = (Class<TranslatorControl>) Class.forName("org.bird.gui.common.i18n.TranslatorControl" + simpleName);
                translatorControl = aClass.getDeclaredConstructor(Node.class).newInstance(node);
            } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                return null;
            }
        }
        return translatorControl;
    }
}
