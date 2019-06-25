package org.bird.gui.common.tableviewextended;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.TextFieldTableCell;
import org.bird.gui.common.ColumnFactoryValue;
import org.bird.gui.common.ConverterTableViewColumn;

public class TableColumnStringExtended extends TableColumnExtended<String> {

    public TableColumnStringExtended(String title, String propertyName, Class<?> transposer) {
        super(title, propertyName, transposer);
    }

    @Override
    public TableColumn<ConverterTableViewColumn, String> get() {
        TableColumn<ConverterTableViewColumn, String> tableColumn = new TableColumn<>(title);
        tableColumn.setCellValueFactory(new ColumnFactoryValue<String>(propertyName));
        tableColumn.setCellFactory(TextFieldTableCell.<ConverterTableViewColumn>forTableColumn());
        return tableColumn;
    }
}
