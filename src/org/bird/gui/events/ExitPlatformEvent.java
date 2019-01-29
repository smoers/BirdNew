package org.bird.gui.events;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import org.bird.gui.common.dialog.DialogAlert;
import org.bird.gui.common.dialog.DialogExtended;
import org.bird.i18n.InternationalizationBuilder;
import org.bird.i18n.InternationalizationBundle;

/**
 * Event destiné à fermer l'application
 */
public class ExitPlatformEvent implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent actionEvent) {
        InternationalizationBuilder internationalizationBuilder = InternationalizationBuilder.getInstance();
        InternationalizationBundle bundle = internationalizationBuilder.getInternationalizationBundle(DialogExtended.class);
        String title = bundle.getString("Attention");
        String message = bundle.getString("Message001");
        DialogAlert dialogAlert = new DialogAlert(DialogExtended.TYPE.BUTTON_YES_NO,title, message,bundle);
        ButtonType response = dialogAlert.showAndWait().get();

        if (response.getButtonData() == ButtonBar.ButtonData.YES)
            Platform.exit();
    }
}
