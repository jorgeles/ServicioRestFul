package servidor;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/*
 *@author Jorge Gonzalez Peregrin
 *Llama a la bse de datos y elimina el local que se pasa como parametro
 */
@Path("/eliminar")
public class Eliminar {
	// Este metodo se llamara si existe una peticion XML desde el cliente
	@POST
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public String getXML(String id) {
		BaseDeDatos bd = new BaseDeDatos();
		String salida = bd.eliminar(Integer.parseInt(id));
		return salida;
	}
}
