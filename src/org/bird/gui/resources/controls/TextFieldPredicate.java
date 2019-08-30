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
import org.apache.logging.log4j.Logger;
import org.bird.gui.common.predicate.LoaderPredicate;
import org.bird.logger.ELoggers;
import org.bird.logger.Loggers;

import java.lang.reflect.InvocationTargetException;

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
    private LoaderPredicate<T,String> loaderPredicate = null;
    /**
     * Logger
     */
    private Loggers loggers = Loggers.getInstance();

    /**
     * Constructeur
     */
    public TextFieldPredicate(LoaderPredicate<T,String> loaderPredicate) {
        this.loaderPredicate = loaderPredicate;
        initialyze();
    }

    /**
     * Constructeur
     * @param s
     */
    public TextFieldPredicate(String s,LoaderPredicate<T,String> loaderPredicate) {
        super(s);
        this.loaderPredicate = loaderPredicate;
        initialyze();
    }

    /**
     * Constructeur
     * @param filteredList
     */
    public TextFieldPredicate(FilteredList<T> filteredList,LoaderPredicate<T,String> loaderPredicate) {
        this.filteredList = filteredList;
        this.loaderPredicate = loaderPredicate;
        initialyze();
    }

    /**
     * Constructeur
     * @param s
     * @param filteredList
     */
    public TextFieldPredicate(String s, FilteredList<T> filteredList,LoaderPredicate<T,String> loaderPredicate) {
        super(s);
        this.loaderPredicate = loaderPredicate;
        this.filteredList = filteredList;
        initialyze();
    }

    /**
     * Setup de l'objet
     */
    private void initialyze(){
        Logger logger = loggers.getLogger(ELoggers.GUI);
        textProperty().addListener(((observableValue, s, t1) -> {
            if (filteredList != null) {
                try {
                    filteredList.setPredicate(loaderPredicate.getInstance(t1));
                } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
                    logger.error(loggers.messageFactory.newMessage(e.getMessage(), this));
                }
            }
        }));
    }

    public void setFilteredList(FilteredList<T> filteredList) {
        this.filteredList = filteredList;
    }

    public FilteredList<T> getFilteredList() {
        return filteredList;
    }

}
