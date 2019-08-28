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

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.layout.VBox;

public class DefaultVBox extends VBox {

    public double paddings = 5.0;

    public DefaultVBox() {
        initialize();
    }

    public DefaultVBox(double v) {
        super(v);
        initialize();
    }

    public DefaultVBox(Node... nodes) {
        super(nodes);
        initialize();
    }

    public DefaultVBox(double v, Node... nodes) {
        super(v, nodes);
        initialize();
    }

    private void initialize(){
        setMaxSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
        setMinSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
        setPrefSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
        setPadding(new Insets(paddings));

    }
}
