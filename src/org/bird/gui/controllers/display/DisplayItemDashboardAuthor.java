package org.bird.gui.controllers.display;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import org.bird.db.models.Author;
import org.bird.db.query.Paginator;
import org.bird.gui.controllers.ItemDashboardController;
import org.bird.gui.events.OnLeftClickEvent;
import org.bird.gui.events.OnProgressChangeEvent;
import org.bird.gui.listeners.OnLeftClickListener;

import java.io.IOException;

public class DisplayItemDashboardAuthor extends DisplayItemDashboard<Author> {

    private Pane itemsContainer;
    private Pane selected = null;
    private Label lbl;

    /**
     * Contructeur
     * @param itemsContainer
     */
    public DisplayItemDashboardAuthor(Pane itemsContainer, Label lbl){
        this.itemsContainer = itemsContainer;
        this.lbl = lbl;
    }

    /**
     * Affiche les items au depart d'un objet Paginator
     * @param paginator
     * @throws IOException
     */
    @Override
    public void display(Paginator<Author> paginator) throws IOException {
        double size = paginator.getItemsByPage();
        double value = 1;
        //vide le conteneur
        itemsContainer.getChildren().clear();
        //Charge le dashboard
        for (Author author : paginator.getList()) {
            notifyOnProgressChangeListener(new OnProgressChangeEvent(this,value,size));
            lbl.setText(String.valueOf(value));
            FXMLLoader _loader = new FXMLLoader();
            _loader.setLocation(getClass().getResource("/org/bird/gui/resources/views/itemDashboard.fxml"));
            Node _node = _loader.load();
            ItemDashboardController item = (ItemDashboardController) _loader.getController();
            item.setItem(author);
            item.addOnLeftClickListener(new OnLeftClickListener() {
                @Override
                public void onLeftClick(OnLeftClickEvent evt) {
                    Pane container = (Pane) evt.getSource();
                    if (evt.getClickCount() == 1) {
                        if ((selected != null)) {
                            selected.getStyleClass().clear();
                            selected.getStyleClass().add("item_container");
                        }
                        container.getStyleClass().add("item_container_active");
                        selected = container;
                    }
                }
            });
            itemsContainer.getChildren().add(_node);
            value++;
        }
    }

}
