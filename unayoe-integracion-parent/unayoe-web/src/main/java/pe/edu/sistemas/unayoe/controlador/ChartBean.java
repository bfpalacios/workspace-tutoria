package pe.edu.sistemas.unayoe.controlador;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.stereotype.Controller;

import pe.edu.sistemas.unayoe.unayoe.bo.IndicadoresBO;
@Controller("chart")
@ViewScoped

public class ChartBean implements Serializable {
	List<IndicadoresBO> listaIndicadores = new ArrayList<IndicadoresBO>();
	   private PieChartModel pieModel1;
	   private PieChartModel pieModel2;
	@PostConstruct
    public void init() {
        createPieModels();
    }
	public PieChartModel getPieModel1() {
        return pieModel1;
    }
	
	  public PieChartModel getPieModel2() {
		return pieModel2;
	}
	public void setPieModel2(PieChartModel pieModel2) {
		this.pieModel2 = pieModel2;
	}
	private void createPieModels() {
	        createPieModel1();
	        createPieModel2();
	    }
	  
	  private void createPieModel1() {
		  System.out.println("listaIndicadores"+listaIndicadores.size());
		  if(listaIndicadores.size()!=0){
	        pieModel1 = new PieChartModel();
	        float i1;
	        float i2;
	        i1=listaIndicadores.get(3).getIndicadorObservados();
	        i2=listaIndicadores.get(4).getIndicadorObservados();
	      pieModel1.set(listaIndicadores.get(3).getIndicador(),i1);	      
	      pieModel1.set(listaIndicadores.get(4).getIndicador(),i2);	      
	        
		  pieModel1.setTitle("GRAFICA DE ASISTENCIA DE SESIONES");
	        pieModel1.setLegendPosition("w");
	        pieModel1.setFill(true);
	        pieModel1.setShowDataLabels(true);
	      
	    
	  }
		  
		
	    }
	  
	  private void createPieModel2() {
		  System.out.println("listaIndicadores"+listaIndicadores.size());
		  if(listaIndicadores.size()!=0){
	        pieModel2 = new PieChartModel();
	        float i1;
	        float i2;
	        float i3;
	        i1=listaIndicadores.get(6).getIndicadorObservados();
	        i2=listaIndicadores.get(7).getIndicadorObservados();
	        i3=listaIndicadores.get(8).getIndicadorObservados();
	      pieModel2.set(listaIndicadores.get(6).getIndicador(),i1);	      
	      pieModel2.set(listaIndicadores.get(7).getIndicador(),i2);	      
	      pieModel2.set(listaIndicadores.get(8).getIndicador(),i3);	
	      
		  pieModel2.setTitle("GRAFICO DE TAREAS");
	        pieModel2.setLegendPosition("w");
	        pieModel2.setFill(true);
	        pieModel2.setShowDataLabels(true);
	    
	  }
	    }
	public List<IndicadoresBO> getListaIndicadores() {
		return listaIndicadores;
	}
	public void setListaIndicadores(List<IndicadoresBO> listaIndicadores) {
		this.listaIndicadores = listaIndicadores;
		createPieModel1();
		createPieModel2();
	}
	
}
