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

import com.google.gson.JsonElement;
import javafx.stage.Stage;
import org.bird.configuration.Configuration;
import org.bird.configuration.ConfigurationBuilder;
import org.bird.configuration.ConfigurationProperty;
import org.bird.configuration.exceptions.ConfigurationException;
import org.bird.logger.ELoggers;
import org.bird.logger.Loggers;

import java.io.IOException;

public class SaveWindowsParameters {

    private Stage stage = null;
    private String key;
    private Number height;
    private Number width;
    private Loggers loggers = Loggers.getInstance();
    private ConfigurationBuilder configurationBuilder = ConfigurationBuilder.getInstance();
    private Configuration configuration;
    private String globalKey = "global.stage_size_memorize.";

    public SaveWindowsParameters(Stage stage,String key) {
        this.stage = stage;
        this.key = key;
        globalKey = globalKey+key;
        loggers.setDefaultLogger(ELoggers.GUI);
        initializeStage();
    }

    private void initializeStage(){
        try {
            JsonElement jsonElement = configuration.get(globalKey);
            if (jsonElement.isJsonObject()){
                stage.setWidth(jsonElement.getAsJsonObject().get("width").getAsDouble());
                stage.setHeight(jsonElement.getAsJsonObject().get("height").getAsDouble());
            }
        } catch (ConfigurationException e) {
            loggers.error(loggers.messageFactory.newMessage(e.getMessage(),this));
        }
        stage.widthProperty().addListener(((observableValue, number, t1) -> {
            width = t1;
        }));
        stage.heightProperty().addListener(((observableValue, number, t1) -> {
            height = t1;
        }));
        /**
         * Sauvegarde les valeurs dans le fichier de configuration "global"
         */
        stage.setOnCloseRequest(windowEvent -> {
            try {
                configuration = configurationBuilder.get("global");
                configuration.edit("global.stage_size_memorize",
                        new ConfigurationProperty(key, "id"),
                        new ConfigurationProperty(height,"height"),
                        new ConfigurationProperty(width,"width")
                        );
                configuration.write();
            } catch (ConfigurationException | IOException e) {
                loggers.error(loggers.messageFactory.newMessage(e.getMessage(),this));
            }
        });
    }

}
