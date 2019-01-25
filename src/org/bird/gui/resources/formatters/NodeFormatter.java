package org.bird.gui.resources.formatters;

import javafx.scene.Node;

public abstract class NodeFormatter<T> implements FormatterInterface {

    protected Node node;

    public NodeFormatter(Node node) {
        this.node = node;
    }

    public T getNode(){
        return (T) node;
    }

    @Override
    public abstract void format();
}
