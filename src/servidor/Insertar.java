package servidor;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
	public String getXML(Local local) {
		BaseDeDatos bd = new BaseDeDatos();
		Boolean salida = bd.insertarLocal(local);
		return salida.toString();
	}

}
