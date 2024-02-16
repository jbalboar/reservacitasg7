package pe.gob.sunat.citas.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.Document;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import pe.gob.sunat.citas.App;
import pe.gob.sunat.citas.bean.HorariosBean;

public class CitasUtils {	
	public static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static Date formatDate(LocalDate datePicker) {
		return Date.from(datePicker.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
	
	public static List<String> getHorarioLibre(Document document){
		List<String> lstHorarios = Arrays.asList("08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00");
		
		if (document != null) {
            HorariosBean bean =  MongoUtils.documentToJavaBean(document, HorariosBean.class);      
            return lstHorarios.stream().filter(item -> !bean.getHorarios().contains(item)).collect(Collectors.toList());       
        }
		
		return lstHorarios;
	}
	
	public static boolean esDiferenteNuloVacio(String valor) {
		if(valor!=null && !valor.isEmpty())
			return true;
		
		return false;
		
	}

	public static String dateToString(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
	}
	
	public static String documentToString(Document dato) {
		
		String codigo = dato.getString("codigo");
		String descripcion = dato.getString("descripcion");
		
        return codigo.concat("-").concat(descripcion);
	}
}
