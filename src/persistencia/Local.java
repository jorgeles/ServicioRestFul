package persistencia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Local implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	public String nombre;
	public String descripcion;
	public double longitud;
	public double latitud;
	@OneToMany
	public List<Comentario> comentarios = new ArrayList<Comentario>() ;
}
