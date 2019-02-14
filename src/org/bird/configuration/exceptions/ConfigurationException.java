package org.bird.configuration.exceptions;

import org.bird.exceptions.BirdException;
import org.bird.i18n.InternationalizationBuilder;
import org.bird.i18n.InternationalizationBundle;

/**
 * Exceptions destinée à être utilisée dans le package org.bird.configuration
 * la traduction des message est assurée par la méthode getI18nMessage()
 */
public class ConfigurationException extends BirdException{

    public ConfigurationException(int code) {
        super(code);
    }

    public ConfigurationException(String message, int code) {
        super(message, code);
    }

    public ConfigurationException(String message, Throwable cause, int code) {
        super(message, cause, code);
    }

    public ConfigurationException(Throwable cause, int code) {
        super(cause, code);
    }

    public ConfigurationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code) {
        super(message, cause, enableSuppression, writableStackTrace, code);
    }

    @Override
    public String getI18nMessage() {
        InternationalizationBundle i18n = InternationalizationBuilder.getInstance().getInternationalizationBundle(ConfigurationException.class);
        return i18n.getString(String.valueOf(getCode()));
    }
}
