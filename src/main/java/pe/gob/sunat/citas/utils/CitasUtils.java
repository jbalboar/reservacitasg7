package pe.gob.sunat.citas.utils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import pe.gob.sunat.citas.App;

public class CitasUtils {
	public static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static Date formatDate(LocalDate datePicker) {
		return Date.from(datePicker.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
}
