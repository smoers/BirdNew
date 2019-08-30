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
import org.bird.db.models.Editor;
import org.bird.gui.common.predicate.AbstractPredicate;
import org.bird.gui.common.predicate.EditorPredicate;
import org.bird.gui.common.predicate.LoaderPredicate;

import java.util.List;

/**
 * Fourni un ComboBox correctement configuré & avec les données Authors
 */
public class ComboBoxEditor {

    private MapperFactory mapperFactory = MapperFactory.getInstance();
    private Mapper mapper;
    private ComboBox<Editor> comboBox;
    private ComboBoxFilteredWithButton<Editor> comboBoxFiltered;

    /**
     * Constructeur
     * @param comboBox
     * @throws DBException
     */
    public ComboBoxEditor(ComboBox<Editor> comboBox) throws DBException {
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
        List<Editor> editors = mapper.getDatastore().createQuery(Editor.class).asList();
        ObservableList<Editor> observableList = FXCollections.observableArrayList(editors);
        /**
         * Setup du ComboBox
         */
        comboBox.setItems(observableList);
        /**
         * Converter pour l'affichage de l'objet
         */
        comboBox.setConverter(new StringConverter<Editor>() {
            @Override
            public String toString(Editor editor) {
                return editor == null ? null : editor.getName();
            }

            @Override
            public Editor fromString(String s) {
                return null;
            }
        });
        /**
         * Init
         */
        comboBoxFiltered = new ComboBoxFilteredWithButton<Editor>(comboBox,new LoaderPredicate<Editor,String>(new EditorPredicate()));
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
    public ComboBoxFilteredWithButton<Editor> getComboBoxFilteredWithButton(){
        return comboBoxFiltered;
    }

}
