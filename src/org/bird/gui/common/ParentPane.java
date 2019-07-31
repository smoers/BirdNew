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

package org.bird.gui.common;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * Permet d'obtenir le Pane parent
 */
public class ParentPane {

    private Pane pane;

    /**
     * Constructeur
     * @param node
     */
    public ParentPane(Node node) {
        pane = getParentPane(node);
    }

    /**
     * Retourne le Pane parent
     * @return
     */
    public Pane getPane() {
        return pane;
    }

    /**
     * Cherche le pane parent
     * @param node
     * @return
     */
    private Pane getParentPane(Node node){
        Node result = node;
        if (node.getParent() instanceof Pane)
            result = node.getParent();
        else
            result = getParentPane(node.getParent());

        return (Pane) result;
    }


}
