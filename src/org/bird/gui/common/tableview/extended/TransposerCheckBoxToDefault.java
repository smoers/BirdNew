package org.bird.gui.common.tableview.extended;

import javafx.scene.control.CheckBox;

public class TransposerCheckBoxToDefault extends Transposer<CheckBox> {

    public TransposerCheckBoxToDefault(Object object) {
        super(object);
    }

    @Override
    public CheckBox getValue(String propertyName) {
        CheckBox checkBox = new CheckBox();
        checkBox.setSelected((boolean) object);
        return checkBox;
    }
}
