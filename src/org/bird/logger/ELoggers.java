package org.bird.logger;

/**
 * Enum utilisé pour le chemin des logger
 */
public enum ELoggers {

    SYSTEM("org.bird"),
    GUI("org.bird.gui");

    private String path;

    public String getPath() {
        return path;
    }

    ELoggers(String path) {
        this.path = path;
    }
}
