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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import org.bird.gui.common.RootPane;

import java.util.function.Predicate;

public abstract class ComboBoxFiltered<T> extends RootPane {

    private ComboBox<T> comboBox;
    private ContextMenu contextMenu;
    private TextField textField = new TextField();
    private MenuItem menuItem = new MenuItem();
    private FilteredList<T> filteredList;

    public ComboBoxFiltered(ComboBox<T> comboBox) {
        this.comboBox = comboBox;
        setup();
    }

    private void setup(){
        /**
         * Filter
         */
        filteredList = new FilteredList<>(comboBox.getItems(), p -> true);
        comboBox.setItems(filteredList);
        /**
         * TextField
         */
        textField.setPrefWidth(200.0);
        textField.textProperty().addListener((observableValue, s, t1) -> {
            filteredList.setPredicate(getPredicate(t1));
        });
        /**
         * MenuContext
         */
        contextMenu = new ContextMenu();
        contextMenu.setHideOnEscape(true);
        comboBox.setOnContextMenuRequested(contextMenuEvent -> {
            comboBox.show();
            contextMenu.show(comboBox,contextMenuEvent.getScreenX(),contextMenuEvent.getScreenY() - comboBox.getHeight());
            textField.requestFocus();
        });
        menuItem.setGraphic(textField);
        contextMenu.getItems().add(menuItem);
    }

    /**
     * Cette méthode doit retourner le Predicate avec les critères d'applications du filtre
     * @param text
     * @return
     */
    public abstract Predicate<T> getPredicate(String text);
}
