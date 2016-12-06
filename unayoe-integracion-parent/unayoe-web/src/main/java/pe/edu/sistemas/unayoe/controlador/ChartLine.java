package pe.edu.sistemas.unayoe.controlador;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import pe.edu.sistemas.unayoe.unayoe.bo.IndicadoresBO;
@Controller("chartLine")
@ViewScoped
public class ChartLine implements Serializable {
	List<IndicadoresBO> listaIndicadores = new ArrayList<IndicadoresBO>();
	private LineChartModel lineModel1;
	LineChartSeries series1;
	LineChartModel model;
	@PostConstruct
    public void init() {
        createLineModels();
    }
    
	public LineChartSeries getSeries1() {
		return series1;
	}

	public void setSeries1(LineChartSeries series1) {
		this.series1 = series1;
	}

	public LineChartModel getModel() {
		return model;
	}

	public void setModel(LineChartModel model) {
		this.model = model;
	}

	public LineChartModel getLineModel1() {
        return lineModel1;
    }

	

	private void createLineModels() {
		System.out.println("2");
		lineModel1 = initLinearModel();
		if(lineModel1!=null){
		System.out.println("4");       
        lineModel1.setTitle("Ciclo 2016-2--Indicadores del Sistema");
        System.out.println("44"); 
        lineModel1.setLegendPosition("e");
        Axis yAxis = lineModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(20);
		}
    }
     
	private LineChartModel initLinearModel() {
		 if(listaIndicadores.size()!=0){
		System.out.println("3");
		System.out.println("listaIndicadores"+listaIndicadores.size());
         model = new LineChartModel();
         series1 = new LineChartSeries();
        series1.setLabel("Series 1");
        float i1;
        i1=listaIndicadores.get(0).getIndicadorObservados();
        
        
        series1.set(1,2);
        series1.set(2, 1);
        series1.set(3, 3);
        series1.set(4, 6);
        series1.set(5, 8);
 
       
        model.addSeries(series1);
		  
        return model;}
		return null;
    }
    public List<IndicadoresBO> getListaIndicadores() {
		return listaIndicadores;
	}
	public void setListaIndicadores(List<IndicadoresBO> listaIndicadores) {
		this.listaIndicadores = listaIndicadores;
		createLineModels();
	    System.out.println("5");
	}

}
