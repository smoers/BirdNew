package org.bird.gui.common.i18n;

import javafx.collections.ObservableList;
import javafx.scene.Node;

public interface ITranslatorControl<T> {

    public abstract ObservableList<T> getNodes();

}
