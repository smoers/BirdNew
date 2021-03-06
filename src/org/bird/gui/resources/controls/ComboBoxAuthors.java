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

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;
import org.bird.db.exceptions.DBException;
import org.bird.db.mapper.Mapper;
import org.bird.db.mapper.MapperFactory;
import org.bird.db.models.Author;
import org.bird.gui.common.predicate.AbstractPredicate;
import org.bird.gui.common.predicate.LoaderPredicate;
import org.bird.gui.common.mapper.CollectionAuthor;

/**
 * Fourni un ComboBox correctement configuré & avec les données Authors
 */
public class ComboBoxAuthors extends CollectionAuthor {

    private MapperFactory mapperFactory = MapperFactory.getInstance();
    private Mapper mapper;
    private ComboBox<Author> comboBox;
    private ComboBoxFilteredWithButton<Author> comboBoxFiltered;

    /**
     * Constructeur
     * @param comboBox
     * @throws DBException
     */
    public ComboBoxAuthors(ComboBox<Author> comboBox) throws DBException {
        this.comboBox = comboBox;
        initSingle();
    }

    /**
     * initSingle
     * @throws DBException
     */
    private void initSingle() throws DBException {

        ObservableList<Author> observableList = getObservableList();
        /**
         * Setup du ComboBox
         */
        comboBox.setItems(observableList);
        /**
         * Converter pour l'affichage de l'objet
         */
        comboBox.setConverter(new StringConverter<Author>() {
            @Override
            public String toString(Author author) {
                return author == null ? null : author.getFullName();
            }

            @Override
            public Author fromString(String s) {
                return null;
            }
        });
        /**
         * Init
         */
        AbstractPredicate<Author,String> abstractPredicate = new AbstractPredicate<Author, String>() {
            @Override
            public boolean test(Author author) {
                if (author.getFullName().contains(getValue()))
                    return true;
                else
                    return false;
            }
        };
        comboBoxFiltered = new ComboBoxFilteredWithButton<Author>(comboBox,new LoaderPredicate<Author,String>(abstractPredicate));
    }

    /**
     * Retourne le ComboBox
     * @return
     */
    public ComboBox getComboBox(){
        return comboBox;
    }

    /**.
     * Retourne l'instance ComboBoxFilteredWithButton
     * @return
     */
    public ComboBoxFilteredWithButton<Author> getComboBoxFilteredWithButton(){
        return comboBoxFiltered;
    }

}
