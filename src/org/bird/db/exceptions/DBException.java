package org.bird.db.exceptions;

/**
 * Exception dans le cas où la database n'est pas définie
 */
public class DBException extends Exception {

    private static final long serialVersionUID = 5874598321574521475L;

    private int code;

    /**
     *
     * @param code
     */
    public DBException(int code) {
        super();
    }

    /**
     *
     * @param message
     * @param code
     */
    public DBException(String message, int code) {
        super(message);
        this.code = code;
    }

    /**
     *
     * @param message
     * @param cause
     * @param code
     */
    public DBException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }

    /**
     *
     * @param cause
     * @param code
     */
    public DBException(Throwable cause, int code) {
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
    public DBException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code) {
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
}
