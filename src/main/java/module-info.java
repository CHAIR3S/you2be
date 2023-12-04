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
    requires javafx.graphics;


    requires kernel;
    requires layout;
    requires org.slf4j;
    requires org.apache.logging.log4j;

    requires io;



    opens com.tecnm.you2be to javafx.fxml;
    exports com.tecnm.you2be.youtube.models;
    exports com.tecnm.you2be.youtube.service;
    exports com.tecnm.you2be;
}