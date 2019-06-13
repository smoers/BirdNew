package org.bird.gui.common.i18n;

import javafx.collections.ObservableList;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

public class TranslatorControlMenuButton extends TranslatorControl<MenuItem> {

    public TranslatorControlMenuButton(Object node) {
        super(node);
    }

    @Override
    public ObservableList<MenuItem> getNodes() {
        return ((MenuButton) node).getItems();
    }
}
