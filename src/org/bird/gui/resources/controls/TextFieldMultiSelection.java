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

import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.util.StringJoiner;
import java.util.function.Consumer;

/**
 * Permet d'afficher un champ text sur base d'un control multiSelection
 * @param <T>
 */
public class TextFieldMultiSelection<T> extends TextField {

    /**
     * L'objet pour la convertion
     */
    protected StringConverter<T> converter;

    /**
     * Constructeur
     * @param converter
     */
    public TextFieldMultiSelection(StringConverter<T> converter) {
        this.converter = converter;
        initialize();
    }

    /**
     * Constructeur
     * @param s
     * @param converter
     */
    public TextFieldMultiSelection(String s, StringConverter<T> converter) {
        super(s);
        this.converter = converter;
        initialize();
    }

    /**
     * Met à jour le text de l'objet
     * @param model
     */
    public void update(MultipleSelectionModel<T> model){
        StringJoiner joiner = new StringJoiner(",");
        model.getSelectedItems().forEach(new Consumer<T>() {
            @Override
            public void accept(T t) {
                joiner.add(converter.toString(t));
            }
        });
    }

    private void initialize(){
        getStyleClass().add("form_label_field");
    }

}
