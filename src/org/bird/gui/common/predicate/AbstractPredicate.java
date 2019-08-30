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

package org.bird.gui.common.predicate;

import org.bird.gui.common.predicate.InterfacePredicate;

import java.util.function.Predicate;

public abstract class AbstractPredicate<T,V> implements Predicate<T>, InterfacePredicate<T,V> {

    protected V value = null;

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
