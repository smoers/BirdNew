package org.bird.gui.resources.layout;

import javafx.scene.control.Button;

/**
 * Cette classe va permettre de configurer le layout d'un button
 */
public class ButtonLayout extends NodeLayout<Button> {

    /**
     * Constructeur
     * @param node
     * @param layoutParameters
     */
    public ButtonLayout(Button node, LayoutParameters layoutParameters) {
        super(node, layoutParameters);
    }

    /**
     * Applique le layout
     */
    @Override
    public void apply() {
        String selector = layoutParameters.<String>getParameter("selector","");
        boolean ifText = layoutParameters.<Boolean>getParameter("iftext", true);
        node.getStyleClass().add(selector);
        if (!ifText)
            node.setText("");
    }
}
