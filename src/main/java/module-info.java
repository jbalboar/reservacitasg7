module pe.gob.sunat.reservacitasg7 {
    requires javafx.controls;
    requires javafx.fxml;
	requires org.mongodb.driver.sync.client;
	requires org.mongodb.driver.core;
	requires org.mongodb.bson;
	requires lombok;
	requires jasperreports;
	requires java.sql;

    opens pe.gob.sunat.citas to javafx.fxml;
    opens pe.gob.sunat.citas.controller to javafx.fxml;
    
    exports pe.gob.sunat.citas;
    exports pe.gob.sunat.citas.controller;
    exports pe.gob.sunat.citas.bean;
}
