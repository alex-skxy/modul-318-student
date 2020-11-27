module alexskxy.transportfinder {
    requires org.junit.jupiter.api;
    requires org.junit.platform.commons;
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires static org.mapstruct.processor;
    requires static lombok;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;
    requires com.google.api.client;

    exports alexskxy.transportfinder.parts;
    exports alexskxy.swisstransport;
    exports alexskxy.swisstransport.entity;
    exports alexskxy.transportfinder;
    opens alexskxy.swisstransport;
    opens alexskxy.swisstransport.entity;
    opens alexskxy.transportfinder;
}