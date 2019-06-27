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

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SaveWindowsParameters {

    private Stage stage = null;
    private Scene scene = null;

    public SaveWindowsParameters(Stage stage) {
        this.stage = stage;
    }

    public SaveWindowsParameters(Scene scene) {
        this.scene = scene;
    }

    private void initializeStage(){
        stage.widthProperty().addListener(((observableValue, number, t1) -> {
            System.out.println("Stage Width: " + number +" : "+ t1);
        }));
        stage.heightProperty().addListener(((observableValue, number, t1) -> {
            System.out.println("Stage Height: " + number +" : "+ t1);
        }));
    }


}
