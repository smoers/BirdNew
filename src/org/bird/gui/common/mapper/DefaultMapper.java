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

package org.bird.gui.common.mapper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.bird.db.exceptions.DBException;

import java.util.List;

public class DefaultMapper<T> extends org.bird.db.mapper.DefaultMapper<T> {

    public DefaultMapper(Class<T> clazz) throws DBException {
        super(clazz);
    }

    /**
     * Retourne une liste des objets
     * @return
     */
    public List<T> getList(){
        return getDatastore().createQuery(clazz).asList();
    }

    /**
     * Retourne une observable liste des objets
     * @return
     */
    public ObservableList<T> getObservableList(){
        return FXCollections.observableArrayList(getList());
    }
}
