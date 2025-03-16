module com.example.javaprojecttest {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javaprojecttest to javafx.fxml;
    exports com.example.javaprojecttest;
}