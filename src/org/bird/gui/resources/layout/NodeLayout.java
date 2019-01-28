package org.bird.gui.resources.layout;

import javafx.scene.Node;

public abstract class NodeLayout<T> implements LayoutInterface {

    protected T node;
    protected LayoutParameters layoutParameters;

    public NodeLayout(T node, LayoutParameters layoutParameters) {
        this.node = node;
        this.layoutParameters = layoutParameters;
    }

    public T getNode(){
        return node;
    }

    public LayoutParameters getLayoutParameters() { return layoutParameters; }

    @Override
    public abstract void apply();

}
