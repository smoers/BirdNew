package org.bird.gui.common.tableviewextended;

public abstract class Transposer<T,S> implements ITransposer<T> {

    protected S object;

    public Transposer(S object) {
        this.object = object;
    }

    @Override
    public abstract T getValue(String propertyName);
}
