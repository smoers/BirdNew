package org.bird.gui.common.tableviewextended;

public abstract class Transposer<T> implements ITransposer<T> {

    protected Object object;

    public Transposer(Object object) {
        this.object = object;
    }

    @Override
    public abstract T getValue(String propertyName);
}
