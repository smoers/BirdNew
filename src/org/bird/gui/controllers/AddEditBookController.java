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

package org.bird.gui.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.util.Callback;
import javafx.util.StringConverter;
import org.bird.db.exceptions.DBException;
import org.bird.db.mapper.Mapper;
import org.bird.db.mapper.MapperFactory;
import org.bird.db.models.Author;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class AddEditBookController extends DisplayWindowController {

    @FXML
    private ComboBox<Author> fldAuthor;

    private Class[] pathFXML;
    private Modality modality;
    private String title;
    private MapperFactory mapperFactory = MapperFactory.getInstance();
    private Mapper mapper;

    public AddEditBookController() throws DBException {
        setting();
    }

    @Override
    protected void setPathFXML(Class ... clazz) {
        pathFXML = clazz;
    }

    @Override
    protected Class[] getPathFXML() {
        return pathFXML;
    }

    @Override
    protected void setModality(Modality modality) {
        this.modality = modality;
    }

    @Override
    protected Modality getModality() {
        return modality;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Author> authors = mapper.getDatastore().createQuery(Author.class).asList();
        ObservableList<Author> observableList = FXCollections.observableArrayList(authors);
        FilteredList<Author> filteredList = new FilteredList<>(observableList, p -> true);
        fldAuthor.setItems(filteredList);
        fldAuthor.selectionModelProperty().addListener((obs,oldValue,newValue)->{
            System.out.println("select");
        });
        fldAuthor.setEditable(true);
        fldAuthor.setConverter(new StringConverter<Author>() {
            @Override
            public String toString(Author author) {
                return author == null ? null : author.getFullName();
            }

            @Override
            public Author fromString(String s) {
                return null;
            }
        });
        /*fldAuthor.getEditor().textProperty().addListener((obs, oldValue, NewValue) -> {
            final TextField editor = fldAuthor.getEditor();
            final Author selected = fldAuthor.getSelectionModel().getSelectedItem();
            Platform.runLater(() ->{
                if (selected == null || (!selected.getLastName().equalsIgnoreCase(editor.getText()) && !selected.getFirstName().equalsIgnoreCase(editor.getText()))){
                    filteredList.setPredicate(item -> {
                        if (item.getLastName().contains(editor.getCharacters()) || item.getFirstName().contains(editor.getCharacters())){
                            return true;
                        } else {
                            return false;
                        }
                    });
                }
            });
        });*/

    }

    public void setting() throws DBException {

        setPathFXML(getClass());
        setModality(Modality.NONE);
        setTitle("Add Book");
        mapper = mapperFactory.<Mapper>getMapper(new Mapper());
    }

    @Override
    public void setLanguage() {

    }

}
