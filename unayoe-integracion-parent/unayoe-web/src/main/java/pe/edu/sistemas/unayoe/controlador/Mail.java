package pe.edu.sistemas.unayoe.controlador;
import java.util.Properties;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Controller;

import javax.mail.*;

@Controller("mail")
@ViewScoped
public class Mail {
	public String pass;
	public String name;
	public String mailAddress;
	public String nombrecompleto;

	public String getNombrecompleto() {
		return nombrecompleto;
	}
	public void setNombrecompleto(String nombrecompleto) {
		this.nombrecompleto = nombrecompleto;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public void sendMail(){
		final String usuario ="tallerproy2@gmail.com";
		final String password ="tallerproyectos2";
		Properties properties=new Properties();
		properties.put("mail.smtp.auth","true");
		properties.put("mail.smtp.starttls.enable","true");
		properties.put("mail.smtp.host","smtp.gmail.com");
		properties.put("mail.smtp.port","587");
		
		Session session = Session.getInstance(properties,
				new javax.mail.Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(usuario,password);
			}
		});
		try {
			
			Message msj=new MimeMessage(session);
			msj.setFrom(new InternetAddress("aa@aa.com"));
			msj.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailAddress));
			msj.setSubject("!Gracias por Registrarte - Sistema Tutoria FISI");
			msj.setText("Hola " + nombrecompleto+",\n"+"Tus datos para ingresar al sistema son los siguientes: \n"+"Usuario: "+name+"\n"+"Contraseña :"+pass+"\n"+"http://sistemas.unmsm.edu.pe/");
		Transport.send(msj);
		}catch(MessagingException ex){
			
			throw new RuntimeException(ex);
		}
		}
	}
	
	

