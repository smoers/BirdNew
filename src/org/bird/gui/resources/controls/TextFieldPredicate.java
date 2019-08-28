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
import org.bird.gui.common.AbstractPredicate;

import java.util.function.Predicate;

/**
 * Etend un objet TextField afin de le combiner avec un objet FilteredList
 * @param <T>
 */
public class TextFieldPredicate<T> extends TextField {

    /**
     * La liste à filtrer
     */
    private FilteredList<T> filteredList = null;
    /**
     * Predicate
     */
    private AbstractPredicate<T,String> abstractPredicate = null;

    /**
     * Constructeur
     */
    public TextFieldPredicate(AbstractPredicate<T,String> abstractPredicate) {
        this.abstractPredicate = abstractPredicate;
        initialyze();
    }

    /**
     * Constructeur
     * @param s
     */
    public TextFieldPredicate(String s,AbstractPredicate<T,String> abstractPredicate) {
        super(s);
        this.abstractPredicate = abstractPredicate;
        initialyze();
    }

    /**
     * Constructeur
     * @param filteredList
     */
    public TextFieldPredicate(FilteredList<T> filteredList,AbstractPredicate<T,String> abstractPredicate) {
        this.filteredList = filteredList;
        this.abstractPredicate = abstractPredicate;
        initialyze();
    }

    /**
     * Constructeur
     * @param s
     * @param filteredList
     */
    public TextFieldPredicate(String s, FilteredList<T> filteredList,AbstractPredicate<T,String> abstractPredicate) {
        super(s);
        this.abstractPredicate = abstractPredicate;
        this.filteredList = filteredList;
        initialyze();
    }

    /**
     * Setup de l'objet
     */
    private void initialyze(){
        textProperty().addListener(((observableValue, s, t1) -> {
            if (filteredList != null)
                filteredList.setPredicate(getPredicate(t1));
        }));
    }

    public void setFilteredList(FilteredList<T> filteredList) {
        this.filteredList = filteredList;
    }

    public FilteredList<T> getFilteredList() {
        return filteredList;
    }

    /**
     * Cette méthode doit retourner le Predicate avec les critères d'applications du filtre
     * @param text
     * @return
     */
    protected Predicate<T> getPredicate(String text){
        abstractPredicate.setValue(text);
        return abstractPredicate;
    };

}
