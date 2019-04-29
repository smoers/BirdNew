package org.bird.gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.bird.configuration.exceptions.ConfigurationException;
import org.bird.db.models.Author;
import org.bird.db.query.Paginator;
import org.bird.gui.common.FXMLLoaderImpl;
import org.bird.gui.controllers.display.DisplayDataSheet;
import org.bird.gui.controllers.display.DisplayDashboardItemAuthor;
import org.bird.gui.events.ExitPlatformEvent;
import org.bird.gui.events.OnPageChangeEvent;
import org.bird.gui.events.OnSelectedEvent;
import org.bird.gui.listeners.OnPageChangeListener;
import org.bird.gui.listeners.OnSelectedListener;

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
    @FXML
    private SplitPane dashboardSplitPane;
    @FXML
    private FlowPane dataSheetPane;

    private FXMLLoaderImpl fxmlLoaderImpl;
    private DisplayDataSheet displayDataSheet;

    /**
     * Contructeur
     */
    public DashboardController(){
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            //le Loader
            fxmlLoaderImpl = new FXMLLoaderImpl();
            //charge le texte de l'interface
            setLanguage();
            setText(toolbar);
            //Layout
            dashboard.setMinSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
            dashboard.setMaxSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
            dashboard.setPrefSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
            //Display datasheet
            displayDataSheet = new DisplayDataSheet(dashboardSplitPane.getItems());
            //Configuration du controller
            Paginator<Author> paginator = Paginator.build(Author.class);
            DisplayDashboardItemAuthor displayDashboardItemAuthor = new DisplayDashboardItemAuthor(itemsContainer);
            //On défini un écouteur sur la selection d'un item
            displayDashboardItemAuthor.addOnSelectedListener(new OnSelectedListener<Author>() {
                @Override
                public void OnSelected(OnSelectedEvent<Author> evt) {
                    try {
                        displayDataSheet.display(evt.getItem());
                    } catch (IOException | ConfigurationException e) {
                        showException(e);
                    }
                }
            });
            PaginatorController paginatorController = new PaginatorController(paginator, displayDashboardItemAuthor);
            paginatorController.addOnPageChangeListener(new OnPageChangeListener() {
                @Override
                public void onChangePage(OnPageChangeEvent evt) {
                    displayDataSheet.remove();
                }
            });
            //Chargement de la WaitingBar
            WaitingBarController waitingBarController = new WaitingBarController(displayDashboardItemAuthor);
            FXMLLoader loaderWaitingBar = fxmlLoaderImpl.getFXMLLoader("waitingbar");
            loaderWaitingBar.setController(waitingBarController);
            Node nodeWaitingBar = loaderWaitingBar.load();
            bottonPane.getChildren().add(nodeWaitingBar);

            //Chargement du paginateur
            FXMLLoader loaderPaginator = fxmlLoaderImpl.getFXMLLoader("paginator");
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

    public FlowPane getItemsContainer() {
        return itemsContainer;
    }

    public SplitPane getDashboardSplitPane() {
        return dashboardSplitPane;
    }


}
