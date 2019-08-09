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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 *
 * @param <T>
 */
public abstract class ComboBoxCheckItem<T> extends HBox {

    protected T value;
    protected Label label;
    protected CheckBox checkBox;

    /**
     * Contructeur
     * @param value
     */
    public ComboBoxCheckItem(T value) {
        this.value = value;
        init();
    }

    /**
     * Initialise l'objet
     */
    private void init(){
        /**
         * Instance des nodes
         */
        checkBox = new CheckBox();
        label = new Label(getLabelText());
        /**
         * Init Checkbox
         */
        checkBox.setSelected(false);
        /**
         * ajoute les nodes au HBox
         */
        HBox.setMargin(checkBox,new Insets(5));
        HBox.setMargin(label,new Insets(5));
        this.getChildren().setAll(checkBox,label);

    }

    /**
     * Retourne la valeur de l'item
     * @return
     */
    public T getValue(){ return value; }

    /**
     * Label
     * @return
     */
    public Label getLabel() {
        return label;
    }

    /**
     * CheckBox
     * @return
     */
    public CheckBox getCheckBox() {
        return checkBox;
    }

    /**
     * Retourne le texte à charger dans le label
     * @return
     */
    public abstract String getLabelText();

}
