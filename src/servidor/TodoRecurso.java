package servidor;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
//Esta clase solo devuelve una instancia de la clase Todo
@Path("/todo")
public class TodoRecurso {
// Este metodo se llamara si existe una peticion XML desde el cliente
  @POST
  @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
  public Todo getXML(String input) {
    Todo todo = new Todo();
    todo.setResumen("HOLAAA");
    todo.setDescripcion("Este es mi primer todo");
    return todo;
  }
//Lo que sigue se puede utilizar para comprobar la integracion con el navegador que utilices
  @GET
  @Produces({ MediaType.TEXT_XML })
  public Todo getHTML() {
    Todo todo = new Todo();
    todo.setResumen("Este es mi primer todo");
    todo.setDescripcion("Este es mi primer todo");
    return todo;
  }
}
