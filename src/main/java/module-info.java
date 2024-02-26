module com.example.pfapplication {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.xml;

    opens presentation to javafx.fxml;
    exports presentation;

}