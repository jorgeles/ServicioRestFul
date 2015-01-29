package servidor;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;


/*
 * @author Jorge Leon Fernandez
 * Inserta el local que se pasa comom parametro
 * Devuelve true si se ha insertado correctamente
 */
@Path("/insertar")
public class Insertar {
	// Este metodo se llamara si existe una peticion XML desde el cliente
	@POST
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public String getXML(String insertar) {
		SAXBuilder builder = new SAXBuilder();
		try {
			// Se crea el documento a traves del archivo
			Document document = (Document) builder.build(new StringReader(
					insertar));
			// Se obtiene la raiz 'local'
			Element rootNode = document.getRootElement();
			Local milocal = new Local();
			// Obtenemos los datos
			milocal.id = Integer.parseInt(rootNode.getChildTextTrim("id"));
			milocal.nombre = rootNode.getChildTextTrim("nombre");
			milocal.descripcion = rootNode.getChildTextTrim("descripcion");
			milocal.latitud = Double.parseDouble(rootNode
					.getChildTextTrim("latitud"));
			milocal.longitud = Double.parseDouble(rootNode
					.getChildTextTrim("longitud"));
			BaseDeDatos bd = new BaseDeDatos();
			Boolean salida = bd.insertarLocal(milocal);
			return salida.toString();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

}
