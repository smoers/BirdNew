package org.bird.gui.common.tableviewextended;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import org.bird.gui.common.ColumnFactoryValue;
import org.bird.gui.common.ConverterTableViewColumn;

public class TableColumnCheckBoxExtended extends TableColumnExtended<CheckBox> {

    public TableColumnCheckBoxExtended(String title, String propertyName, Class<?> transposer) {
        super(title, propertyName, transposer);
    }

    @Override
    public TableColumn<ConverterTableViewColumn, CheckBox> get() {
        TableColumn<ConverterTableViewColumn,CheckBox> tableColumn = new TableColumn<>(title);
        tableColumn.setCellValueFactory(new ColumnFactoryValue<>(propertyName));
        return tableColumn;
    }
}
