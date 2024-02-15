package pe.gob.sunat.citas.controller;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import pe.gob.sunat.citas.bean.EstadisticaBean;
import pe.gob.sunat.citas.dao.HorarioDao;
import pe.gob.sunat.citas.dao.impl.HorarioDaoImpl;

public class EstadisticasController {
	
	@FXML
    private BarChart<String, Number> charMedicos;

    private final HorarioDao horarioDao = new HorarioDaoImpl();
    
	@FXML
	public void initialize() {
		List<EstadisticaBean> lstEstadisticaBean = horarioDao.obtenerEstadisticas();
		
		CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Medicos");
        
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Citas");

        // Crea los datos de muestra
        ObservableList<EstadisticaBean> datos = FXCollections.observableArrayList(lstEstadisticaBean);
        
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Pacientes atendidos");
        
        for (EstadisticaBean dato : datos) {
            series.getData().add(new XYChart.Data<>(dato.getMedico(), dato.getCitas()));
        }
		
        charMedicos.getData().add(series);
	}
}
