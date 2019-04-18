package org.bird.gui.resources.controls;

import javafx.scene.control.Control;
import javafx.scene.control.Label;

/**
 * Cet objet étend l'objet Label afin de limiter la taille du texte
 * à la valeur du paramêtre LimitedSize
 */
public class LabelLimited extends Label {

    protected int limitedSize = 100;
    protected boolean isLimited = false;
    protected String originalText;

    public LabelLimited() {
        super();
        setMinSize(Control.USE_PREF_SIZE, Control.USE_COMPUTED_SIZE);
        setPrefSize(Control.USE_COMPUTED_SIZE,Control.USE_COMPUTED_SIZE);
        setMaxSize(Control.USE_COMPUTED_SIZE,Control.USE_COMPUTED_SIZE);
    }

    /**
     * Text à limiter à l'affichage si plus grand que limitedSize
     * @param value
     */
    public void setLimitedText(String value) {
        originalText = value;
        if (value.length() > limitedSize) {
            value = value.substring(0, limitedSize) + " ...";
            isLimited = true;
        }
        setText(value);
    }

    /**
     * Texte après avoir été limité
     * @return
     */
    public String getLimitedText(){
        return getText();
    }

    /**
     * Le texte est-il limité ?
     * @return
     */
    public boolean isLimited() {
        return isLimited;
    }

    /**
     * taille de la limitation
     * @return
     */
    public int getLimitedSize() {
        return limitedSize;
    }

    /**
     * Défini la limitation
     * @param limitedSize
     */
    public void setLimitedSize(int limitedSize) {
        this.limitedSize = limitedSize;
    }

    /**
     * Retourne le texte original
     * @return
     */
    public String getOriginalText(){
        return originalText;
    }
}
