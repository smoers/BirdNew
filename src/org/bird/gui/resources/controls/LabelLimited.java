package org.bird.gui.resources.controls;

import javafx.scene.control.Label;

public class LabelLimited extends Label {

    protected int limitedSize = 100;
    protected boolean isLimited = false;

    public LabelLimited() {
        super();
    }

    /**
     * Text à limiter à l'affichage si plus grand que limitedSize
     * @param value
     */
    public void setLimitedText(String value) {
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
}
