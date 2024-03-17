module org.example.projo {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.projo to javafx.fxml;
    exports org.example.projo;
}