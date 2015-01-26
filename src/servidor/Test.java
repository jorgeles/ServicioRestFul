package servidor;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class Test {

	public static void main(String[] args) {
		ClientConfig config = new DefaultClientConfig();
		Client cliente = Client.create(config);
		Local input = new Local();
		Comentario com = new Comentario();
		com.texto="JaaajjjjjjiiiiiiijjjjjaaaaaaaJU";
		com.id=301;
		input.comentarios.add(com);
		input.id=501;
		input.descripcion="Guapaaaaa";
		WebResource servicio = cliente.resource(getBaseURI());
		// Conseguir XML para la aplicacion
		String xmlFile = servicio.path("rest").path("insertarcomentario")
				.accept(MediaType.APPLICATION_XML).post(String.class,input);
		
		System.out.println(xmlFile);
		
		/*//Se crea un SAXBuilder para poder parsear el archivo
		SAXBuilder builder = new SAXBuilder();
		try {
			//Se crea el documento a traves del archivo
			Document document = (Document) builder.build( new StringReader(xmlFile) );
			//Se obtiene la raiz 'tables'
	        Element rootNode = document.getRootElement();
	        //Obtenemos el hijo todo
	        List list = rootNode.getChildren("descripcion");
	        //Lo convertimos en un elemento
	        Element descripcion = (Element)list.get(0);
	        //Obtenemos el texto del elemento y lo imprimimos por pantalla
	        System.out.println(descripcion.getText());
	        //Igual pero con el hijo resumen
	        list = rootNode.getChildren("resumen");
	        descripcion = (Element)list.get(0);
	        System.out.println(descripcion.getText());
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/Practica3").build();
	}
}
