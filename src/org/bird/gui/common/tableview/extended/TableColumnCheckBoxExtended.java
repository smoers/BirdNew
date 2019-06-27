package org.bird.gui.common.tableview.extended;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import org.bird.gui.common.tableview.ColumnFactoryValue;
import org.bird.gui.common.tableview.ConverterTableViewColumn;

public class TableColumnCheckBoxExtended extends TableColumnExtended<CheckBox> {

    public TableColumnCheckBoxExtended(String title, String propertyName, Class<?> transposer) {
        super(title, propertyName, transposer);
    }

    public TableColumnCheckBoxExtended(String title, String propertyName, Class<?> transposer, Object defaultValue) {
        super(title, propertyName, transposer, defaultValue);
    }

    @Override
    public TableColumn<ConverterTableViewColumn, CheckBox> get() {
        TableColumn<ConverterTableViewColumn,CheckBox> tableColumn = new TableColumn<>(title);
        tableColumn.setCellValueFactory(new ColumnFactoryValue<>(propertyName));
        return tableColumn;
    }
}
