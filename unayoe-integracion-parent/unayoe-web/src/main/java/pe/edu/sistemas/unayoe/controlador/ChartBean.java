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
	@PostConstruct
    public void init() {
        createPieModels();
    }
	public PieChartModel getPieModel1() {
        return pieModel1;
    }
	  private void createPieModels() {
	        createPieModel1();

	    }
	  
	  private void createPieModel1() {
		  System.out.println("listaIndicadores"+listaIndicadores.size());
		  if(listaIndicadores.size()!=0){
	        pieModel1 = new PieChartModel();
	         for(int i=0;i<listaIndicadores.size();i++)
	         {  pieModel1.set(listaIndicadores.get(i).getIndicador(), listaIndicadores.get(i).getIndicadorObservados());	      
		  }
	        
		  pieModel1.setTitle("GRAFICO ESTADISTICA MODULO DE OBSERVADOS");
	        pieModel1.setLegendPosition("w");
	  }
	    }
	public List<IndicadoresBO> getListaIndicadores() {
		return listaIndicadores;
	}
	public void setListaIndicadores(List<IndicadoresBO> listaIndicadores) {
		this.listaIndicadores = listaIndicadores;
		createPieModel1();
	}
	
}
