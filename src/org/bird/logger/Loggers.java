/*
 * *
 *  * Copyright (c) 2018.  MO Consult
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 *
 */

package org.bird.logger;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.message.FormattedMessageFactory;
import org.apache.logging.log4j.message.Message;

import java.util.HashMap;

/**
 * Gestion des Logger
 */
public class Loggers {

    private HashMap<String, Logger> loggers;
    private static Loggers ourInstance = new Loggers();
    public final FormattedMessageFactory messageFactory = new FormattedMessageFactory();
    private Logger defaultLogger;

    /**
     * Instance
     * @return
     */
    public static Loggers getInstance() {
        return ourInstance;
    }

    /**
     * Constructeur privé
     */
    private Loggers() {
        loggers = new HashMap<String, Logger>();
        LoggerContext loggerContext = (LoggerContext) LogManager.getContext(false);
        loggerContext.getConfiguration().getLoggers().forEach((String key, LoggerConfig loggerConfig) ->{
            loggers.put(key, LogManager.getLogger(key));
        });
        //Premier logger comme default
        defaultLogger = loggers.get(loggers.keySet().stream().findFirst().get());
    }

    /**
     * Retourne le logger demandé
     * @param name
     * @return
     */
    public Logger getLogger(String name){
        return loggers.get(name);
    }

    /**
     * Retourne le logger demandé
     * @param eLoggers
     * @return
     */
    public Logger getLogger(ELoggers eLoggers){ return getLogger(eLoggers.getPath());}

    /**
     * Retourne le current default logger
     * @return
     */
    public Logger getDefaultLogger() {
        return defaultLogger;
    }

    /**
     * Defini le default logger
     * @param defaultLoggerName
     */
    public void setDefaultLogger(String defaultLoggerName) {
        this.defaultLogger = getLogger(defaultLoggerName);
    }

    /**
     * Defini le default logger
     * @param eLoggers
     */
    public void setDefaultLogger(ELoggers eLoggers) { setDefaultLogger(eLoggers.getPath());}

    /**
     * log un message
     * @param message
     * @param level
     */
    public void setMessage(Message message, Level level){
        setMessage(defaultLogger, message, level);
    }

    /**
     * log un message
     * @param loggerName
     * @param message
     * @param level
     */
    public void setMessage(String loggerName, Message message, Level level){
        setMessage(getLogger(loggerName),message,level);
    }

    /**
     * log un message
     * @param logger
     * @param message
     * @param level
     */
    public void setMessage(Logger logger, Message message, Level level){
        logger.log(level, message);
    }

    /**
     * Info
     * @param message
     */
    public void info(Message message){
        setMessage(defaultLogger, message, Level.INFO);
    }

    /**
     * Info
     * @param logger
     * @param message
     */
    public void info(Logger logger, Message message){
        setMessage(logger, message, Level.INFO);
    }

    /**
     * Warn
     * @param message
     */
    public void warn(Message message){
        setMessage(defaultLogger, message, Level.WARN);
    }

    /**
     * Warn
     * @param logger
     * @param message
     */
    public void warn(Logger logger, Message message){
        setMessage(logger, message, Level.WARN);
    }

    /**
     * Trace
     * @param message
     */
    public void trace(Message message){
        setMessage(defaultLogger, message, Level.TRACE);
    }

    /**
     * Trace
     * @param logger
     * @param message
     */
    public void trace(Logger logger, Message message){
        setMessage(logger, message, Level.TRACE);
    }

    /**
     * Fatal
     * @param message
     */
    public void fatal(Message message){
        setMessage(defaultLogger, message, Level.FATAL);
    }

    /**
     * Fatal
     * @param logger
     * @param message
     */
    public void fatal(Logger logger, Message message){
        setMessage(logger, message, Level.FATAL);
    }

    /**
     * Error
     * @param message
     */
    public void error(Message message){
        setMessage(defaultLogger, message, Level.ERROR);
    }

    /**
     * Error
     * @param logger
     * @param message
     */
    public void error(Logger logger, Message message){
        setMessage(logger, message, Level.ERROR);
    }

    /**
     * Debug
     * @param message
     */
    public void debug(Message message){
        setMessage(defaultLogger, message, Level.DEBUG);
    }

    /**
     * Debug
     * @param logger
     * @param message
     */
    public void debug(Logger logger, Message message){
        setMessage(logger, message, Level.DEBUG);
    }

    /**
     * All
     * @param message
     */
    public void all(Message message){
        setMessage(defaultLogger, message, Level.ALL);
    }

    /**
     * All
     * @param logger
     * @param message
     */
    public void all(Logger logger, Message message){
        setMessage(logger, message, Level.ALL);
    }







}
