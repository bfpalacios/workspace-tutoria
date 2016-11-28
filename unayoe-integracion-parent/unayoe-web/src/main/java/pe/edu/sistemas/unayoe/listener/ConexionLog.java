package pe.edu.sistemas.unayoe.listener;




import java.net.URL;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class ConexionLog {
	
	
	static Logger log=Logger.getLogger(ConexionLog.class);
    static URL url=ConexionLog.class.getResource("log4j.properties");
    
    public void conectar(int num) {
        PropertyConfigurator.configure(url);
        if(num==0){
            //conexion fue exitosa
            log.info("La conexion fue exitosa");
        }else if(num<0){
            //warning, un error puede suceder durante la ejecucion
            log.warn("Posiblemente suceda un Error");
        }else{
            // error fatal
            log.fatal("Ocurrio un error fatal");
        }
    }
    
    /**
     * Metodo para registrar un mensaje en el Log
     * Seleccione el tipo de error
     * - 0: Mensaje informativo
     * - 1: Mensaje de Advertencia
     * - 2: Mensaje de Error
     * - 4: Mensaje de Error fatal
     * @param numero
     * @param mensaje
     */
    public  static void registrarMensaje(int numero, String mensaje){
      
        PropertyConfigurator.configure(url);
        System.out.println("entre");
        System.out.println("numb"+numero);
        System.out.println("msj"+mensaje);
        switch(numero){
      
            case 0: //Mensaje informativo
            	log.info(mensaje);
                break;
            case 1: //Mensaje de Warning
                log.warn(mensaje);
                break;
            case 2: //Mensaje de Warning
                log.warn(mensaje);
                break;
            case 3: //Mensaje de error
                log.error(mensaje);
                break;
            case 4:
                //Mensaje de error fatal
                log.fatal(mensaje);
         System.out.println("Error el numero indicado es incorrecto");
         break;
        }
    }
   


}
