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

import java.lang.reflect.InvocationTargetException;
import java.util.function.Predicate;

public class LoaderPredicate<T,V> {

    private InterfacePredicate<T,V> predicate;

    public LoaderPredicate(InterfacePredicate<T,V> predicate) {
        this.predicate = predicate;
    }

    public Predicate<T> getInstance(V value) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class clazz = predicate.getClass();
        InterfacePredicate<T,V> interfacePredicate = (InterfacePredicate<T,V>) clazz.getDeclaredConstructor().newInstance();
        interfacePredicate.setValue(value);
        return (Predicate<T>) interfacePredicate;
    }
}
