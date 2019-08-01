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

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.bird.gui.common.ParentPane;
import org.bird.gui.events.OnLeftClickEvent;
import org.bird.gui.listeners.OnLeftClickListener;

import java.util.ArrayList;

/**
 * Cette classe étend la classe ComboBoxFiltered en ajouutant un bouton Filter et un bouton Add
 * @param <T>
 */
public abstract class ComboBoxFilteredWithButton<T> extends ComboBoxFiltered<T> {

    protected HBox hBox = new HBox();
    protected Button btFilter = new Button();
    protected Button btAdd = new Button();
    protected ArrayList<OnLeftClickListener> filterListeners = new ArrayList<>();
    protected ArrayList<OnLeftClickListener> addListeners = new ArrayList<>();

    /**
     * Constructeur
     * @param comboBox
     */
    public ComboBoxFilteredWithButton(ComboBox<T> comboBox) {
        super(comboBox);
        build();
    }

    /**
     * Construit le rendu
     */
    protected void build(){
        /**
         * Parent container
         */
        ParentPane parentPane = new ParentPane(comboBox);
        Pane pane =  parentPane.getPane();
        if (pane instanceof GridPane){
            /**
             * Si le panneau est de type GridPane
             * on doit conserver la même position dans le tableau
             */
            GridPane gridPane = (GridPane) pane;
            Integer row = GridPane.getRowIndex(comboBox);
            Integer column = GridPane.getColumnIndex(comboBox);
            gridPane.getChildren().remove(comboBox);
            GridPane.setConstraints(hBox,row,column);
            GridPane.setMargin(hBox,GridPane.getMargin(comboBox));
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
        btFilter.setOnMousePressed(mouseEvent -> {
            notifyFilterButtonListener(new OnLeftClickEvent(btFilter, mouseEvent.getClickCount()));
        });
        btAdd.setId("buttonAdd");
        btAdd.getStyleClass().add("mediumWhiteButton");
        btAdd.setOnMousePressed(mouseEvent -> {
            notifyAddButtonListner(new OnLeftClickEvent(btAdd, mouseEvent.getClickCount()));
        });
        /**
         * Config HBox
         */
        hBox.getChildren().setAll(comboBox,btFilter,btAdd);

    }

    public void addFilterButtonListener(OnLeftClickListener listener){
        filterListeners.add(listener);
    }

    public void addAddButtonListener(OnLeftClickListener listener){
        addListeners.add(listener);
    }

    protected void notifyFilterButtonListener(OnLeftClickEvent evt){
        filterListeners.forEach(listener -> {
            listener.onLeftClick(evt);
        });
    }

    protected void notifyAddButtonListner(OnLeftClickEvent evt){
        addListeners.forEach(listener -> {
            listener.onLeftClick(evt);
        });
    }
}
