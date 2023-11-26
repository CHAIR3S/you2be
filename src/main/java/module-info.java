module com.tecnm.you2be {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.desktop;
    requires java.sql;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;
    requires static lombok;
    requires com.fasterxml.jackson.databind;


    opens com.tecnm.you2be to javafx.fxml;
    exports com.tecnm.you2be.youtube.models;
    exports com.tecnm.you2be.youtube.service;
    exports com.tecnm.you2be;
}