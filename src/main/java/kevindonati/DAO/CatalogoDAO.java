package kevindonati.DAO;

import jakarta.persistence.*;
import kevindonati.Exception.NotFoundException;
import kevindonati.entities.Catalogo;
import kevindonati.entities.Libro;
import kevindonati.entities.Prestito;
import kevindonati.entities.Utente;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class CatalogoDAO {
    private final EntityManager entityManager;

    public CatalogoDAO(EntityManager em) {
        this.entityManager = em;
    }

    public void save(Catalogo nuovoCat) {
        // CREO TRANSAZIONE
        EntityTransaction transaction = this.entityManager.getTransaction();

        // LA FACCIO PARTIRE
        transaction.begin();

        // PERSIST
        this.entityManager.persist(nuovoCat);

        // COMMIT
        transaction.commit();

        // CHECK
        System.out.println(nuovoCat + " salvato nel db");
    }

    public Catalogo findById(String id) {
        Catalogo founded = entityManager.find(Catalogo.class, UUID.fromString(id));
        if (founded == null) {
            throw new NotFoundException(id);
        } else {
            return founded;
        }
    }

    public Catalogo findByIsbn(long isbn) {
        try {
            TypedQuery<Catalogo> query = entityManager.createQuery("SELECT c FROM Catalogo c WHERE c.codiceIsbn = :param", Catalogo.class);
            query.setParameter("param", isbn);
            Catalogo trovato = query.getSingleResult();
            System.out.println("Oggeto con ISBN " + isbn + " trovato");
            return trovato;
        } catch (NoResultException ex) {
            throw new NotFoundException();
        }
    }

    public void removeByIsbn(long isbn) {
        Catalogo catDaRimuovere = findByIsbn(isbn);
        // CREO TRANSAZIONE
        EntityTransaction transaction = this.entityManager.getTransaction();

        // LA FACCIO PARTIRE
        transaction.begin();

        entityManager.createQuery("DELETE FROM Prestito p WHERE p.elementoPrestato = :param")
                .setParameter("param", catDaRimuovere)
                .executeUpdate();
        this.entityManager.remove(catDaRimuovere);


        // COMMIT
        transaction.commit();

        // CHECK
        System.out.println("L'oggetto con codice isbn " + isbn + " è stato rimossso dal db");
    }

    public List<Catalogo> findByPublicationYear(int year) {
        List<Catalogo> query = entityManager.createQuery("SELECT c FROM Catalogo c WHERE c.annoDiPubblicazione = :param", Catalogo.class)
                .setParameter("param", year)
                .getResultList();
        if (query.size() != 0) {
            System.out.println("Libri e riviste pubblicate nel " + year + ": " + query);
            return query;
        } else {
            throw new NotFoundException();
        }
    }

    public List<Libro> findByAuthor(String autore) {
        List<Libro> query = entityManager.createQuery("SELECT l FROM Libro l WHERE l.autore LIKE :param", Libro.class)
                .setParameter("param", "%" + autore + "%")
                .getResultList();
        if (query.size() != 0) {
            System.out.println("Libri trovati dalla ricerca " + autore + ": " + query);
            return query;
        } else {
            throw new NotFoundException();
        }
    }

    public List<Catalogo> findByTitolo(String titolo) {
        List<Catalogo> query = entityManager.createQuery("SELECT c FROM Catalogo c WHERE c.titolo LIKE :param", Catalogo.class)
                .setParameter("param", "%" + titolo + "%")
                .getResultList();
        if (query.size() != 0) {
            System.out.println("Risultato della ricerca: " + query);
            return query;
        } else {
            throw new NotFoundException();
        }
    }

    public List<Catalogo> findElementiInPrestitoByTessera(int tessera) {
        List<Catalogo> query = entityManager.createQuery("SELECT p.elementoPrestato FROM Prestito p WHERE p.utente.numeroTessera = :param AND p.dataRestituzioneEffettiva IS NULL", Catalogo.class)
                .setParameter("param", tessera)
                .getResultList();
        if (query.size() != 0) {
            System.out.println("Elementi in prestito della tessera " + tessera + ": " + query);
            return query;
        } else {
            throw new NotFoundException();
        }
    }

    public List<Catalogo> findAllPrestitiScadutiNonRestituiti() {
        List<Catalogo> query = entityManager.createQuery("SELECT p.elementoPrestato FROM Prestito p WHERE p.dataRestituzionePrevista < :param AND p.dataRestituzioneEffettiva IS NULL", Catalogo.class)
                .setParameter("param", LocalDate.now())
                .getResultList();
        if (query.size() != 0) {
            System.out.println("Prestiti scaduti e non ancora restituiti: " + query);
            return query;
        } else {
            throw new NotFoundException();
        }
    }
}
