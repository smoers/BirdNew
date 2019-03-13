package org.bird.gui.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.bird.configuration.exceptions.ConfigurationException;
import org.bird.db.models.Author;
import org.bird.db.query.Paginator;
import org.bird.gui.controllers.display.DisplayItemDashboardAuthor;
import org.bird.gui.events.ExitPlatformEvent;
import org.bird.gui.events.OnProcessEvent;
import org.bird.gui.listeners.OnProcessListener;
import org.bird.gui.resources.images.ImageProvider;

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
    @FXML
    private VBox bottonPane;

    /**
     * Contructeur
     */
    public DashboardController(){}


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

            //Configuration du controller
            Paginator<Author> paginator = new Paginator<Author>(1,30,Author.class);
            DisplayItemDashboardAuthor displayItemDashboardAuthor = new DisplayItemDashboardAuthor(itemsContainer);
            PaginatorController paginatorController = new PaginatorController(paginator, displayItemDashboardAuthor);
            //Chargement de la WaitingBar
            ImageProvider provider = new ImageProvider("/images/loading3.gif");
            WaitingBarAnimatedController waitingBarController = new WaitingBarAnimatedController(provider);
            FXMLLoader loaderWaitingBar = new FXMLLoader();
            loaderWaitingBar.setLocation(getClass().getResource("/org/bird/gui/resources/views/waitingbar_animated.fxml"));
            loaderWaitingBar.setController(waitingBarController);
            Node nodeWaitingBar = loaderWaitingBar.load();
            bottonPane.getChildren().add(nodeWaitingBar);


            //Chargement du paginateur
            FXMLLoader loaderPaginator = new FXMLLoader();
            loaderPaginator.setLocation(getClass().getResource("/org/bird/gui/resources/views/paginator.fxml"));
            loaderPaginator.setController(paginatorController);
            Node nodePaginator = loaderPaginator.load();
            bottonPane.getChildren().add(nodePaginator);
            paginatorController.refresh();

            //evenements
            menuExit.setOnAction(new ExitPlatformEvent());

        } catch (ConfigurationException | IOException e) {
            showException(e);
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

}
