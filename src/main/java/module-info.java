module alexskxy.transportfinder {
    requires org.junit.jupiter.api;
    requires org.junit.platform.commons;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.google.gson;
    requires static org.mapstruct.processor;
    requires static lombok;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;
    requires com.google.api.client;

    exports alexskxy.transportfinder.part;
    exports alexskxy.transportfinder.view;
    exports alexskxy.swisstransport;
    exports alexskxy.swisstransport.entity;
    exports alexskxy.transportfinder;
    opens alexskxy.swisstransport;
    opens alexskxy.swisstransport.entity;
    opens alexskxy.transportfinder;
    opens alexskxy.transportfinder.view;
}