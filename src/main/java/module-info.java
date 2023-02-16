module com.example.agencecolis {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.agencecolis to javafx.fxml;
    exports com.example.agencecolis;
}