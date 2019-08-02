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

package org.bird.db.models;

import org.bird.db.models.metadata.ModelMetaData;
import org.bson.types.ObjectId;
import xyz.morphia.annotations.*;

@Entity("collection")
@Indexes({
        @Index(fields = @Field("name"), options = @IndexOptions(name = "name")),
})
public class Collection extends ModelMetaData {


    @Id
    private ObjectId id = new ObjectId();
    private String name;
    private Boolean active;

    public Collection() { }

    public Collection(String name) {
        this.name = name;
    }

    public Collection(String name, Boolean active) {
        this.name = name;
        this.active = active;
    }

    public ObjectId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
