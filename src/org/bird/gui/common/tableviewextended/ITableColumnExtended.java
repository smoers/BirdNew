package org.bird.gui.common.tableviewextended;

import javafx.scene.control.TableColumn;
import org.bird.gui.common.ConverterTableViewColumn;

public interface ITableColumnExtended<T> {

    public String getTitle();

    public String getPropertyName();

    public TableColumn<ConverterTableViewColumn,T> get();
}
