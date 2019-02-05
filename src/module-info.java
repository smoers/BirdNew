module org.bird {
    exports org.bird;
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;
    requires gson;
    requires guava;
    requires core;
    requires mongo.java.driver;
    requires java.sql;
    exports org.bird.gui to javafx.graphics;
    exports org.bird.gui.controllers to javafx.fxml;
    opens org.bird.gui.controllers to javafx.fxml;
    opens org.bird.db.models to core;
    opens org.bird.db.models.metadata to core;
}