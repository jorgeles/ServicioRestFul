package servidor;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/*
 * @author Jorge Leon Fernandez.
 * Devuelbe true si la actualización ha sido correcta
 */
@Path("/actualizar")
public class Actualizar {
	// Este metodo se llamara si existe una peticion XML desde el cliente
	@POST
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public String getXML(Local local) {
		BaseDeDatos bd = new BaseDeDatos();
		Boolean realizado = bd.actualizar(local);
		return realizado.toString();
	}
}
