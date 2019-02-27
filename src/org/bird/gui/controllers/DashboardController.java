package org.bird.gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.bird.configuration.exceptions.ConfigurationException;
import org.bird.db.query.Paginator;
import org.bird.gui.events.ExitPlatformEvent;
import org.bird.gui.events.OnLeftClickEvent;
import org.bird.gui.listeners.OnLeftClickListener;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController extends ProtectedController implements Initializable {

    @FXML
    private MenuItem menuExit;
    @FXML
    private Menu menuFile;
    @FXML
    private MenuBar menuBar;
    @FXML
    private FlowPane itemsContainer;
    @FXML
    private BorderPane mainItemDashboard;
    @FXML
    private BorderPane dashboard;
    @FXML
    private ToolBar toolbar;
    @FXML
    private Button buttonLarge;
    @FXML
    private Button buttonList;
    //
    private VBox selectedContainer = null;

    /**
     * Contructeur
     */
    public DashboardController() throws ConfigurationException {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            //charge le texte de l'interface
            setLanguage();
            setText(toolbar);
            //Layout
            dashboard.setMinSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
            dashboard.setMaxSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
            dashboard.setPrefSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);

            //Chargement du paginateur
            FXMLLoader loader = new FXMLLoader();
            Node node = null;
            loader.setLocation(getClass().getResource("/org/bird/gui/resources/views/paginator.fxml"));
            try {
                node = loader.load();
                mainItemDashboard.setBottom(node);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //evenements
            menuExit.setOnAction(new ExitPlatformEvent());

            //Charge le dashboard
            int ii = 100;
            for (int i = 1; i <= ii; i++) {
                FXMLLoader _loader = new FXMLLoader();
                _loader.setLocation(getClass().getResource("/org/bird/gui/resources/views/itemDashboard.fxml"));
                Node _node = _loader.load();
                ItemDashboardController item = (ItemDashboardController) _loader.getController();
                item.addOnLeftClickListener(new OnLeftClickListener() {
                    @Override
                    public void onLeftClick(OnLeftClickEvent evt) {
                        VBox container = (VBox) evt.getSource();
                        if (evt.getClickCount() == 1) {
                            if ((selectedContainer != null)) {
                                selectedContainer.getStyleClass().clear();
                                selectedContainer.getStyleClass().add("item_container");
                            }
                            container.getStyleClass().add("item_container_active");
                            selectedContainer = container;
                        }
                    }
                });
                    itemsContainer.getChildren().add(_node);
            }
        } catch (ConfigurationException | IOException e) {
            shoxException(e);
        }
    }

    /**
     * Charge le texte sur base de la langue
     */
    public void setLanguage(){
        //Ne defini le texte que si l'objet InternationalizationBundle est non null
        if (internationalizationBundle != null) {
            menuFile.setText(internationalizationBundle.getString("Dashboard"));
            menuExit.setText(internationalizationBundle.getString("Exit"));
            //ToolBar
            buttonLarge.setText(internationalizationBundle.getString("Large"));
            buttonList.setText(internationalizationBundle.getString("List"));
        }

    }

    public void showDashboardItems(Paginator paginator){

    }
}
