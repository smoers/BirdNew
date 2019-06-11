package org.bird.gui.resources.controls;

import javafx.scene.control.MenuItem;

import java.net.MalformedURLException;
import java.net.URL;

public class Favorite extends MenuItem {

    private URL url;

    public Favorite(String name, URL url) {
        super(name);
        this.url = url;
    }

    public Favorite(String name,String url) throws MalformedURLException {
        super(name);
        this.url = new URL(url);
    }

    public URL getUrl() {
        return url;
    }

}
