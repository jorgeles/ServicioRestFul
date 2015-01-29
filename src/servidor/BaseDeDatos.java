package servidor;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/*
 * @author: Jorge Leon Fernandez
 */
public class BaseDeDatos {
	private static final String PERSISTENCE_UNIT_NAME = "geolocalizacion";
	private EntityManagerFactory factoria;

	public boolean insertarLocal(Local local) {
		factoria = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();
		//Iniciamos la transaccion
		em.getTransaction().begin();
		Local miLocal = local;
		// Posteriormente hay que decirle al gestor de entidad (em) que dicha
		// instancia va a ser persistente; conseguir la transaccion
		// ("em.getTransaction()") y hacerla definitiva ("commit()")
		em.persist(local);
		em.getTransaction().commit();
		// Por ultimo, hay que cerrar al gestor de entidad
		em.close();
		return true;
	}

	/*
	 * @author: Jorge Leon Fernandez
	 * Se comprueba si existe un local. Se evita meter mas de uno igual
	 */
	public boolean existeLocal(Local local) {
		factoria = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();
		Query q = em.createQuery("SELECT l from Local l WHERE l.id = :local");
		q.setParameter("local", local.id);
		List<Local> locales = q.getResultList();
		for (Local lista : locales) {
			if (lista.id == local.id) {
				return true;
			}
		}
		return false;
	}

	/*
	 * @author: Jorge Gonzalez Peregrin
	 * Se obtiene todos los locales de la base de datos
	 */
	public List<Local> obtenerLocales() {
		factoria = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();
		Query q = em.createQuery("SELECT l from Local l");
		List<Local> listalocales = q.getResultList();
		return listalocales;
	}
	
	/*
	 * @author: Jorge Leon	Fernandez
	 * Se obtiene todos los locales de la base de datos
	 */
	public List<Local> obtenerLocal(String id) {
		factoria = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();
		Query q = em.createQuery("SELECT l from Local l WHERE l.id= :id");
		q.setParameter("id", Integer.parseInt(id));
		List<Local> listalocales = q.getResultList();
		return listalocales;
	}

	/*
	 * @author: Jorge Leon Fernandez
	 * Se actualiza el local que se pasa como parametro en la base de datos
	 */
	public boolean actualizar(Local local) {
		factoria = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();
		EntityTransaction updateTransaction = em.getTransaction();
		updateTransaction.begin();
		Query q = em
				.createQuery("UPDATE Local l SET l.nombre = :nombre, l.descripcion = :descripcion, l.longitud= :longitud, l.latitud= :latitud WHERE l.id = :id");
		q.setParameter("nombre", local.nombre);
		q.setParameter("descripcion", local.descripcion);
		q.setParameter("longitud", local.longitud);
		q.setParameter("latitud", local.latitud);
		q.setParameter("id", local.id);
		q.executeUpdate();
		updateTransaction.commit();

		//Como no se pueden actulizar de golpe todos los comentarios, se actualizan de uno en uno
		for (int i = 0; i < local.comentarios.size(); i++) {
			factoria = Persistence
					.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			em = factoria.createEntityManager();
			updateTransaction = em.getTransaction();
			updateTransaction.begin();
			q = em.createQuery("UPDATE Comentario C SET C.texto = :texto, C.valoracion = :valoracion WHERE C.id = :id");
			q.setParameter("texto", local.comentarios.get(i).texto);
			q.setParameter("valoracion", local.comentarios.get(i).valoracion);
			q.setParameter("id", local.comentarios.get(i).id);
			q.executeUpdate();
			updateTransaction.commit();
		}
		return true;

	}

	/*
	 * @author: Jorge Gonzalez Peregrin
	 * Se elimina el local que se pasa como parametro en la base de datos
	 */
	public String eliminar(long id) {
		factoria = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();
		em.getTransaction().begin();
		Query q = em.createQuery("SELECT l FROM Local l WHERE l.id = :id ");
		q.setParameter("id", id);
		List<Local> listaLocales = q.getResultList();
		for (Local lista : listaLocales) {
			if (lista.id == id) {
				em.remove(lista);
			}
		}
		em.getTransaction().commit();
		em.close();
		Boolean bo = true;
		return bo.toString();

	}

	/*
	 * @author: Jorge Gonzalez Peregrin
	 * Se inserta el  comentario que se pasa como parametro en el local que se pasa como parametro
	 */
	public Local insertarComentarios(Local local, Comentario comentario) {
		factoria = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();
		em.getTransaction().begin();
		Local miLocal = em.find(Local.class, local.id);
		miLocal.comentarios.add(comentario);
		em.getTransaction().commit();
		em.close();
		return miLocal;
	}
}
