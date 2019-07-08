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

package org.bird.configuration;

import com.google.gson.JsonPrimitive;

/**
 * Elle est utilisée pour la construction d'un objet JsonObject
 * par la classe de configuration
 */
public class ConfigurationProperty {

    private JsonPrimitive primitive;
    private String name;

    /**
     * Constructeur
     * @param bool
     * @param name
     */
    public ConfigurationProperty(Boolean bool, String name) {
        primitive = new JsonPrimitive(bool);
        this.name = name;
    }

    /**
     * Constructeur
     * @param bool
     * @param defaultBoot
     * @param name
     */
    public ConfigurationProperty(Boolean bool, Boolean defaultBoot, String name) {
        primitive = new JsonPrimitive(this.<Boolean>isNull(bool, defaultBoot));
        this.name = name;
    }

    /**
     * Constructeur
     * @param number
     * @param name
     */
    public ConfigurationProperty(Number number, String name) {
        primitive = new JsonPrimitive(number);
        this.name = name;
    }

    /**
     * Constructeur
     * @param number
     * @param defaultNumber
     * @param name
     */
    public ConfigurationProperty(Number number, Number defaultNumber, String name) {
        primitive = new JsonPrimitive(this.<Number>isNull(number,defaultNumber));
        this.name = name;
    }

    /**
     * Constructeur
     * @param string
     * @param name
     */
    public ConfigurationProperty(String string, String name) {
        primitive = new JsonPrimitive(string);
        this.name = name;
    }

    /**
     * Constructeur
     * @param string
     * @param defaultString
     * @param name
     */
    public ConfigurationProperty(String string, String defaultString, String name) {
        primitive = new JsonPrimitive(this.<String>isNull(string, defaultString));
        this.name = name;
    }

    /**
     * Constructeur
     * @param c
     * @param name
     */
    public ConfigurationProperty(Character c, String name) {
        primitive = new JsonPrimitive(c);
        this.name = name;
    }

    /**
     * Constructeur
     * @param c
     * @param defaultC
     * @param name
     */
    public ConfigurationProperty(Character c, Character defaultC, String name) {
        primitive = new JsonPrimitive(this.<Character>isNull(c,defaultC));
        this.name = name;
    }

    /**
     * Retourne le nom attribué à la propriété
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Propriété sous forme d'un objet JsonPrimitive
     * @return
     */
    public JsonPrimitive getJsonPrimitive(){
        return primitive;
    }

    private <T> T isNull(T value, T defaultValue){
        return null != value ? value : defaultValue ;
    }

}
