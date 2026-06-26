package kevindonati.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import kevindonati.entities.Prestito;

public class PrestitoDAO {
    private final EntityManager entityManager;

    public PrestitoDAO(EntityManager em) {
        this.entityManager = em;
    }

    public void save(Prestito nuovoPrestito) {
        // CREO TRANSAZIONE
        EntityTransaction transaction = this.entityManager.getTransaction();

        // LA FACCIO PARTIRE
        transaction.begin();

        // PERSIST
        this.entityManager.persist(nuovoPrestito);

        // COMMIT
        transaction.commit();

        // CHECK
        System.out.println(nuovoPrestito + " salvato nel db");
    }
}
