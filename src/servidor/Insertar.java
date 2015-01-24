package servidor;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/insertar")
public class Insertar {
	// Este metodo se llamara si existe una peticion XML desde el cliente
	@POST
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Local getXML(Local local) {
		BaseDeDatos bd = new BaseDeDatos();
		bd.insertarLocal(local);
		return local;
	}
	
	@GET
	  @Produces({ MediaType.TEXT_XML })
	  public Local getHTML() {
		Local todo = new Local();
	    return todo;
	  }
}
