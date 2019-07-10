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

package org.bird.gui.controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.bird.configuration.exceptions.ConfigurationException;
import org.bird.gui.common.FXMLLoaderImpl;
import org.bird.gui.common.SaveWindowsParameters;
import org.bird.gui.resources.images.ImageProvider;

import java.io.IOException;

public abstract class DisplayWindowController extends ProtectedController implements Initializable {

    protected SaveWindowsParameters saveWindowsParameters;
    protected Stage stage = new Stage();

    public void show(Window owner) throws ConfigurationException, IOException {
        FXMLLoaderImpl loaderImpl = new FXMLLoaderImpl();
        FXMLLoader loader = loaderImpl.getFXMLLoader(getPathFXML());
        loader.setController(this);
        stage.setScene(new Scene(loader.load()));
        stage.getIcons().add(ImageProvider.getLogoImage());
        stage.setTitle(getTitle());
        stage.initModality(getModality());
        stage.initOwner(owner);
        saveWindowsParameters = new SaveWindowsParameters(stage, getClass().getSimpleName());
        stage.show();

    }

    protected abstract void setPathFXML(Class ... clazz);

    protected abstract Class[] getPathFXML();

    protected abstract void setModality(Modality modality);

    protected abstract Modality getModality();

    public abstract void setTitle(String title);

    public abstract String getTitle();


}
