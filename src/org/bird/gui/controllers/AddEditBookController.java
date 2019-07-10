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

import javafx.stage.Modality;

import java.net.URL;
import java.util.ResourceBundle;

public class AddEditBookController extends DisplayWindowController {

    private Class[] pathFXML;
    private Modality modality;
    private String title;

    public AddEditBookController() {
        setting();
    }

    @Override
    protected void setPathFXML(Class ... clazz) {
        pathFXML = clazz;
    }

    @Override
    protected Class[] getPathFXML() {
        return pathFXML;
    }

    @Override
    protected void setModality(Modality modality) {
        this.modality = modality;
    }

    @Override
    protected Modality getModality() {
        return modality;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    public void setting(){

        setPathFXML(getClass());
        setModality(Modality.NONE);
        setTitle("Add Book");
    }

    @Override
    public void setLanguage() {

    }

}
