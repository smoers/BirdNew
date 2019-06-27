package org.bird.gui.common.tableview.extended;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import org.bird.gui.common.tableview.ConverterTableViewColumn;

import java.util.List;
import java.util.function.Consumer;

/**
 * Cette classe construit les listes à passer à un objet TableView.
 * La liste des colonnes et la liste des données ConverterRableViewColumn
 */
public class TableViewColumDataFactory {

    private List<ITableColumnExtended> listColumn;
    private List listData;
    private ObservableList<TableColumn<ConverterTableViewColumn,?>> observableListColumn = FXCollections.observableArrayList();
    private ObservableList<ConverterTableViewColumn> observableListData = FXCollections.observableArrayList();

    /**
     * Constructeur
     * @param listColumn
     * @param listData
     */
    public TableViewColumDataFactory(List<ITableColumnExtended> listColumn, List listData) {
        this.listColumn = listColumn;
        this.listData = listData;
        initialize();
    }

    /**
     * Liste des Colonnes
     * @return
     */
    public ObservableList<TableColumn<ConverterTableViewColumn, ?>> getTableColumn(){ return observableListColumn; }

    /**
     * Liste des données
     * @return
     */
    public ObservableList<ConverterTableViewColumn> getTableData() { return observableListData; }

    /**
     * Initialisation de l'objet
     */
    private void initialize(){
        //Construction de la liste des colonnes
        listColumn.forEach(new Consumer<ITableColumnExtended>() {
            @Override
            public void accept(ITableColumnExtended iTableColumnExtended) {
                observableListColumn.add(iTableColumnExtended.get());
            }
        });

        //Construction de la liste des données de type ConverterTableViewColumn
        TransposerFactory transposerFactory = TransposerFactory.getInstance();
        listData.forEach(new Consumer() {
            @Override
            public void accept(Object o) {
                ConverterTableViewColumn column = new ConverterTableViewColumn(o);
                listColumn.forEach(new Consumer<ITableColumnExtended>() {
                    @Override
                    public void accept(ITableColumnExtended iTableColumnExtended) {
                        Class<ITransposer> clazz = iTableColumnExtended.getTransposer();
                        String propertyName = iTableColumnExtended.getPropertyName();
                        Object data = o;
                        //Si une valeur par défaut a été preciser elle sera passée au TransposerFactory comme valeur du paramètre constructeur
                        if ( null != iTableColumnExtended.getDefaultValue())
                            data = iTableColumnExtended.getDefaultValue();
                        ITransposer transposer = transposerFactory.getTransposerInstance(clazz,data);
                        if (null != transposer) {
                            column.set(iTableColumnExtended.getPropertyName(), transposer.getValue(iTableColumnExtended.getPropertyName()));
                        }
                    }
                });
                observableListData.add(column);
            }
        });

    }
}
