package org.bird.gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.bird.configuration.Configuration;
import org.bird.configuration.ConfigurationBuilder;
import org.bird.configuration.exceptions.ConfigurationException;
import org.bird.db.query.Paginator;
import org.bird.gui.events.ExitPlatformEvent;
import org.bird.gui.events.OnLeftClickEvent;
import org.bird.gui.listeners.OnLeftClickListener;
import org.bird.gui.resources.images.ImageProvider;
import org.bird.gui.resources.layout.*;
import org.bird.i18n.InternationalizationBuilder;
import org.bird.i18n.InternationalizationBundle;
import org.bird.i18n.InternationalizationController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DashboardController extends InternationalizationController implements Initializable {

    @FXML
    private MenuItem menuExit;
    @FXML
    private Menu menuFile;
    @FXML
    private MenuBar menuBar;
    @FXML
    private FlowPane itemsContainer;
    @FXML
    private BorderPane dashboard;
    @FXML
    private ToolBar toolbar;
    @FXML
    private Button buttonLarge;
    @FXML
    private Button buttonList;
    //
    private final InternationalizationBuilder internationalizationBuilder = InternationalizationBuilder.getInstance();
    private InternationalizationBundle internationalizationBundle;
    private ConfigurationBuilder configurationBuilder = ConfigurationBuilder.getInstance();
    private Configuration configurationLayout;
    private VBox selectedContainer = null;

    /**
     * Contructeur
     */
    public DashboardController() throws ConfigurationException {
        internationalizationBundle = internationalizationBuilder.getInternationalizationBundle(getClass());
        configurationLayout = configurationBuilder.get("layout");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //charge le texte de l'interface
        setLanguage();
        //Layout
        dashboard.setMinSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
        dashboard.setMaxSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
        dashboard.setPrefSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
        //Configuration du layout de la toolbar
        ArrayList<LayoutInterface> nodeLayouts = new ArrayList<>();
        LayoutParameters layoutParameters = new LayoutParameters();
        layoutParameters.put(LayoutParameters.SELECTOR, "toolbar");
        try {
            layoutParameters.put(LayoutParameters.IFTEXT, configurationLayout.get("layout.toolbar.iftext").getAsBoolean());
        } catch (ConfigurationException e) {
            e.getI18nMessage();
        }
        layoutParameters.put(LayoutParameters.CHILDREN, nodeLayouts);
        nodeLayouts.add(new ButtonLayout(buttonLarge, layoutParameters));
        nodeLayouts.add(new ButtonLayout(buttonList,layoutParameters));
        ToolBarLayout toolBarLayout = new ToolBarLayout(toolbar, layoutParameters);
        toolBarLayout.apply();

        itemsContainer.getStyleClass().add("items_container");

        //evenements
        menuExit.setOnAction(new ExitPlatformEvent());

        //Charge le dashboard
        int ii = 100;
        for(int i=1;i<=ii;i++) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/org/bird/gui/resources/views/itemDashboard.fxml"));
                Node node = loader.load();
                ItemDashboardController item = (ItemDashboardController) loader.getController();
                item.addOnLeftClickListener(new OnLeftClickListener() {
                    @Override
                    public void onLeftClick(OnLeftClickEvent evt) {
                        VBox container = (VBox) evt.getSource();
                        if(evt.getClickCount() == 1){
                            if ((selectedContainer != null)) {
                                selectedContainer.getStyleClass().clear();
                                selectedContainer.getStyleClass().add("item_container");
                            }
                            container.getStyleClass().add("item_container_active");
                            selectedContainer = container;
                        }
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
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
