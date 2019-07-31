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

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.bird.gui.common.ParentPane;

public abstract class ComboBoxFilteredWithButton<T> extends ComboBoxFiltered<T> {

    protected HBox hBox = new HBox();
    protected Button btFilter = new Button();
    protected Button btAdd = new Button();

    public ComboBoxFilteredWithButton(ComboBox<T> comboBox) {
        super(comboBox);
        build();
    }

    protected void build(){
        /**
         * Parent container
         */
        ParentPane parentPane = new ParentPane(comboBox);
        Pane pane =  parentPane.getPane();
        if (pane instanceof GridPane){
            GridPane gridPane = (GridPane) pane;
            Integer row = GridPane.getRowIndex(comboBox);
            Integer column = GridPane.getColumnIndex(comboBox);
            gridPane.getChildren().remove(comboBox);
            GridPane.setConstraints(hBox,row,column);
            GridPane.setMargin(hBox,comboBox.getInsets());
            gridPane.getChildren().add(hBox);
        } else {
            pane.getChildren().remove(comboBox);
            pane.getChildren().add(hBox);
        }
        /**
         * Config button
         */
        btFilter.setId("buttonFilter");
        btFilter.getStyleClass().add("mediumWhiteButton");
        btAdd.setId("buttonAdd");
        btAdd.getStyleClass().add("mediumWhiteButton");
        /**
         * Config HBox
         */
        hBox.getChildren().setAll(comboBox,btFilter,btAdd);

    }
}
