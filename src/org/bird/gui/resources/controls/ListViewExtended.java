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
import javafx.geometry.Bounds;
import javafx.scene.control.*;
import javafx.util.Callback;
import javafx.util.StringConverter;
import org.bird.db.exceptions.DBException;
import org.bird.gui.common.predicate.LoaderPredicate;
import org.bird.gui.common.ParentPaneOverrideControl;
import org.bird.gui.common.mapper.DefaultMapper;
import org.bird.gui.events.OnLeftClickEvent;
import org.bird.gui.listeners.OnLeftClickListener;
import org.bird.gui.resources.controls.popup.PopupDefault;
import org.bird.gui.resources.controls.popup.PopupSkinFiltered;

/**
 * Cette classe permet l'affichage d'un ListView avec un champ
 * permettant d'appliquer un filtre sur les données listées dans le ListView
 * @param <T>
 */
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
    private Bounds bounds;
    private PopupDefault popup = new PopupDefault();
    private PopupSkinFiltered<T> popupSkinFiltered;
    private LoaderPredicate<T,String> loaderPredicate;
    private FilteredList<T> filteredList;

    /**
     * Constructeur
     * @param listView
     * @param clazz
     * @throws DBException
     */
    public ListViewExtended(ListView<T> listView, LoaderPredicate<T, String> loaderPredicate, Class<T> clazz) throws DBException {
        super(clazz);
        this.listView = listView;
        this.loaderPredicate = loaderPredicate;
        this.filteredList = new FilteredList<>(getObservableList());
        init();
    }

    /**
     * Initialise l'objet
     */
    private void init(){
        txtSelection = new TextFieldMultiSelection(getStringConverter());

        /**
         * Recupère les données & Charge les données dans la liste
         */
        listView.setItems(filteredList);
        /**
         * Défini le convertor
         */
        listView.setCellFactory(new CallBackItem(txtSelection));
        /**
         * Config listview
         */
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listView.setPrefHeight(listViewHeight);
        listView.setOnMousePressed(mouseEvent -> {
            if (mouseEvent.isPrimaryButtonDown() && mouseEvent.getClickCount() == 1) {
                MultipleSelectionModel model = txtSelection.update(listView.getSelectionModel());
                popupSkinFiltered.getTextFieldPredicate().clear();
                model.clearSelection();
            }
        });
        /**
         * Button Filter
         */
        btFilter.addOnLeftClickListener(new OnLeftClickListener() {
            @Override
            public void onLeftClick(OnLeftClickEvent evt) {showPopup();}
        });
        /**
         * Text multiselection
         */

        txtSelection.setEditable(false);
        txtSelection.setPrefHeight(txtSelectionHeight);
        txtSelection.setPrefWidth(txtSelectionWidth);
        txtSelection.update(listView.getSelectionModel());
        txtSelection.setOnMousePressed(mouseEvent -> {
            if (mouseEvent.isPrimaryButtonDown() && mouseEvent.getClickCount() == 1){
                if (popup.isShowing())
                    popup.hide();
                else
                    showPopup();
            }
        });
        /**
         * Modifie l'apparence
         */
        ParentPaneOverrideControl parentPane = new ParentPaneOverrideControl(listView);
        hBox = parentPane.<DefaultHBox>getPane(hBox);
        hBox.getChildren().setAll(txtSelection, btFilter, btAdd);
        /**
         * Popup
         */
        definePopup();
    }

    /**
     * Retourne
     * @return
     */
    public TextFieldMultiSelection getTextFieldMultiSelection() {
        return txtSelection;
    }

    /**
     * Permet de représenter l'objet sous forme d'une chaime de caractère
     * @return
     */
    protected abstract StringConverter<T> getStringConverter();

    /**
     * Affiche le ListView et le champ filtre
     */
    public void showPopup(){
        bounds = txtSelection.localToScreen(txtSelection.getBoundsInLocal());
        popup.show(txtSelection,bounds.getMinX()+txtSelection.getWidth(),bounds.getMinY());
    }

    /**
     * Multiple ou simple sélection
     * @param value
     */
    public void setMultipleSelection(boolean value){
        if (value)
            listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        else
            listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    private void definePopup(){
        popupSkinFiltered = new PopupSkinFiltered<T>(popup, loaderPredicate);
        popupSkinFiltered.getTextFieldPredicate().setFilteredList(filteredList);
        popupSkinFiltered.addNode(listView);
        popup.setSkin(popupSkinFiltered);

    }

    /**
     * Cette classe est utilisée pour l'affichage des items
     */
    private class CallBackItem implements Callback<ListView<T>, ListCell<T>>{

        private TextFieldMultiSelection<T> textFieldMultiSelection;
        private static final String HIGHLIGHTED_CONTROL_INNER_BACKGROUND = "derive(palegreen, 50%)";

        public CallBackItem(TextFieldMultiSelection<T> textFieldMultiSelection) {
            this.textFieldMultiSelection = textFieldMultiSelection;
        }

        @Override
        public ListCell<T> call(ListView<T> tListView) {
            return new ListCell<>(){
                @Override
                protected void updateItem(T item, boolean b) {
                    super.updateItem(item, b);
                    setText(getStringConverter().toString(item));
                    System.out.println(textFieldMultiSelection.selectedObject.size());
                    if (textFieldMultiSelection.selectedObject.contains(item)){
                        setStyle("-fx-control-inner-background: " + HIGHLIGHTED_CONTROL_INNER_BACKGROUND + ";");
                    } else {
                        setStyle("");
                    }
                }
            };
        }
    }

}
