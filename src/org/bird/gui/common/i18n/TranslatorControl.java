package org.bird.gui.common.i18n;

import javafx.collections.ObservableList;


public abstract class TranslatorControl<T> implements ITranslatorControl<T>{

    protected Object node;

    public TranslatorControl(Object node) {
        this.node = node;
    }

    @Override
    public abstract ObservableList<T> getNodes();
}
