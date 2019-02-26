package org.bird.gui.controllers;

import javafx.collections.transformation.FilteredList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import org.bird.configuration.Configuration;
import org.bird.configuration.ConfigurationBuilder;
import org.bird.configuration.exceptions.ConfigurationException;
import org.bird.i18n.InternationalizationController;

import java.util.ListIterator;
import java.util.function.Predicate;

public abstract class ProtectedController extends InternationalizationController {

    protected ConfigurationBuilder configurationBuilder = ConfigurationBuilder.getInstance();

    protected Configuration getConfigurationLayout() throws ConfigurationException {
        return configurationBuilder.get("layout");
    }

    protected void setText(Pane pane) throws ConfigurationException {
        if (!getConfigurationLayout().get("layout.iftext.button").getAsBoolean()) {
            FilteredList<Node> list = pane.getChildren().filtered(new Predicate<Node>() {
                @Override
                public boolean test(Node node) {
                    return node instanceof Button;
                }
            });
            ListIterator<Node> it = list.listIterator();
            while (it.hasNext()) {
                Button button = (Button) it.next();
                button.setText("");
            }
        }
    }

    protected void setText(ToolBar toolBar) throws ConfigurationException {
        if (!getConfigurationLayout().get("layout.iftext.button").getAsBoolean()) {
            FilteredList<Node> list = toolBar.getItems().filtered(new Predicate<Node>() {
                @Override
                public boolean test(Node node) {
                    return node instanceof Button;
                }
            });
            ListIterator<Node> it = list.listIterator();
            while (it.hasNext()) {
                Button button = (Button) it.next();
                button.setText("");
            }
        }
    }

}
