package servidor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/*
 * @author Jorge Leon Fernandez
 * Clase que contiene los datos de los comentarios que tendra cada local
 * Contiene una valoracion que indica en valor numerico la puntuacion que le dan al local
 * Contiene un texto donde se encuentra el comentario sobre el local
 */
@Entity
@XmlRootElement
public class Comentario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	public String texto="Hola";
	public int valoracion;

}
