module BirdFly {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;
    requires gson;
    requires guava;
    requires org.mongodb.bson;
    requires core;
    exports org.bird.gui to javafx.graphics;
    exports org.bird.gui.controllers to javafx.fxml;
    opens org.bird.gui.controllers to javafx.fxml;
}