module com.tecnm.you2be {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;


    opens com.tecnm.you2be to javafx.fxml;
    exports com.tecnm.you2be;
}