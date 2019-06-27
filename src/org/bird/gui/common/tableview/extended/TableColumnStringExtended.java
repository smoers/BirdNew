package org.bird.gui.common.tableview.extended;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.TextFieldTableCell;
import org.bird.gui.common.tableview.ColumnFactoryValue;
import org.bird.gui.common.tableview.ConverterTableViewColumn;

public class TableColumnStringExtended extends TableColumnExtended<String> {

    public TableColumnStringExtended(String title, String propertyName, Class<?> transposer) {
        super(title, propertyName, transposer);
    }

    public TableColumnStringExtended(String title, String propertyName, Class<?> transposer, Object defaultValue) {
        super(title, propertyName, transposer, defaultValue);
    }

    @Override
    public TableColumn<ConverterTableViewColumn, String> get() {
        TableColumn<ConverterTableViewColumn, String> tableColumn = new TableColumn<>(title);
        tableColumn.setCellValueFactory(new ColumnFactoryValue<String>(propertyName));
        tableColumn.setCellFactory(TextFieldTableCell.<ConverterTableViewColumn>forTableColumn());
        return tableColumn;
    }
}
