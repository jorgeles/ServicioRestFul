package persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class BaseDeDatos {
	private static final String PERSISTENCE_UNIT_NAME = "Geolocalizacion";
	private EntityManagerFactory factoria;

	public boolean insertarLocal(Local local) {
		factoria = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();
		em.getTransaction().begin();
		Local miLocal = local;
		// Posteriormente hay que decirle al gestor de entidad (em) que dicha
		// instancia va a ser persistente; conseguir la transaccion
		// ("em.getTransaction()") y hacerla definitiva ("commit()")
		em.persist(local);
		em.getTransaction().commit();
		// Por ultimo, hay que cerrar al gestor de entidad
		em.close();
		return false;
	}

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

	public List<Local> obtenerLocales() {
		factoria = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();
		// leer las entradas existentes y escribir en la consola
		Query q = em.createQuery("SELECT l from Local l");
		List<Local> listalocales = q.getResultList();
		return listalocales;
	}

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
		return false;

	}

	public boolean eliminar(List<Long> id) {
		factoria = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();
		for (Long miId : id) {
			em.getTransaction().begin();
			Query q = em
					.createQuery("SELECT l FROM Local l WHERE l.id = :id ");
			q.setParameter("id", miId);
			List<Local> listaLocales = q.getResultList();
			for (Local lista : listaLocales) {
				if (lista.id == miId) {
					em.remove(lista);
				}
			}
			em.getTransaction().commit();
			em.close();
		}
		return false;

	}
}
