package servidor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Comentario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	public String texto="Hola";
	public int valoracion;

}
