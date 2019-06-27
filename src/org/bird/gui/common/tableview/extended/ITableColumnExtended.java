package org.bird.gui.common.tableview.extended;

import javafx.scene.control.TableColumn;
import org.bird.gui.common.tableview.ConverterTableViewColumn;

public interface ITableColumnExtended<T> {

    public Object getDefaultValue();

    public String getTitle();

    public String getPropertyName();

    public TableColumn<ConverterTableViewColumn,T> get();

    public Class getTransposer();
}
