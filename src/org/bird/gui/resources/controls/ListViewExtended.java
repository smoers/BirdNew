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
import javafx.geometry.Bounds;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javafx.util.StringConverter;
import org.bird.db.exceptions.DBException;
import org.bird.gui.common.ParentPaneOverrideControl;
import org.bird.gui.common.mapper.DefaultMapper;
import org.bird.gui.events.OnLeftClickEvent;
import org.bird.gui.listeners.OnLeftClickListener;

public abstract class ListViewExtended<T> extends DefaultMapper<T> {

    private ListView<T> listView;
    private TextFieldMultiSelection txtSelection;
    private ButtonFilter btFilter = new ButtonFilter();
    private ButtonAdd btAdd = new ButtonAdd();
    private HBox hBox = new HBox();
    private ContextMenu contextMenu;
    private MenuItem menuItem = new MenuItem();

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
         * Config listview
         */
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listView.setPrefHeight(100);
        /**
         * Button Filter
         */
        btFilter.addOnLeftClickListener(new OnLeftClickListener() {
            @Override
            public void onLeftClick(OnLeftClickEvent evt) {
                showContextField();
            }
        });
        /**
         * Text multiselection
         */
        txtSelection = new TextFieldMultiSelection(getStringConverter());
        txtSelection.setEditable(false);
        txtSelection.setPrefHeight(29.0);
        txtSelection.update(listView.getSelectionModel());
        txtSelection.setOnContextMenuRequested(contextMenuEvent -> {
            showContextField();
        });
        txtSelection.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if (t1){
                showContextField();
            }
        });
        /**
         * Modifie l'apparence
         */
        ParentPaneOverrideControl parentPane = new ParentPaneOverrideControl(listView);
        hBox = parentPane.<HBox>getPane(hBox);
        hBox.getChildren().setAll(txtSelection, btFilter, btAdd);
        /**
         * Context Menu
         */
        contextMenu = new ContextMenu();
        contextMenu.setHideOnEscape(true);
        menuItem.setGraphic(listView);
        contextMenu.getItems().add(menuItem);

    }


    /**
     * Permet de forcer l'affichage du ContextMenu
     */
    public void showContextField(){
        Bounds bounds = txtSelection.localToScreen(txtSelection.getBoundsInLocal());
        contextMenu.show(txtSelection,bounds.getMinX()+txtSelection.getWidth(),bounds.getMinY() - txtSelection.getHeight());
        listView.requestFocus();
    }

    /**
     * Retourne
     * @return
     */
    public TextFieldMultiSelection getTextFieldMultiSelection() {
        return txtSelection;
    }

    protected abstract StringConverter<T> getStringConverter();

}
