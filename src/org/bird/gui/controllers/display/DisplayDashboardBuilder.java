package org.bird.gui.controllers.display;

import javafx.scene.layout.Pane;
import org.bird.configuration.exceptions.ConfigurationException;

public class DisplayDashboardBuilder {

    private Pane itemsContainer;

    public DisplayDashboardBuilder(Pane itemsContainer) {
        this.itemsContainer = itemsContainer;
    }

    public IDisplayDashboard build(Class clazz) throws ConfigurationException {
        IDisplayDashboard displayDashboard = null;
        if (clazz.getSimpleName().equals(DisplayDashboardItemAuthor.class.getSimpleName())){
            displayDashboard = new DisplayDashboardItemAuthor(itemsContainer);
        } else if (clazz.getSimpleName().equals(DisplayDashboardListAuthor.class.getSimpleName())){
            displayDashboard = new DisplayDashboardListAuthor(itemsContainer);
        }
        return displayDashboard;
    }
}
