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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import javafx.util.StringConverter;
import org.bird.db.exceptions.DBException;
import org.bird.db.models.Author;
import org.bird.gui.common.mapper.CollectionAuthor;

import java.util.function.Predicate;

public class ComboBoxAuthorsMultiple extends CollectionAuthor {

    private ComboBox<ComboBoxCheckItem<Author>> comboBox;
    private ComboBoxFilteredWithButton<ComboBoxCheckItem<Author>> comboBoxFilteredWithButton;

    /**
     * Contructeur
     *
     * @throws DBException
     */
    public ComboBoxAuthorsMultiple(ComboBox<ComboBoxCheckItem<Author>> comboBox) throws DBException {
        super();
        this.comboBox = comboBox;
        initMultiple();
    }

    private void initMultiple(){
        /**
         * Collection des Auteurs
          */
        ObservableList<Author> authors = getObservableList();
        /**
         * Convertion vers ComboBoxCheckItem
         */
        ObservableList<ComboBoxCheckItem<Author>> comboBoxCheckItems = FXCollections.observableArrayList();
        authors.forEach(author -> {
            comboBoxCheckItems.add(new ComboBoxCheckItem<Author>(author) {
                @Override
                public String getLabelText() {
                    return getValue().getFullName();
                }
            });
        });
        /**
         * Charge le Combo
         */
        comboBox.setItems(comboBoxCheckItems);
        /**
         * Defini le converteur pour l'affichage
         */
        comboBox.setConverter(new StringConverter<ComboBoxCheckItem<Author>>() {
            @Override
            public String toString(ComboBoxCheckItem<Author> authorComboBoxCheckItem) {
                return authorComboBoxCheckItem.getValue().getFullName();
            }

            @Override
            public ComboBoxCheckItem<Author> fromString(String s) {
                return null;
            }
        });
        /**
         * Ajouter au ComboBox avec bouton
         */
        comboBoxFilteredWithButton = new ComboBoxFilteredWithButton<ComboBoxCheckItem<Author>>(comboBox) {
            @Override
            public Predicate<ComboBoxCheckItem<Author>> getPredicate(String text) {
                return new Predicate<ComboBoxCheckItem<Author>>() {
                    @Override
                    public boolean test(ComboBoxCheckItem<Author> authorComboBoxCheckItem) {
                        if (authorComboBoxCheckItem.getValue().getFullName().contains(text)){
                            return true;
                        } else {
                            return false;
                        }
                    }
                };
            }
        };
    }

    public ComboBox<ComboBoxCheckItem<Author>> getComboBox() {
        return comboBox;
    }

    public ComboBoxFilteredWithButton<ComboBoxCheckItem<Author>> getComboBoxFilteredWithButton() {
        return comboBoxFilteredWithButton;
    }
}
