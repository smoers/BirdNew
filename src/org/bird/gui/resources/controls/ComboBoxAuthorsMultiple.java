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
import javafx.scene.control.skin.ComboBoxBaseSkin;
import javafx.util.Callback;
import javafx.util.StringConverter;
import org.bird.db.exceptions.DBException;
import org.bird.db.models.Author;
import org.bird.gui.common.mapper.CollectionAuthor;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Predicate;

public class ComboBoxAuthorsMultiple extends CollectionAuthor {

    private ComboBox<Author> comboBox;
    private ComboBoxFilteredWithButton<Author> comboBoxFilteredWithButton;
    private List<Author> selection = new ArrayList<>();

    /**
     * Contructeur
     *
     * @throws DBException
     */
    public ComboBoxAuthorsMultiple(ComboBox<Author> comboBox) throws DBException {
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
         * Charge le Combo
         */
        comboBox.setItems(authors);
        /**
         * Defini le converteur pour l'affichage
         */
        comboBox.setCellFactory(new Callback<ListView<Author>, ListCell<Author>>() {
            @Override
            public ListCell<Author> call(ListView<Author> authorListView) {
                return new ListCell<>(){
                    @Override
                    protected void updateItem(Author author, boolean b) {
                        super.updateItem(author, b);
                        if(author == null || b){
                            setGraphic(null);
                        } else {
                            setGraphic(new ComboBoxCheckItem<Author>(author) {
                                @Override
                                public String getLabelText() {
                                    return getValue().getFullName();
                                }
                            });
                        }
                    }
                };
            }
        });
        comboBox.setConverter(new StringConverter<Author>() {
            @Override
            public String toString(Author author) {
                StringJoiner joiner = new StringJoiner(",");
                selection.forEach(selected -> {
                    joiner.add(selected.getFullName());
                });
                return joiner.toString();
            }

            @Override
            public Author fromString(String s) {
                return null;
            }
        });
        comboBox.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (selection.contains(newValue)){
                selection.remove(newValue);
            } else {
                selection.add(newValue);
            }

        });
        /**
         * Ajouter au ComboBox avec bouton
         */
        comboBoxFilteredWithButton = new ComboBoxFilteredWithButton<Author>(comboBox) {
            @Override
            public Predicate<Author> getPredicate(String text) {
                return new Predicate<Author>() {
                    @Override
                    public boolean test(Author author) {
                        if (author.getFullName().contains(text)){
                            return true;
                        } else {
                            return false;
                        }
                    }
                };
            }
        };
    }

    public ComboBox<Author> getComboBox() {
        return comboBox;
    }

    public ComboBoxFilteredWithButton<Author> getComboBoxFilteredWithButton() {
        return comboBoxFilteredWithButton;
    }
}
