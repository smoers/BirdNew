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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class ParentPaneOverrideControl extends ParentPane {

    private Node node;
    /**
     * Constructeur
     *
     * @param overrideNode
     */
    public ParentPaneOverrideControl(Node overrideNode) {
        super(overrideNode);
        this.node = overrideNode;
    }

    public <T> T getPane(Node pane){
        Pane parent =  getPane();
        if (parent instanceof GridPane){
            /**
             * Si le panneau est de type GridPane
             * on doit conserver la même position dans le tableau
             */
            GridPane gridPane = (GridPane) parent;
            Integer row = GridPane.getRowIndex(node);
            Integer column = GridPane.getColumnIndex(node);
            gridPane.getChildren().remove(node);
            GridPane.setConstraints(pane,column,row);
            GridPane.setMargin(pane,GridPane.getMargin(node));
            gridPane.getChildren().add(pane);
        } else {
            parent.getChildren().remove(node);
            parent.getChildren().add(pane);
        }

        return (T) pane;
    }
}
