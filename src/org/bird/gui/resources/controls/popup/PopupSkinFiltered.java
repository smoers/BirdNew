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

package org.bird.gui.resources.controls.popup;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.PopupControl;
import javafx.scene.control.Skin;
import javafx.scene.layout.VBox;
import org.bird.gui.common.predicate.LoaderPredicate;
import org.bird.gui.resources.controls.DefaultVBox;
import org.bird.gui.resources.controls.TextFieldPredicate;

/**
 * Cette classe est le contenu d'un PopupControl destiné à filtrer
 * un objet de type FilterList
 * @param <T>
 */
public class PopupSkinFiltered<T> implements Skin<PopupControl> {

    /**
     * L'instance du PopupControl utilisée
     */
    private PopupControl popupControl;
    /**
     * Le champ pour introduire la valeur du filtre
     */
    private TextFieldPredicate<T> textFieldPredicate;
    /**
     * la classe prédicate pour pouvoir avoir une instace de l'objet TextFieldPredicate
     */
    private LoaderPredicate<T,String> loaderPredicate;
    /**
     * le container VBox
     */
    private DefaultVBox container;
    /**
     * Variable publique permettant de modifier le valeur des marges dans le VBox
     */
    public double margins = 5.0;

    /**
     * Constructeur
     * @param popupControl
     * @param loaderPredicate
     */
    public PopupSkinFiltered(PopupControl popupControl, LoaderPredicate<T,String> loaderPredicate) {
        this.popupControl = popupControl;
        this.loaderPredicate = loaderPredicate;
        initialyze();
    }

    /**
     * Setup
     */
    private void initialyze(){
        textFieldPredicate = new TextFieldPredicate<>(loaderPredicate);
        VBox.setMargin(textFieldPredicate,new Insets(margins));
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

    /**
     * l'objet TextFieldPredicate
     * @return
     */
    public TextFieldPredicate<T> getTextFieldPredicate() {
        return textFieldPredicate;
    }

    /**
     * le container
     * @return
     */
    public DefaultVBox getContainer() {
        return container;
    }

    /**
     * Permet d'ajouter un node dans le container
     * @param node
     */
    public void addNode(Node node){
        VBox.setMargin(node, new Insets(margins));
        container.getChildren().add(node);
    }
}
