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
import javafx.util.Callback;
import org.bird.db.exceptions.DBException;
import org.bird.db.models.Author;
import org.bird.gui.common.mapper.CollectionAuthor;

public class ListViewAuthors extends CollectionAuthor {

    private ListView<Author> listView;

    public ListViewAuthors(ListView<Author> listView) throws DBException {
        super();
        this.listView = listView;
        init();
    }

    private void init(){
        /**
         * Recupère les données Author
         */
        ObservableList<Author> observableList = getObservableList();
        /**
         * Charge les données dans la liste
         */
        listView.setItems(observableList);
        listView.setCellFactory(new Callback<ListView<Author>, ListCell<Author>>() {
            @Override
            public ListCell<Author> call(ListView<Author> authorListView) {
                return new ListCell<>(){
                    @Override
                    protected void updateItem(Author author, boolean b) {
                        super.updateItem(author, b);

                    }
                };
            }
        });
    }
}
