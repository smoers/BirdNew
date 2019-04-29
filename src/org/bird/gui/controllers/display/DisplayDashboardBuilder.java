package org.bird.gui.controllers.display;

import org.bird.configuration.exceptions.ConfigurationException;
import org.bird.gui.controllers.DashboardController;

public class DisplayDashboardBuilder {

    private DashboardController controller;

    public DisplayDashboardBuilder(DashboardController controller) {
        this.controller = controller;
    }

    public IDisplayDashboard build(Class clazz) throws ConfigurationException {
        if (clazz.isInstance(DisplayDashboardItemAuthor.class)){
            return  new DisplayDashboardItemAuthor(controller.getItemsContainer());
        } else if (clazz.isInstance(DisplayDashboardListAuthor.class)){

        }
        return null;
    }
}
