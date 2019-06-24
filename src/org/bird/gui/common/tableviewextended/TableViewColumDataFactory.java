package org.bird.gui.common.tableviewextended;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import org.bird.gui.common.ConverterTableViewColumn;

import java.util.List;
import java.util.function.Consumer;

public class TableViewColumDataFactory {

    private List<ITableColumnExtended> listColumn;
    private List listData;
    private ObservableList<TableColumn<ConverterTableViewColumn,?>> observableListColumn = FXCollections.observableArrayList();
    private ObservableList<ConverterTableViewColumn> observableListData = FXCollections.observableArrayList();

    public TableViewColumDataFactory(List<ITableColumnExtended> listColumn) {
        this.listColumn = listColumn;
        initialize();
    }

    public ObservableList<TableColumn<ConverterTableViewColumn, ?>> getTableColumn(){ return observableListColumn; }

    public ObservableList<ConverterTableViewColumn> getTableData() { return observableListData; }

    private void initialize(){
        listColumn.forEach(new Consumer<ITableColumnExtended>() {
            @Override
            public void accept(ITableColumnExtended iTableColumnExtended) {
                observableListColumn.add(iTableColumnExtended.get());
            }
        });

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
                        ITransposer transposer = transposerFactory.getTransposerInstance(clazz,o);
                        column.set(iTableColumnExtended.getPropertyName(),transposer.getValue(iTableColumnExtended.getPropertyName()));
                    }
                });
            }
        });

    }
}
