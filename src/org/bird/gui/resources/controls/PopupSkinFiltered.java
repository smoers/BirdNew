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

import javafx.scene.Node;
import javafx.scene.control.PopupControl;
import javafx.scene.control.Skin;
import org.bird.gui.common.AbstractPredicate;

public class PopupSkinFiltered<T> implements Skin<PopupControl> {

    private PopupControl popupControl;
    private TextFieldPredicate<T> textFieldPredicate;
    private AbstractPredicate<T,String> abstractPredicate;
    private DefaultVBox container;

    public PopupSkinFiltered(PopupControl popupControl, AbstractPredicate<T,String> abstractPredicate) {
        this.popupControl = popupControl;
        this.abstractPredicate = abstractPredicate;
        initialyze();
    }

    private void initialyze(){
        textFieldPredicate = new TextFieldPredicate<>(abstractPredicate);
        container = new DefaultVBox(textFieldPredicate);
    }

    @Override
    public PopupControl getSkinnable() {
        return popupControl;
    }

    @Override
    public Node getNode() {
        return container;
    }

    @Override
    public void dispose() {

    }

    public TextFieldPredicate<T> getTextFieldPredicate() {
        return textFieldPredicate;
    }

    public AbstractPredicate<T, String> getAbstractPredicate() {
        return abstractPredicate;
    }

    public DefaultVBox getContainer() {
        return container;
    }
}
