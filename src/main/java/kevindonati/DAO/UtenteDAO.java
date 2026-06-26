package kevindonati.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import kevindonati.Exception.NotFoundException;
import kevindonati.entities.Utente;

import java.util.UUID;

public class UtenteDAO {
    private final EntityManager entityManager;

    public UtenteDAO(EntityManager em) {
        this.entityManager = em;
    }

    public void save(Utente nuovoUtente) {
        // CREO TRANSAZIONE
        EntityTransaction transaction = this.entityManager.getTransaction();

        // LA FACCIO PARTIRE
        transaction.begin();

        // PERSIST
        this.entityManager.persist(nuovoUtente);

        // COMMIT
        transaction.commit();

        // CHECK
        System.out.println(nuovoUtente + " salvato nel db");
    }

    public Utente findById(String id) {
        Utente founded = entityManager.find(Utente.class, UUID.fromString(id));
        if (founded == null) {
            throw new NotFoundException(id);
        } else {
            System.out.println("Trovato");
            return founded;
        }
    }
}
