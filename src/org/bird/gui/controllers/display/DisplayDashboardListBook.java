package org.bird.gui.controllers.display;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import org.bird.configuration.exceptions.ConfigurationException;
import org.bird.db.models.Book;
import org.bird.db.query.Paginator;
import org.bird.gui.common.ConverterTableViewColumnSix;
import org.bird.gui.common.FXMLLoaderImpl;
import org.bird.gui.common.ShowException;
import org.bird.gui.controllers.ListDashboardController;
import org.bird.gui.events.OnSelectedEvent;

import java.io.IOException;

public class DisplayDashboardListBook extends DisplayDashboardList<Book> {

    private TableView<ConverterTableViewColumnSix<ImageView, Void, Void, Void, Void, Void>> tableView;

    /**
     * Constructeur
     *
     * @param itemsContainer
     */
    public DisplayDashboardListBook(ObservableList itemsContainer) throws ConfigurationException {
        super(itemsContainer);
    }

    @Override
    public void display(Paginator<Book> paginator) throws IOException {
        try {
            //Vide le container
            itemsContainer.clear();
            //instance le controller pour la vue
            ListDashboardController controller = new ListDashboardController();
            //Crée la vue
            FXMLLoaderImpl fxmlLoader = new FXMLLoaderImpl();
            FXMLLoader _loader = fxmlLoader.getFXMLLoader(this.getClass());
            _loader.setController(controller);
            Node node = _loader.load();
            //Ajoute la vue dans le container du dashboard
            itemsContainer.add(node);
            //Ajoute les colonnes pour la liste des livres
            /**Pochette**/
            TableColumn<ConverterTableViewColumnSix<ImageView, Void, Void, Void, Void, Void>, ImageView> imageCol = new TableColumn<>("Images");
            imageCol.setCellValueFactory(new PropertyValueFactory<>("objectColumn01"));
            /**Titre du cycle**/
            TableColumn<ConverterTableViewColumnSix<ImageView, Void, Void, Void, Void, Void>,String> cycleTitle = new TableColumn<>("Cycle Title");
            cycleTitle.setCellValueFactory(new PropertyValueFactory<>("StringColumn01"));
            /**Volume**/
            TableColumn<ConverterTableViewColumnSix<ImageView, Void, Void, Void, Void, Void>,Integer> volumeNumber = new TableColumn<>("Volume Number");
            volumeNumber.setCellValueFactory(new PropertyValueFactory<>("integerColumn01"));
            /**Titre du cycle**/
            TableColumn<ConverterTableViewColumnSix<ImageView, Void, Void, Void, Void, Void>,String> bookTitle = new TableColumn<>("Book Title");
            bookTitle.setCellValueFactory(new PropertyValueFactory<>("StringColumn02"));
            /**Nom de l'auteur**/
            TableColumn<ConverterTableViewColumnSix<ImageView, Void, Void, Void, Void, Void>, String> lastNameCol = new TableColumn<>("LastName");
            lastNameCol.setCellValueFactory(new PropertyValueFactory<>("stringColumn03"));
            /**Prénom de l'auteur**/
            TableColumn<ConverterTableViewColumnSix<ImageView, Void, Void, Void, Void, Void>, String> firstNameCol = new TableColumn<>("FirstName");
            firstNameCol.setCellValueFactory(new PropertyValueFactory<>("stringColumn04"));
            //Récupère le tableau
            tableView = controller.getTableView();
            //On charge les colonnes dans le tableau
            tableView.getColumns().setAll(imageCol,cycleTitle,volumeNumber,bookTitle,lastNameCol,firstNameCol);
            //on defini l'event
            tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ConverterTableViewColumnSix<ImageView, Void, Void, Void, Void, Void>>() {
                @Override
                public void changed(ObservableValue<? extends ConverterTableViewColumnSix<ImageView, Void, Void, Void, Void, Void>> observableValue, ConverterTableViewColumnSix<ImageView, Void, Void, Void, Void, Void> oldVal, ConverterTableViewColumnSix<ImageView, Void, Void, Void, Void, Void> newVal) {
                    Book book = (Book) newVal.getSource();
                    notifyOnSelectedListener(new OnSelectedEvent<Book>(this, book));
                }
            });
            //On demande au controller de charger la traduction des titres des colonnes
            controller.setLanguage();
            //Instance & start le service qui va se charger d'afficher la liste des auteurs
            DisplayService service = new DisplayService(tableView, paginator);
            service.start();
        } catch (ConfigurationException e){
            ShowException showException = new ShowException(e);
            showException.show("ConfigurationException");
        }
    }

    @Override
    public ConverterTableViewColumnSix<ImageView, Void, Void, Void, Void, Void> getConverterTableViewColumn(Book item) throws ConfigurationException {
        return null;
    }
}
