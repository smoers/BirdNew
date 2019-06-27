package org.bird.gui.common.tableview.extended;

import org.apache.logging.log4j.Logger;
import org.bird.logger.ELoggers;
import org.bird.logger.Loggers;

import java.lang.reflect.InvocationTargetException;

public class TransposerFactory {

    private static TransposerFactory ourInstance = new TransposerFactory();
    private Loggers loggers = Loggers.getInstance();
    private Logger logger;

    public static TransposerFactory getInstance() {
        return ourInstance;
    }

    private TransposerFactory() {
        loggers.setDefaultLogger(ELoggers.GUI);
    }

    public ITransposer getTransposerInstance(Class clazz, Object object){
        ITransposer transposer = null;
        String name = clazz.getCanonicalName();
        try {
            Class<ITransposer> aClass = (Class<ITransposer>) Class.forName(name);
            transposer = aClass.getDeclaredConstructor(Object.class).newInstance(((Object) object));
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            loggers.error(loggers.messageFactory.newMessage(e.getMessage(), this));
            return null;
        }
        return transposer;
    }


}
