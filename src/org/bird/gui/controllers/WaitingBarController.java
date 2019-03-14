package org.bird.gui.controllers;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import org.bird.gui.common.progress.IProgessListener;
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
    private IProgessListener listener;

    public WaitingBarController(IProgessListener listener) {
        this.listener = listener;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listener.addOnProgressChangeListener(new OnProgressChangeListener() {
            @Override
            public void onProcessChange(OnProgressChangeEvent evt) {
                double tmp = (evt.getValue()*100)/evt.getSize();
                lbl.setText(String.valueOf(tmp));
                waitingBar.setProgress((evt.getValue()*100)/evt.getSize());
                System.out.println(waitingBar.getProgress());
            }
        });
    }

    public ProgressBar getWaitingBar() {
        return waitingBar;
    }
}
