package persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BaseDeDatos {
	private static final String PERSISTENCE_UNIT_NAME = "Geolocalizacion";
	private EntityManagerFactory factoria;
	
	public boolean InsertarLocal(Local local){
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
}
