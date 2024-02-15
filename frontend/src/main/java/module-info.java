module org.example.frontend {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens org.example.frontend to javafx.fxml;
    exports org.example.frontend;
}