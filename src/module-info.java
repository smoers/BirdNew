module BirdFly {
    requires javafx.controls;
    requires javafx.fxml;
    exports org.bird to javafx.graphics;
    exports org.bird.gui.controllers to javafx.fxml;
    opens org.bird.gui.controllers to javafx.fxml;
}