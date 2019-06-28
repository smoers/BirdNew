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

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.stage.Stage;
import org.bird.configuration.Configuration;
import org.bird.configuration.ConfigurationBuilder;
import org.bird.configuration.exceptions.ConfigurationException;
import org.bird.logger.ELoggers;
import org.bird.logger.Loggers;

public class SaveWindowsParameters {

    private Stage stage = null;
    private String key;
    private Number height;
    private Number width;
    private Loggers loggers = Loggers.getInstance();
    private ConfigurationBuilder configurationBuilder = ConfigurationBuilder.getInstance();
    private Configuration configuration;

    public SaveWindowsParameters(Stage stage) {
        this.stage = stage;
        loggers.setDefaultLogger(ELoggers.GUI);
        initializeStage();
    }

    private void initializeStage(){
        stage.widthProperty().addListener(((observableValue, number, t1) -> {
            width = t1;
        }));
        stage.heightProperty().addListener(((observableValue, number, t1) -> {
            height = t1;
        }));
        stage.setOnCloseRequest(windowEvent -> {
            try {
                configuration = configurationBuilder.get("global");
                if (configuration.get("global.stage_size_memorize").isJsonArray()){
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty();
                    JsonArray jsonArray = configuration.get("global.stage_size_memorize").getAsJsonArray();
                    if (jsonArray.contains())
                }

            } catch (ConfigurationException e) {
                loggers.error(loggers.messageFactory.newMessage(e.getMessage(),this));
            }

            System.out.println("close");
        });
    }

}
