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

package org.bird.sandbox;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.Label;
import javafx.scene.control.skin.ComboBoxBaseSkin;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class _ComboBoxMulti extends Application {

    @Override
    public void start(Stage primaryStage) {
        ComboBoxBase<String> comboBoxBase = new ComboBoxBase<>(){};
        ComboBoxBaseSkin<String> stringComboBoxBaseSkin = new ComboBoxBaseSkin<String>(comboBoxBase) {
            @Override
            public Node getDisplayNode() {
                return new Label();
            }

            @Override
            public void show() {

            }

            @Override
            public void hide() {

            }
        };
        BorderPane root = new BorderPane();
        root.setCenter(comboBoxBase);
        //root.setTop();

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public class Item {
        private final String name;
        private final StringProperty details = new SimpleStringProperty();

        public Item(String name, String details) {
            this.name = name;
            setDetails(details);
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return getName();
        }

        public final StringProperty detailsProperty() {
            return this.details;
        }


        public final String getDetails() {
            return this.detailsProperty().get();
        }


        public final void setDetails(final String details) {
            this.detailsProperty().set(details);
        }


    }

    public static void main(String[] args) {
        launch(args);
    }
}
