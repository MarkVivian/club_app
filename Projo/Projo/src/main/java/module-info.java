module org.example.projo {
    requires javafx.controls;
    requires javafx.fxml;
    requires json;


    opens org.example.projo to javafx.fxml;
    exports org.example.projo;
}