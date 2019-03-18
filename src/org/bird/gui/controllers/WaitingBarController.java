package org.bird.gui.controllers;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import org.bird.gui.common.progress.IOnProgessChangeListener;
import org.bird.gui.events.OnProgressChangeEvent;
import org.bird.gui.listeners.OnProgressChangeListener;

import java.net.URL;
import java.util.ResourceBundle;

public class WaitingBarController implements Initializable {

    @FXML
    private ProgressBar waitingBar;
    @FXML
    private AnchorPane progressBarPane;
    @FXML
    private Label lbl;
    private IOnProgessChangeListener listener;

    public WaitingBarController(IOnProgessChangeListener listener) {
        this.listener = listener;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ProgressService service = new ProgressService(listener);
        waitingBar.progressProperty().bind(service.progressProperty());
        service.start();
    }

    public ProgressBar getWaitingBar() {
        return waitingBar;
    }

    private class ProgressService extends Service<Void>{

        private IOnProgessChangeListener listener;

        public ProgressService(IOnProgessChangeListener listener) {
            this.listener = listener;
        }

        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    listener.addOnProgressChangeListener(new OnProgressChangeListener(){

                        @Override
                        public void onProcessChange(OnProgressChangeEvent evt) {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    updateProgress(evt.getValue(), evt.getSize());
                                }
                            });

                        }
                    });
                    return null;
                }
            };
        }
    }
}
