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
import javafx.scene.control.Control;
import javafx.scene.layout.HBox;

/**
 * Cette classe permet de disposer d'un objet HBox préconfiguré
 */
public class DefaultHBox extends HBox {

    /**
     * Constructeur
     */
    public DefaultHBox() {
        initialyze();
    }

    /**
     * Constructeur
     * @param v
     */
    public DefaultHBox(double v) {
        super(v);
        initialyze();
    }

    /**
     * Constructeur
     * @param nodes
     */
    public DefaultHBox(Node... nodes) {
        super(nodes);
        initialyze();
    }

    /**
     * Constructeur
     * @param v
     * @param nodes
     */
    public DefaultHBox(double v, Node... nodes) {
        super(v, nodes);
        initialyze();
    }

    /**
     * Préconfiguration
     */
    private void initialyze(){
        setMaxSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
        setMinSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
        setPrefSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
    }
}
