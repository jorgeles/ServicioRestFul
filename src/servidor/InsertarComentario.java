package servidor;

import java.io.IOException;
import java.io.StringReader;

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
 * @author: Jorge Gonzalez Peregrin
 * Llama a la base de datos indicandole que inserte un comentario en el local
 * Devuelve los datos completos del local con el comentario ya insertado
 */
@Path("/insertarcomentario")
public class InsertarComentario {
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
			Comentario comentario = new Comentario();
			// Obtenemos los datos
			milocal.id = Integer.parseInt(rootNode.getChildTextTrim("idLocal"));
			comentario.valoracion = Integer.parseInt(rootNode
					.getChildTextTrim("valoracion"));
			comentario.texto = rootNode.getChildTextTrim("texto");
			BaseDeDatos bd = new BaseDeDatos();
			Boolean salida = bd.insertarComentarios(milocal,comentario);
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
