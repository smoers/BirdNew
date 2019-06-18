package org.bird.gui.controllers;

import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Window;
import org.bird.gui.resources.controls.DefaultAnchorPaneZero;

import java.net.URL;
/**
 * Etend la classe DataViewController afin d'afficher
 * dans un WebView l'url passé en content
 */
public class DataViewWebViewController extends DataViewController<URL> {

    public DataViewWebViewController(Window owner) {
        super(owner);
    }

    @Override
    protected AnchorPane displayContent() {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.load(getContent().toExternalForm());
        AnchorPane node = new DefaultAnchorPaneZero(webView);
        node.setPadding(new Insets(5.0));
        return node;
    }
}
