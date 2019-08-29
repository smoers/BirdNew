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

package org.bird.gui.common;

import java.lang.reflect.InvocationTargetException;

public class LoaderPredicate<T,V> {

    private Class<AbstractPredicate<T,V>> clazz;

    public LoaderPredicate(Class<AbstractPredicate<T, V>> clazz) {
        this.clazz = clazz;
    }

    public AbstractPredicate<T,V> getInstance(V value) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        AbstractPredicate<T,V> abstractPredicate = clazz.getDeclaredConstructor().newInstance();
        abstractPredicate.setValue(value);
        return abstractPredicate;
    }
}
