package servidor;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import paquete.Todo;

@Path("/eliminar")
public class Eliminar {
	// Este metodo se llamara si existe una peticion XML desde el cliente
	@POST
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Todo getXML() {
		return null;
	}
}
