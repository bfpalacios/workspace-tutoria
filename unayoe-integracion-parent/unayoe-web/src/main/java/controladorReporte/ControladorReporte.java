package controladorReporte;

import java.io.File;
import java.util.ArrayList;  
import java.util.Collection;  
import java.util.HashMap;  
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;  
import net.sf.jasperreports.engine.JasperExportManager;  
import net.sf.jasperreports.engine.JasperFillManager;  
import net.sf.jasperreports.engine.JasperPrint;  
import net.sf.jasperreports.engine.JasperReport;  
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class ControladorReporte {
	
	private String nombreReporte="";

	public String getNombreReporte() {
		return nombreReporte;
	}

	public void setNombreReporte(String nombreReporte) {
		this.nombreReporte = nombreReporte;
	}

	public void generarReporteHorarioDocente(Map parametros ,Collection campos ){
		JRBeanCollectionDataSource dataSource;
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		Map pars = new HashMap();
		String ruta_arc="classpath:reportes/";
		pars=parametros;
		//pars.put("LOGO_URL", "logo.gif");
		//pars.put("P_TITULO", "Reporte de universidades inscritas");
		//pars.put("P_SUBTITULO", "Región Nor-Este");
		try{
			FacesContext contexto =FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) contexto.getExternalContext().getResponse();
			HttpServletRequest request = (HttpServletRequest)contexto.getExternalContext().getRequest();
			response.setContentType("application/pdf");
    	
			File archivo = ResourceUtils.getFile((new StringBuilder(ruta_arc)).append(nombreReporte).append(".jasper").toString());
			if(archivo.exists())
				System.out.println("Existe");
			/***************************/
			//JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
			//1-Llenar el datasource con la informacion de la base de datos o de donde este, en este caso "hardcode"
			Collection lista =campos;
			dataSource = new JRBeanCollectionDataSource(lista);
			jasperReport =(JasperReport) JRLoader.loadObject(archivo);
			//3-Llenamos el reporte con la información de la coleccion y parámetros necesarios para la consulta
			jasperPrint = JasperFillManager.fillReport(jasperReport, pars, dataSource);
			//4-Exportamos el reporte a pdf y lo guardamos en disco
        
			//File tempFile =  tempFile = File.createTempFile("tmp", ".pdf");
			//FileOutputStream fos  = new FileOutputStream(tempFile);
			// JasperExportManager.exportReportToPdfStream( jasperPrint,fos);
         
			JRExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
			//exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream(nombreReporte + ".pdf")); 
			exporter.exportReport();     
			System.out.println("Done!");
		}catch (Exception e){
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	public void generarReporteNotasAlumno(Map<String,Object> parametros, ArrayList<Object> campos){
		JRBeanCollectionDataSource dataSource;
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		Map<String,Object> pars = new HashMap<String,Object>();
		String ruta_arc="classpath:reportes/";
		pars = parametros;
		
		try{
			FacesContext contexto = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) contexto.getExternalContext().getResponse();			
			response.setContentType("application/pdf");
    	
			String var = ruta_arc;
			String var1 = nombreReporte;
			String var2 = ".jasper";					
			
			File archivo = ResourceUtils.getFile((new StringBuilder(ruta_arc)).append(nombreReporte).append(".jasper").toString());
			if(archivo.exists()){
				ArrayList<Object> lista = campos;
				dataSource = new JRBeanCollectionDataSource(lista);
				jasperReport =(JasperReport) JRLoader.loadObject(archivo);			
				jasperPrint = JasperFillManager.fillReport(jasperReport, pars, dataSource);			
				JRPdfExporter exporter = new JRPdfExporter();
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
				//exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream(nombreReporte + ".pdf")); 
				//File pdf = File.createTempFile("e://output", ".pdf");
				//JasperExportManager.exportReportToPdfStream(jasperPrint, new FileOutputStream(pdf));				
				exporter.exportReport();     
				System.out.println("Done!");
			}			
		}catch (Exception e){
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
