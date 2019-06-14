package org.bird.gui.resources.controls;

import javafx.scene.control.MenuItem;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Etend la classe MenuItem afin d'ajouter les parametres URL & ID
 */
public class Favorite extends MenuItem {

    private URL url;
    private String id;

    /**
     * Constructeur
     * @param id
     * @param name
     * @param url
     */
    public Favorite(String id, String name, URL url) {
        super(name);
        this.url = url;
        this.id = id;
    }

    /**
     * Constructeur
     * @param id
     * @param name
     * @param url
     * @throws MalformedURLException
     */
    public Favorite(String id, String name,String url) throws MalformedURLException {
        super(name);
        this.url = new URL(url);
        this.id = id;
    }

    /**
     * Retourne l'URL
     * @return
     */
    public URL getUrl() {
        return url;
    }

    /**
     * Retourne l'ID du favori
     * @return
     */
    public String getIdFavorite() {
        return id;
    }
}
