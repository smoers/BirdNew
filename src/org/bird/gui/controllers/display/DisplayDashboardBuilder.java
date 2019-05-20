package org.bird.gui.controllers.display;

import com.google.common.reflect.TypeToken;
import javafx.collections.ObservableList;
import org.bird.configuration.exceptions.ConfigurationException;

/**
 * Cette classe retourne l'instance de la DisplayDashboard
 */
public class DisplayDashboardBuilder {

    private ObservableList itemsContainer;

    /**
     * Constructeur
     * @param itemsContainer
     */
    public DisplayDashboardBuilder(ObservableList itemsContainer) {
        this.itemsContainer = itemsContainer;
    }

    /**
     * Retourne l'instance de la classe passé en parametre
     * @param clazz
     * @return
     * @throws ConfigurationException
     */
    public IDisplayDashboard build(Class clazz) throws ConfigurationException {
        IDisplayDashboard displayDashboard = null;
        if (clazz.getSimpleName().equals(DisplayDashboardItemAuthor.class.getSimpleName())){
            displayDashboard = new DisplayDashboardItemAuthor(itemsContainer);
        } else if (clazz.getSimpleName().equals(DisplayDashboardListAuthor.class.getSimpleName())){
            displayDashboard = new DisplayDashboardListAuthor(itemsContainer);
        } else if (clazz.getSimpleName().equals(DisplayDashboardItemBook.class.getSimpleName())){
            displayDashboard = new DisplayDashboardItemBook(itemsContainer);
        } else if (clazz.getSimpleName().equals(DisplayDashboardListBook.class.getSimpleName())){
            displayDashboard = new DisplayDashboardListBook(itemsContainer);
        }
        return displayDashboard;
    }
}
