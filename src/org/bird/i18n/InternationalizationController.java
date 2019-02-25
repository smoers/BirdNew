package org.bird.i18n;

/**
 * Classe abstraite permettant l'internationalisation d'un controller FX
 */
public abstract class InternationalizationController {

    protected final InternationalizationBuilder internationalizationBuilder = InternationalizationBuilder.getInstance();
    protected InternationalizationBundle internationalizationBundle = null;

    public InternationalizationBundle getInternationalizationBundle() {
        return internationalizationBundle;
    }

    public void setInternationalizationBundle(InternationalizationBundle internationalizationBundle) {
        this.internationalizationBundle = internationalizationBundle;
    }

    /**
     * Méthode à implémenter
     */
    public abstract void setLanguage();
}
