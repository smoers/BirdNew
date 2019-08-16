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
import javafx.util.StringConverter;
import org.bird.db.exceptions.DBException;
import org.bird.db.mapper.Mapper;
import org.bird.db.mapper.MapperFactory;
import org.bird.db.models.Collection;

import java.util.List;
import java.util.function.Predicate;

/**
 * Fourni un ComboBox correctement configuré & avec les données Collection
 */
public class ComboBoxCollection {

    private MapperFactory mapperFactory = MapperFactory.getInstance();
    private Mapper mapper;
    private ComboBox<Collection> comboBox;
    private ComboBoxFilteredWithButton<Collection> comboBoxFiltered;

    /**
     * Constructeur
     * @param comboBox
     * @throws DBException
     */
    public ComboBoxCollection(ComboBox<Collection> comboBox) throws DBException {
        this.comboBox = comboBox;
        init();
    }

    /**
     * Init
     * @throws DBException
     */
    private void init() throws DBException {
        /**
         * Récupération des données
         */
        mapper = mapperFactory.<Mapper>getMapper(new Mapper());
        List<Collection> collections = mapper.getDatastore().createQuery(Collection.class).asList();
        ObservableList<Collection> observableList = FXCollections.observableArrayList(collections);
        /**
         * Setup du ComboBox
         */
        comboBox.setItems(observableList);
        /**
         * Converter pour l'affichage de l'objet
         */
        comboBox.setConverter(new StringConverter<Collection>() {
            @Override
            public String toString(Collection collection) {
                return collection == null ? null : collection.getName();
            }

            @Override
            public Collection fromString(String s) {
                return null;
            }
        });
        /**
         * Init
         */
        comboBoxFiltered = new ComboBoxFilteredWithButton<Collection>(comboBox) {
            @Override
            public Predicate<Collection> getPredicate(String text) {
                return new Predicate<Collection>() {
                    @Override
                    public boolean test(Collection collection) {
                        if (collection.getName().contains(text)){
                            return true;
                        } else {
                            return false;
                        }
                    }
                };
            }
        };


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
    public ComboBoxFilteredWithButton<Collection> getComboBoxFilteredWithButton(){
        return comboBoxFiltered;
    }

}
