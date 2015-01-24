package servidor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Local implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	public String nombre;
	public String descripcion;
	public double longitud;
	public double latitud;
	@OneToMany(targetEntity=Comentario.class,cascade = CascadeType.PERSIST)
	public List<Comentario> comentarios = new ArrayList<Comentario>() ;
}
