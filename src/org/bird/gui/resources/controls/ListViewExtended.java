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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javafx.util.StringConverter;
import org.bird.db.exceptions.DBException;
import org.bird.gui.common.ParentPaneOverrideControl;
import org.bird.gui.common.mapper.DefaultMapper;

public abstract class ListViewExtended<T> extends DefaultMapper<T> {

    private ListView<T> listView;
    private TextFieldMultiSelection txtSelection;
    private ButtonAdd btAdd = new ButtonAdd();
    private HBox hBox = new HBox();

    public ListViewExtended(ListView<T> listView, Class<T> clazz) throws DBException {
        super(clazz);
        this.listView = listView;
        init();
    }

    private void init(){
        /**
         * Recupère les données
         */
        ObservableList<T> observableList = getObservableList();
        /**
         * Charge les données dans la liste
         */
        listView.setItems(observableList);
        /**
         * Défini le convertor
         */
        listView.setCellFactory(new Callback<ListView<T>, ListCell<T>>() {
            @Override
            public ListCell<T> call(ListView<T> authorListView) {
                return new ListCell<>(){
                    @Override
                    protected void updateItem(T item, boolean b) {
                        super.updateItem(item, b);
                        setText(getStringConverter().toString(item));
                    }
                };
            }
        });
        /**
         * Text multiselection
         */
        txtSelection = new TextFieldMultiSelection(getStringConverter());
        txtSelection.setEditable(false);
        txtSelection.update(listView.getSelectionModel());

        /**
         * Modifie l'apparence
         */
                ParentPaneOverrideControl parentPane = new ParentPaneOverrideControl(listView);
        hBox = parentPane.<HBox>getPane(hBox);
        hBox.getChildren().setAll(txtSelection, btAdd);

    }

    protected abstract StringConverter<T> getStringConverter();

}
