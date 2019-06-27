package org.bird.gui.controllers.display;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import org.bird.configuration.exceptions.ConfigurationException;
import org.bird.db.models.Author;
import org.bird.db.models.Book;
import org.bird.db.query.Paginator;
import org.bird.gui.common.tableview.ColumnFactoryValue;
import org.bird.gui.common.tableview.ConverterTableViewColumn;
import org.bird.gui.common.FXMLLoaderImpl;
import org.bird.gui.common.ShowException;
import org.bird.gui.controllers.ListDashboardController;
import org.bird.gui.events.OnSelectedEvent;
import org.bird.gui.resources.images.ImageProvider;

import java.io.IOException;
import java.util.StringJoiner;
import java.util.function.Consumer;

public class DisplayDashboardListBook extends DisplayDashboardList<Book> {

    private TableView<ConverterTableViewColumn> tableView;

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
            TableColumn<ConverterTableViewColumn, ImageView> imageCol = new TableColumn<>("Images");
            imageCol.setCellValueFactory(new ColumnFactoryValue<ImageView>("image"));
            /**Titre du cycle**/
            TableColumn<ConverterTableViewColumn,String> cycleTitle = new TableColumn<>("Cycle Title");
            cycleTitle.setCellValueFactory(new ColumnFactoryValue<String>("cycletitle"));
            /**Volume**/
            TableColumn<ConverterTableViewColumn,Integer> volumeNumber = new TableColumn<>("Volume Number");
            volumeNumber.setCellValueFactory(new ColumnFactoryValue<Integer>("volumenumber"));
            /**Titre du cycle**/
            TableColumn<ConverterTableViewColumn,String> bookTitle = new TableColumn<>("Book Title");
            bookTitle.setCellValueFactory(new ColumnFactoryValue<String>("booktitle"));
            /**le Nom des auteurs**/
            TableColumn<ConverterTableViewColumn, String> fullName = new TableColumn<>("Authors");
            fullName.setCellValueFactory(new ColumnFactoryValue<String>("authors"));

            //Récupère le tableau
            tableView = controller.getTableView();
            //On charge les colonnes dans le tableau
            tableView.getColumns().setAll(imageCol,cycleTitle,volumeNumber,bookTitle,fullName);
            //on defini l'event
            tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ConverterTableViewColumn>() {
                @Override
                public void changed(ObservableValue<? extends ConverterTableViewColumn> observableValue, ConverterTableViewColumn oldVal, ConverterTableViewColumn newVal) {
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
    public ConverterTableViewColumn getConverterTableViewColumn(Book item) throws ConfigurationException {
        ConverterTableViewColumn column = new ConverterTableViewColumn(item);
        ImageProvider provider = new ImageProvider(item.getPicture());
        ImageView imageView = provider.getImageView();
        imageView.setPreserveRatio(true);
        Double fitHeight = configuration.get("layout.list_dashboard.image_view_height").getAsDouble();
        imageView.setFitHeight(fitHeight);
        column.<ImageView>set("image", imageView);
        column.<String>set("cycletitle", item.getCycle().getTitle());
        column.<Integer>set("volumenumber",item.getCycle().getVolumeNumber());
        column.<String>set("booktitle",item.getTitle());
        StringJoiner joiner = new StringJoiner(", ");
        item.getCycle().getAuthors().forEach(new Consumer<Author>() {
            @Override
            public void accept(Author author) {
                joiner.add(author.getFullName());
            }
        });
        column.<String>set("authors",joiner.toString());
        return column;
    }
}
