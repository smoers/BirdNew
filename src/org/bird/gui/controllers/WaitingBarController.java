package org.bird.gui.controllers;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import org.bird.gui.common.progress.IOnWaitingBarListener;
import org.bird.gui.events.OnProcessEvent;
import org.bird.gui.events.OnProgressChangeEvent;
import org.bird.gui.listeners.OnProcessListener;
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
    private IOnWaitingBarListener listener;

    public WaitingBarController(IOnWaitingBarListener listener) {
        this.listener = listener;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        progressBarPane.visibleProperty().set(false);
        ProgressService service = new ProgressService(listener);
        waitingBar.progressProperty().bind(service.progressProperty());
        service.start();
    }

    public ProgressBar getWaitingBar() {
        return waitingBar;
    }

    /**
     * Ce service exécute dans un thread l'affichage de la WaitingBar
     */
    private class ProgressService extends Service<Void>{

        private IOnWaitingBarListener listener;

        public ProgressService(IOnWaitingBarListener listener) {
            this.listener = listener;
        }

        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    //ecoute la fin et le début du processus de chargement
                    listener.addOnProcessListener(new OnProcessListener() {
                        @Override
                        public void onProcess(OnProcessEvent evt) {
                            if (evt.isStarted())
                                progressBarPane.visibleProperty().set(true);
                            else
                                progressBarPane.visibleProperty().set(false);
                        }
                    });
                    //ecoute l'évolution du chargement
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
