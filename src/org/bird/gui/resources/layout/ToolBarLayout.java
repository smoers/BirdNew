package org.bird.gui.resources.layout;

import javafx.scene.control.ToolBar;

import java.util.ArrayList;

/**
 * ette classe va permettre de configurer le layout d'une ToolBar
 */
public class ToolBarLayout extends NodeLayout<ToolBar> {


    /**
     * Constructeur
     * @param node
     * @param layoutParameters
     */
    public ToolBarLayout(ToolBar node, LayoutParameters layoutParameters) {
        super(node, layoutParameters);
    }

    @Override
    public void apply() {
        ArrayList<NodeLayout> nodeLayouts = layoutParameters.<ArrayList<NodeLayout>>getParameter(LayoutParameters.CHILDREN, new ArrayList<NodeLayout>());
        for(LayoutInterface nodeLayout : nodeLayouts){
            nodeLayout.apply();
        }
        String selector = layoutParameters.<String>getParameter(LayoutParameters.SELECTOR,"");
        node.getStyleClass().add(selector);
    }
}
