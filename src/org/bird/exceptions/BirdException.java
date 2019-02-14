package org.bird.exceptions;

public abstract class BirdException extends Exception implements BirdExceptionI18nInterface {

    private static final long serialVersionUID = 8454545554521475L;
    private int code;

    /**
     *
     * @param code
     */
    public BirdException(int code) {
        super();
        this.code = code;
    }

    /**
     *
     * @param message
     * @param code
     */
    public BirdException(String message, int code) {
        super(message);
        this.code = code;
    }

    /**
     *
     * @param message
     * @param cause
     * @param code
     */
    public BirdException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }

    /**
     *
     * @param cause
     * @param code
     */
    public BirdException(Throwable cause, int code) {
        super(cause);
        this.code = code;
    }

    /**
     *
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     * @param code
     */
    public BirdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }

    /**
     * Retourne le code error
     * @return
     */
    public int getCode() {
        return code;
    }

    @Override
    public abstract String getI18nMessage();
}
