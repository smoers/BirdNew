package org.bird.sandbox;

import org.apache.logging.log4j.Logger;
import org.bird.logger.Loggers;

import java.util.Date;

public class _Loggers {

    public static void main(String[] args) {
        Loggers loggers = Loggers.getInstance();
        Logger logger = loggers.getLogger("org.bird.gui");
        loggers.error(logger, loggers.messageFactory.newMessage("Test", new Date()));
    }

}
