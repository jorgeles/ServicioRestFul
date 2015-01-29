package servidor;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/*
 * @author Jorge Leon Fernandez
 * Llama a la base de datos y obtiene los datos del local con Local.id = id
 * Devuelve una lista con el local
 */
@Path("/consultar_local")
public class ConsultarLocal {
	// Este metodo se llamara si existe una peticion XML desde el cliente
	@POST
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Local> getXML(String id) {
		BaseDeDatos bd = new BaseDeDatos();
		List<Local> locales = bd.obtenerLocal(id);
		return locales;
	}
}
