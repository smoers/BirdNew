/*
 * Copyright [2019] Moers Serge
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.bird.gui.resources.controls;

import javafx.scene.Node;
import javafx.scene.control.Button;
import org.bird.gui.events.OnLeftClickEvent;
import org.bird.gui.listeners.OnLeftClickListener;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * Button avec l'image Add
 */
public class ButtonAdd extends Button {

    protected ArrayList<OnLeftClickListener> onLeftClickListeners = new ArrayList<>();

    /**
     * Constructeur
     */
    public ButtonAdd() {
        initialize();
    }

    /**
     * Constructeur
     */
    public ButtonAdd(String s) {
        super(s);
        initialize();
    }

    /**
     * Constructeur
     */
    public ButtonAdd(String s, Node node) {
        super(s, node);
        initialize();
    }

    /**
     * Setup
     */
    private void initialize(){
        setId("buttonAdd");
        getStyleClass().add("mediumWhiteButton");
        setOnMousePressed(mouseEvent -> {
            notifyOnLeftClickListener(new OnLeftClickEvent(this, mouseEvent.getClickCount()));
        });
    }

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
}
