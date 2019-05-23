package org.bird.gui.resources.controls;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import org.bird.gui.events.OnLeftClickEvent;
import org.bird.gui.listeners.OnLeftClickListener;

import java.util.ArrayList;
import java.util.function.Consumer;

public abstract class AbstractButtonShow {

    protected HBox hbox;
    protected Button button;
    protected ArrayList<OnLeftClickListener> onLeftClickListeners = new ArrayList<>();

    public AbstractButtonShow(){
        initialize();
    }

    abstract protected void initialize();

    /**
     * Enregistre un listener
     * @param listener
     */
    public void addOnLeftClickListener(OnLeftClickListener listener){
        onLeftClickListeners.add(listener);
    }

    /**
     * Notifie les listeners
     * @param evt
     */
    protected void notifyOnLeftClickListener(OnLeftClickEvent evt){
        onLeftClickListeners.forEach(new Consumer<OnLeftClickListener>() {
            @Override
            public void accept(OnLeftClickListener listener) {
                listener.onLeftClick(evt);
            }
        });
    }

    public HBox getHbox() {
        return hbox;
    }

    public Button getButton() {
        return button;
    }
}
