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
import javafx.scene.control.TextField;

import java.util.function.Predicate;

/**
 * Etend un objet TextField afin de la combiner avec un objet FilteredList
 * @param <T>
 */
public abstract class TextFieldPredicate<T> extends TextField {

    /**
     * La liste à filtrer
     */
    private FilteredList<T> filteredList;

    /**
     * Constructeur
     * @param filteredList
     */
    public TextFieldPredicate(FilteredList<T> filteredList) {
        this.filteredList = filteredList;
        initialyze();
    }

    /**
     * Constructeur
     * @param s
     * @param filteredList
     */
    public TextFieldPredicate(String s, FilteredList<T> filteredList) {
        super(s);
        this.filteredList = filteredList;
        initialyze();
    }

    /**
     * Setup de l'objet
     */
    private void initialyze(){
        textProperty().addListener(((observableValue, s, t1) -> {
            filteredList.setPredicate(getPredicate(t1));
        }));
    }

    public FilteredList<T> getFilteredList() {
        return filteredList;
    }

    /**
     * Cette méthode doit retourner le Predicate avec les critères d'applications du filtre
     * @param text
     * @return
     */
    protected abstract Predicate<T> getPredicate(String text);

}
