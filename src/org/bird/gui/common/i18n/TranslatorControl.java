package org.bird.gui.common.i18n;

import javafx.collections.ObservableList;
import javafx.scene.Node;

public abstract class TranslatorControl implements ITranslatorControl{

    protected Node node;

    public TranslatorControl(Node node) {
        this.node = node;
    }

    @Override
    public abstract ObservableList<Node> getNodes();
}
