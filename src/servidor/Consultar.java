package servidor;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/*
 * @author Jorge Gonzalez Peregrin
 * Llama a la base de datos y obtiene de esta todos los locales de la base de datos.
 * Devuelve una lista con todos los locales
 */
@Path("/consultar")
public class Consultar {
	// Este metodo se llamara si existe una peticion XML desde el cliente
	@POST
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Local> getXML() {
		BaseDeDatos bd = new BaseDeDatos();
		List<Local> locales = bd.obtenerLocales();
		return locales;
	}
}
