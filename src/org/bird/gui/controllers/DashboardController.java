package org.bird.gui.controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import org.bird.configuration.Configuration;
import org.bird.configuration.ConfigurationDashboardDisplayDefault;
import org.bird.configuration.exceptions.ConfigurationException;
import org.bird.db.models.Author;
import org.bird.db.models.Book;
import org.bird.db.query.Paginator;
import org.bird.gui.common.FXMLLoaderImpl;
import org.bird.gui.controllers.display.*;
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
    private ToggleButton buttonBook;
    @FXML
    private ToggleButton buttonAuthor;
    @FXML
    private VBox bottonPane;
    @FXML
    private SplitPane dashboardSplitPane;
    @FXML
    private FlowPane dataSheetPane;
    @FXML
    private AnchorPane dashboardDivider01;

    private FXMLLoaderImpl fxmlLoaderImpl;
    private DisplayDataSheet displayDataSheet;
    private PaginatorController paginatorController;
    private ConfigurationDashboardDisplayDefault configurationDefault;

    /**
     * Contructeur
     */
    public DashboardController(){
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

    /**
     * Charge le panneau centrale avec les auteurs sous le format d'Item
     * @return
     * @throws ConfigurationException
     */
    protected IDisplayDashboard<Author> setItemAuthor() throws ConfigurationException {
        //Display datasheet
        displayDataSheet = new DisplayDataSheet(dashboardSplitPane.getItems());
        //Configuration du controller
        Paginator<Author> paginator = Paginator.build(Author.class);
        //Display objet
        DisplayDashboardBuilder builder = new DisplayDashboardBuilder(dashboardSplitPane.getItems());
        IDisplayDashboard<Author> displayDashboard = builder.build(DisplayDashboardItemAuthor.class);
        //On défini un écouteur sur la selection d'un item
        displayDashboard.addOnSelectedListener(new OnSelectedListener<Author>() {
            @Override
            public void OnSelected(OnSelectedEvent<Author> evt) {
                try {
                    displayDataSheet.display(evt.getItem());
                } catch (IOException | ConfigurationException e) {
                    showException(e);
                }
            }
        });
        paginatorController = new PaginatorController(paginator, displayDashboard);
        paginatorController.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onChangePage(OnPageChangeEvent evt) {
                displayDataSheet.remove();
            }
        });
        bottonPane.getChildren().clear();
        return displayDashboard;
    }

    /**
     * Charge le panneau centrale avec les auteurs sous le format d'une List
     * @return
     * @throws ConfigurationException
     */
    protected IDisplayDashboard<Author> setListAuthor() throws ConfigurationException {
        //Display datasheet
        displayDataSheet = new DisplayDataSheet(dashboardSplitPane.getItems());
        //Configuration du controller
        Paginator<Author> paginator = Paginator.build(Author.class);
        //Display objet
        DisplayDashboardBuilder builder = new DisplayDashboardBuilder(dashboardSplitPane.getItems());
        IDisplayDashboard<Author> displayDashboard = builder.build(DisplayDashboardListAuthor.class);
        displayDashboard.addOnSelectedListener(new OnSelectedListener<Author>() {
            @Override
            public void OnSelected(OnSelectedEvent<Author> evt) {
                try {
                    displayDataSheet.display(evt.getItem());
                } catch (Exception e) {
                    showException(e);
                }
            }
        });
        paginatorController = new PaginatorController(paginator, displayDashboard);
        paginatorController.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onChangePage(OnPageChangeEvent evt) {
                displayDataSheet.remove();
            }
        });
        bottonPane.getChildren().clear();
        return displayDashboard;
    }

    /**
     * Charge le panneau centrale avec les livres au format Item
     * @return
     * @throws ConfigurationException
     */
    protected IDisplayDashboard<Book> setItemBook() throws ConfigurationException {
        //Display datasheet
        displayDataSheet = new DisplayDataSheet(dashboardSplitPane.getItems());
        //Configuration du controller
        Paginator<Book> paginator = Paginator.build(Book.class);
        //Display objet
        DisplayDashboardBuilder builder = new DisplayDashboardBuilder(dashboardSplitPane.getItems());
        IDisplayDashboard<Book> displayDashboard = builder.build(DisplayDashboardItemBook.class);
        paginatorController = new PaginatorController(paginator, displayDashboard);
        paginatorController.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onChangePage(OnPageChangeEvent evt) {
                displayDataSheet.remove();
            }
        });
        bottonPane.getChildren().clear();
        return displayDashboard;
    }

    /**
     * ajoute la waitingbar dans le panneau du dessous
     * @param displayDashboard
     * @throws IOException
     */
    protected void setWaitingBar(IDisplayDashboard displayDashboard) throws IOException {
        //Chargement de la WaitingBar
        WaitingBarController waitingBarController = new WaitingBarController(displayDashboard);
        FXMLLoader loaderWaitingBar = fxmlLoaderImpl.getFXMLLoader("waitingbar");
        loaderWaitingBar.setController(waitingBarController);
        Node nodeWaitingBar = loaderWaitingBar.load();
        bottonPane.getChildren().add(nodeWaitingBar);
    }

    /**
     * ajoute le paginateur dans le panneau du dessous
     * @throws IOException
     */
    protected void setPaginator() throws IOException {
        //Chargement du paginateur
        FXMLLoader loaderPaginator = fxmlLoaderImpl.getFXMLLoader("paginator");
        loaderPaginator.setController(paginatorController);
        Node nodePaginator = loaderPaginator.load();
        bottonPane.getChildren().add(nodePaginator);
        paginatorController.refresh();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            configurationDefault = configurationBuilder.<ConfigurationDashboardDisplayDefault>get("layout", ConfigurationDashboardDisplayDefault.class);
            //le Loader
            fxmlLoaderImpl = new FXMLLoaderImpl();
            //charge le texte de l'interface
            setLanguage();
            //Layout
            dashboard.setMinSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
            dashboard.setMaxSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
            dashboard.setPrefSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
            // Event sur les boutons
            /**Format grand icon**/
            buttonLarge.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if(mouseEvent.isPrimaryButtonDown()){
                        try {
                            if (configurationDefault.getDefault(Configuration.Paths.DASHBOARD_DISPLAY_DEFAULT_TYPE).equals("author")) {
                                IDisplayDashboard<Author> displayDashboard = setItemAuthor();
                                setWaitingBar(displayDashboard);
                            } else if (configurationDefault.getDefault(Configuration.Paths.DASHBOARD_DISPLAY_DEFAULT_TYPE).equals("book")){
                                IDisplayDashboard<Book> displayDashboard = setItemBook();
                                setWaitingBar(displayDashboard);

                            }
                            setPaginator();
                            configurationDefault.setDefault(Configuration.Paths.DASHBOARD_DISPLAY_DEFAULT_MODE,"item");
                        } catch (Exception e) {
                            showException(e);
                        }
                    }
                }
            });
            /**Format liste**/
            buttonList.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (mouseEvent.isPrimaryButtonDown()){
                        try {
                            if (configurationDefault.getDefault(Configuration.Paths.DASHBOARD_DISPLAY_DEFAULT_TYPE).equals("author")) {
                                IDisplayDashboard<Author> displayDashboard = setListAuthor();
                                setWaitingBar(displayDashboard);
                            } else if (configurationDefault.getDefault(Configuration.Paths.DASHBOARD_DISPLAY_DEFAULT_TYPE).equals("book")){
                                IDisplayDashboard<Book> displayDashboard = null;
                                setWaitingBar(displayDashboard);

                            }
                            setPaginator();
                            configurationDefault.setDefault(Configuration.Paths.DASHBOARD_DISPLAY_DEFAULT_MODE,"list");
                        } catch (Exception e) {
                            showException(e);
                        }
                    }
                }
            });
            /**Affiche les auteurs**/
            buttonAuthor.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (mouseEvent.isPrimaryButtonDown()){
                        try {
                            if (configurationDefault.getDefault(Configuration.Paths.DASHBOARD_DISPLAY_DEFAULT_MODE).equals(("item"))){
                                IDisplayDashboard<Author> displayDashboard = setItemAuthor();
                                setWaitingBar(displayDashboard);
                            } else if (configurationDefault.getDefault(Configuration.Paths.DASHBOARD_DISPLAY_DEFAULT_MODE).equals("list")){
                                IDisplayDashboard<Author> displayDashboard = setListAuthor();
                                setWaitingBar(displayDashboard);
                            }
                            setPaginator();
                            configurationDefault.setDefault(Configuration.Paths.DASHBOARD_DISPLAY_DEFAULT_TYPE,"author");
                        } catch (Exception e) {
                            showException(e);
                        }
                    }
                }
            });
            /**Affiche les livres**/
            buttonBook.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (mouseEvent.isPrimaryButtonDown()){
                        try {
                            if (configurationDefault.getDefault(Configuration.Paths.DASHBOARD_DISPLAY_DEFAULT_MODE).equals("item")){
                                IDisplayDashboard<Book> displayDashboard = setItemBook();
                                setWaitingBar(displayDashboard);
                            } else if (configurationDefault.getDefault(Configuration.Paths.DASHBOARD_DISPLAY_DEFAULT_MODE).equals("list")){
                                IDisplayDashboard<Book> displayDashboard = null;
                                setWaitingBar(displayDashboard);
                            }
                            setPaginator();
                            configurationDefault.setDefault(Configuration.Paths.DASHBOARD_DISPLAY_DEFAULT_TYPE,"book");
                        } catch (Exception e) {
                            showException(e);
                        }

                    }
                }
            });
            //Dashboard par défaut
            IDisplayDashboard displayDashboard = null;
            switch (configurationDefault.getDefault()){
                case "item_author" :
                    displayDashboard = setItemAuthor();
                    buttonAuthor.setSelected(true);
                    break;
                case "list_author" :
                    displayDashboard = setListAuthor();
                    buttonAuthor.setSelected(true);
                    break;
                case "item_book" :
                    displayDashboard = setItemBook();
                    buttonBook.setSelected(true);
                    break;
            }
            setWaitingBar(displayDashboard);
            setPaginator();

            //evenements
            menuExit.setOnAction(new ExitPlatformEvent());

        } catch (Exception e) {
            showException(e);
        }
    }

}
