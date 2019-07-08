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

/**
 * Cette classe se charge de sauvegarder et de restaurer
 * les paramétres des fenetres
 */
public class SaveWindowsParameters {

    private Stage stage = null;
    private String key;
    private Number height = null;
    private Number width = null;
    private Boolean maximized = null;
    private Number defaultWidth = 0;
    private Number defaultHeight = 0;
    private Boolean defaultMaximized = false;
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
            configuration = configurationBuilder.get("global");
            JsonElement jsonElement = configuration.get(globalKey);
            if (!jsonElement.isJsonNull() && jsonElement.isJsonObject()){
                stage.setMaximized(jsonElement.getAsJsonObject().get("maximized").getAsBoolean());
                defaultMaximized = jsonElement.getAsJsonObject().get("maximized").getAsBoolean();
                stage.setWidth(jsonElement.getAsJsonObject().get("width").getAsDouble());
                defaultWidth = jsonElement.getAsJsonObject().get("width").getAsDouble();
                stage.setHeight(jsonElement.getAsJsonObject().get("height").getAsDouble());
                defaultHeight = jsonElement.getAsJsonObject().get("height").getAsDouble();
            } else {
                width = stage.getWidth();
                height = stage.getHeight();
                maximized = stage.isMaximized();
            }
        } catch (ConfigurationException e) {
            loggers.error(loggers.messageFactory.newMessage(e.getMessage(),this));
        }
        stage.widthProperty().addListener(((observableValue, number, t1) -> {
            if (!stage.isMaximized())
                width = t1;
        }));
        stage.heightProperty().addListener(((observableValue, number, t1) -> {
            if (!stage.isMaximized())
                height = t1;
        }));
        stage.maximizedProperty().addListener(((observable, bool, t1) -> {
            maximized = t1;
        }));
        /**
         * Sauvegarde les valeurs dans le fichier de configuration "global"
         */
        stage.setOnCloseRequest(windowEvent -> {
            //Si pas de changement dans les valeurs
            //les deux variables restent null
            if (null != width || null != height) {
                try {
                    configuration = configurationBuilder.get("global");
                    configuration.edit("global.stage_size_memorize",
                            new ConfigurationProperty(key, "id"),
                            new ConfigurationProperty(height, defaultHeight, "height"),
                            new ConfigurationProperty(width, defaultWidth, "width"),
                            new ConfigurationProperty(maximized, defaultMaximized, "maximized")
                    );
                    configuration.write();
                } catch (ConfigurationException | IOException e) {
                    loggers.error(loggers.messageFactory.newMessage(e.getMessage(), this));
                }
            }
        });
    }

}
