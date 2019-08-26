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
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Bounds;
import javafx.scene.control.*;
import javafx.util.Callback;
import javafx.util.StringConverter;
import org.bird.db.exceptions.DBException;
import org.bird.gui.common.ParentPaneOverrideControl;
import org.bird.gui.common.mapper.DefaultMapper;
import org.bird.gui.events.OnLeftClickEvent;
import org.bird.gui.listeners.OnLeftClickListener;

import java.util.function.Predicate;

public abstract class ListViewExtended<T> extends DefaultMapper<T> {

    /**
     * Détermine la hauteur de la zone texte
     */
    public double txtSelectionHeight = 29.0;
    /**
     * Détermine la longueur de la zone texte
     */
    public double txtSelectionWidth = 200;
    /**
     * Détermine la hauteur de la zone texte
     */
    public double listViewHeight = 100;

    private ListView<T> listView;
    private TextFieldMultiSelection txtSelection;
    private ButtonFilter btFilter = new ButtonFilter();
    private ButtonAdd btAdd = new ButtonAdd();
    private DefaultHBox hBox = new DefaultHBox();
    private ContextMenu contextMenu;
    private MenuItem menuItemList = new MenuItem();
    private MenuItem menuItemFilter = new MenuItem();
    private TextFieldPredicate<T> txtFilter;

    /**
     * Constructeur
     * @param listView
     * @param clazz
     * @throws DBException
     */
    public ListViewExtended(ListView<T> listView,TextFieldPredicate<T> textFieldPredicate, Class<T> clazz) throws DBException {
        super(clazz);
        this.listView = listView;
        this.txtFilter = textFieldPredicate;
        init();
    }

    /**
     * Initialise l'objet
     */
    private void init(){
        /**
         * Panneau filtre list
         */
        ButtonAdd btAddFilter = new ButtonAdd();
        ButtonErase btEraseFilter = new ButtonErase();
        ButtonDelete btDeleteFilter = new ButtonDelete();
        DefaultHBox hBoxFilter = new DefaultHBox(txtFilter,btEraseFilter, btAddFilter,btDeleteFilter);
        /**
         * Recupère les données & instance de l'objet TextFieldPredicate
         */
        txtFilter.setFilteredList(new FilteredList<T>(getObservableList()));
        /**
         * Charge les données dans la liste
         */
        listView.setItems(txtFilter.getFilteredList());
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
        listView.setPrefHeight(listViewHeight);
        listView.setOnMousePressed(mouseEvent -> {
            if (mouseEvent.isPrimaryButtonDown() && mouseEvent.getClickCount() == 1)
                txtSelection.update(listView.getSelectionModel());
        });
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
        txtSelection.setPrefHeight(txtSelectionHeight);
        txtSelection.setPrefWidth(txtSelectionWidth);
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
        hBox = parentPane.<DefaultHBox>getPane(hBox);
        hBox.getChildren().setAll(txtSelection, btFilter, btAdd);
        /**
         * Context Menu
         */
        contextMenu = new ContextMenu();
        contextMenu.setHideOnEscape(true);
        menuItemFilter.setGraphic(hBoxFilter);
        menuItemList.setGraphic(listView);
        contextMenu.getItems().addAll(menuItemFilter, menuItemList);

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
