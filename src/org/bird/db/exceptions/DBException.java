package org.bird.db.exceptions;

import org.bird.exceptions.BirdException;
import org.bird.i18n.InternationalizationBuilder;
import org.bird.i18n.InternationalizationBundle;

/**
 * Exception dans le cas où la database n'est pas définie
 */
public class DBException extends BirdException {

    private static final long serialVersionUID = 5874598321574521475L;

    public DBException(int code) {
        super(code);
    }

    public DBException(String message, int code) {
        super(message, code);
    }

    public DBException(String message, Throwable cause, int code) {
        super(message, cause, code);
    }

    public DBException(Throwable cause, int code) {
        super(cause, code);
    }

    public DBException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code) {
        super(message, cause, enableSuppression, writableStackTrace, code);
    }

    @Override
    public String getI18nMessage() {
        InternationalizationBundle i18n = InternationalizationBuilder.getInstance().getInternationalizationBundle(DBException.class);
        return i18n.getString(String.valueOf(getCode()));
    }
}
