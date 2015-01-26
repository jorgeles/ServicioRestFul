package servidor;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
	public Local getXML(Local local) {
		BaseDeDatos bd = new BaseDeDatos();
		Comentario comentario = local.comentarios.get(local.comentarios.size()-1);
		Local salida = bd.insertarComentarios(local, comentario);
		return salida;
	}

}

