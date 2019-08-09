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

import javafx.collections.transformation.FilteredList;
import javafx.geometry.Bounds;
import javafx.scene.control.*;

import java.util.function.Predicate;

/**
 * Cette classe permet de créer un champ filter sur un objet ComboBox
 * @param <T>
 */
public abstract class ComboBoxFiltered<T> {

    protected ComboBox<T> comboBox;
    protected ContextMenu contextMenu;
    protected TextField textField = new TextField();
    protected MenuItem menuItem = new MenuItem();
    protected FilteredList<T> filteredList;

    public static enum Selection {
        SINGLE(0),
        MULTIPLE(1);
        public int selection;
        Selection(int selection){ this.selection = selection; }
    }

    public ComboBoxFiltered(ComboBox<T> comboBox) {
        this.comboBox = comboBox;
        setup();
    }

    protected void setup(){

        /**
         * Filter
         */
        filteredList = new FilteredList<>(comboBox.getItems(), p -> true);
        comboBox.setItems(filteredList);
        /**
         * TextField
         */
        textField.setPrefWidth(comboBox.getPrefWidth());
        textField.textProperty().addListener((observableValue, s, t1) -> {
            filteredList.setPredicate(getPredicate(t1));
        });
        /**
         * MenuContext
         */
        contextMenu = new ContextMenu();
        contextMenu.setHideOnEscape(true);
        comboBox.setOnContextMenuRequested(contextMenuEvent -> {
            showContextField();
        });
        menuItem.setGraphic(textField);
        contextMenu.getItems().add(menuItem);
    }

    /**
     * Permet de forcer l'affichage du ContextMenu
     */
    public void showContextField(){
        Bounds bounds = comboBox.localToScreen(comboBox.getBoundsInLocal());
        comboBox.show();
        contextMenu.show(comboBox,bounds.getMinX(),bounds.getMinY() - comboBox.getHeight());
        textField.requestFocus();
    }



    /**
     * Cette méthode doit retourner le Predicate avec les critères d'applications du filtre
     * @param text
     * @return
     */
    public abstract Predicate<T> getPredicate(String text);

}
