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

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.util.StringConverter;
import org.bird.db.exceptions.DBException;
import org.bird.db.mapper.Mapper;
import org.bird.db.mapper.MapperFactory;
import org.bird.db.models.*;
import org.bird.gui.common.predicate.*;
import org.bird.gui.resources.controls.*;
import org.bird.logger.ELoggers;
import org.bird.logger.Loggers;

import java.net.URL;
import java.util.ResourceBundle;

public class AddEditBookController extends DisplayWindowController {

    @FXML
    private ListView<Author> fldAuthors;
    @FXML
    private ListView<Cycle> fldCycle;
    @FXML
    private ListView<Editor> fldEditor;
    @FXML
    private ListView<Collection> fldCollection;
    @FXML
    private ListView<Illustrator> fldIllustrators;
    @FXML
    private Accordion accData;
    @FXML
    private BorderPane borderPaneMain;

    private Class[] pathFXML;
    private Modality modality;
    private String title;
    private MapperFactory mapperFactory = MapperFactory.getInstance();
    private Mapper mapper;
    private Loggers loggers = Loggers.getInstance();

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
        /**
         * Expand le panneau par défaut
         */
        accData.setExpandedPane(accData.getPanes().get(0));
        try {
            /**
             * L'extended listview pour les auteurs
             */
            ListViewExtended<Author> listViewExtendedAuthor = new ListViewExtended<Author>(fldAuthors,new LoaderPredicate<Author,String>(new AuthorPredicate()), Author.class) {
                @Override
                protected StringConverter<Author> getStringConverter() {
                    return new StringConverter<Author>() {
                        @Override
                        public String toString(Author author) {
                            return author != null ? author.getFullName() : null;
                        }

                        @Override
                        public Author fromString(String s) {
                            return null;
                        }
                    };
                }

            };
            /**
             * L'extended listview pour les Cycles
             */
            ListViewExtended<Cycle> listViewExtendedCycle = new ListViewExtended<Cycle>(fldCycle, new LoaderPredicate<Cycle,String>(new CyclePredicator()), Cycle.class) {
                @Override
                protected StringConverter<Cycle> getStringConverter() {
                    return new StringConverter<Cycle>() {
                        @Override
                        public String toString(Cycle cycle) {
                            return cycle != null ? cycle.getTitle() : null;
                        }

                        @Override
                        public Cycle fromString(String s) {
                            return null;
                        }
                    };
                }
            };
            listViewExtendedCycle.setMultipleSelection(false);
            /**
             * L'extended listview pour les Editors
             */
            ListViewExtended<Editor> listViewExtendedEditor = new ListViewExtended<Editor>(fldEditor, new LoaderPredicate<Editor,String>(new EditorPredicate()),Editor.class) {
                @Override
                protected StringConverter<Editor> getStringConverter() {
                    return new StringConverter<Editor>() {
                        @Override
                        public String toString(Editor editor) {
                            return editor != null ? editor.getName() : null;
                        }

                        @Override
                        public Editor fromString(String s) {
                            return null;
                        }
                    };
                }
            };
            listViewExtendedEditor.setMultipleSelection(false);
            /**
             * L'extended listview pour les Collections
             */
            ListViewExtended<Collection> listViewExtendedCollection = new ListViewExtended<Collection>(fldCollection, new LoaderPredicate<Collection,String>(new CollectionPredicate()), Collection.class) {
                @Override
                protected StringConverter<Collection> getStringConverter() {
                    return new StringConverter<Collection>() {
                        @Override
                        public String toString(Collection collection) {
                            return collection != null ? collection.getName() : null;
                        }

                        @Override
                        public Collection fromString(String s) {
                            return null;
                        }
                    };
                }
            };
            listViewExtendedCollection.setMultipleSelection(false);
            /**
             * L'extended listview pour les Illustrators
             */
            ListViewExtended<Illustrator> listViewExtendedIllustrator = new ListViewExtended<Illustrator>(fldIllustrators, new LoaderPredicate<>(new IllustratorPredicate()), Illustrator.class) {
                @Override
                protected StringConverter<Illustrator> getStringConverter() {
                    return new StringConverter<Illustrator>() {
                        @Override
                        public String toString(Illustrator illustrator) {
                            return illustrator != null ? illustrator.getFullName() : null;
                        }

                        @Override
                        public Illustrator fromString(String s) {
                            return null;
                        }
                    };
                }
            };
        } catch (DBException e) {
            loggers.setDefaultLogger(ELoggers.GUI);
            loggers.error(loggers.messageFactory.newMessage(e.getMessage()));
        }

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
