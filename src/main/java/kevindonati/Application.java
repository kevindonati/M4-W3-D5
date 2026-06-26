package kevindonati;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import kevindonati.DAO.CatalogoDAO;
import kevindonati.DAO.PrestitoDAO;
import kevindonati.DAO.UtenteDAO;
import kevindonati.Enum.Genere;
import kevindonati.Enum.Periodicita;
import kevindonati.Exception.NotFoundException;
import kevindonati.entities.*;

import java.time.LocalDate;

public class Application {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("catalogobibliotecario");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        UtenteDAO utenteDAO = new UtenteDAO(entityManager);
        CatalogoDAO catalogoDAO = new CatalogoDAO(entityManager);
        PrestitoDAO prestitoDAO = new PrestitoDAO(entityManager);

        // CREO UTENTI
        Utente utente1 = new Utente("Kevin", "Donati", LocalDate.of(2002, 4, 6), 1521548);
        Utente utente2 = new Utente("Marco", "Rossi", LocalDate.of(1998, 7, 16), 1245789);
        Utente utente3 = new Utente("Edoardo", "Verdi", LocalDate.of(1999, 1, 24), 1254689);

        // CREO CATALOGHI
        Catalogo catalogo1 = new Libro(9788804668234L, "Il nome della rosa", 1980, 512, "Umberto Eco", Genere.GIALLO);
        Catalogo catalogo2 = new Libro(9788806229648L, "I promessi sposi", 1840, 720, "Alessandro Manzoni", Genere.NARRATIVA);
        Catalogo catalogo3 = new Rivista(9781234567890L, "Focus", 2025, 120, Periodicita.MENSILE);
        Catalogo catalogo4 = new Rivista(9780987654321L, "National Geographic", 2025, 140, Periodicita.MENSILE);

////         SALVO UTENTI
//        utenteDAO.save(utente1);
//        utenteDAO.save(utente2);
//        utenteDAO.save(utente3);
//
////         SALVO CATALOGHI
//        catalogoDAO.save(catalogo1);
//        catalogoDAO.save(catalogo2);
//        catalogoDAO.save(catalogo3);
//        catalogoDAO.save(catalogo4);

        // TROVO UTENTI TRAMITE ID
        Utente utente1FromDb = null;
        Utente utente2FromDb = null;
        Utente utente3FromDb = null;
        try {
            utente1FromDb = utenteDAO.findById("1e41a318-081e-4a71-be25-e32380a7717c");
        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            utente2FromDb = utenteDAO.findById("7c2e8cf0-94eb-4acc-9129-885d92fa9daa");
        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            utente3FromDb = utenteDAO.findById("e642a821-11cb-4974-becf-c4a50c4cb269");
        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        // TROVO CATALOGHI TRAMITE ID
        Catalogo catalogo1FromDb = null;
        Catalogo catalogo2FromDb = null;
        Catalogo catalogo3FromDb = null;
        Catalogo catalogo4FromDb = null;
        try {
            catalogo1FromDb = catalogoDAO.findById("04135f0c-d67a-4b92-b748-e7880c2ffc5d");
        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            catalogo2FromDb = catalogoDAO.findById("219c98bd-afbd-41fe-8fce-a3640290f7b1");
        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            catalogo3FromDb = catalogoDAO.findById("4ce24951-7bdc-4de9-8912-762566f6a606");
        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            catalogo4FromDb = catalogoDAO.findById("f16f56e6-aa20-4af1-ad4b-49e4d7af00f0");
        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        // CREO E SALVO I PRESTITI
//        Prestito prestito1 = new Prestito(utente1FromDb, catalogo1FromDb, LocalDate.now());
//        Prestito prestito2 = new Prestito(utente2FromDb, catalogo4FromDb, LocalDate.now());
//        Prestito prestito3 = new Prestito(utente1FromDb, catalogo3FromDb, LocalDate.now());
//        Prestito prestito4 = new Prestito(utente3FromDb, catalogo2FromDb, LocalDate.now());
//        Prestito prestito5 = new Prestito(utente3FromDb, catalogo1FromDb, LocalDate.of(2025, 04, 15));
//        Prestito prestito6 = new Prestito(utente1FromDb, catalogo2FromDb, LocalDate.of(2026, 02, 9));
//
//        prestitoDAO.save(prestito1);
//        prestitoDAO.save(prestito2);
//        prestitoDAO.save(prestito3);
//        prestitoDAO.save(prestito4);
//        prestitoDAO.save(prestito5);
//        prestitoDAO.save(prestito6);

        // ESERCIZI
        // 1) catalogoDAO.save(catalogo1);
        // 2) try {
        //            catalogoDAO.removeByIsbn(9781234567890L);
        //        } catch (NotFoundException ex) {
        //            System.out.println(ex.getMessage());
        //        }
        // 3) try {
        //            catalogoDAO.findByIsbn(9788806229648L);
        //        } catch (NotFoundException ex) {
        //            System.out.println(ex.getMessage());
        //        }
        // 4) try {
        //            catalogoDAO.findByPublicationYear(2025);
        //        } catch (NotFoundException ex) {
        //            System.out.println(ex.getMessage());
        //        }
        // 5) try {
        //            catalogoDAO.findByAuthor("e");
        //        } catch (NotFoundException ex) {
        //            System.out.println(ex.getMessage());
        //        }
        // 6) try {
        //            catalogoDAO.findByTitolo("a");
        //        } catch (NotFoundException ex) {
        //            System.out.println(ex.getMessage());
        //        }
        // 7) try {
        //            catalogoDAO.findElementiInPrestitoByTessera(125789);
        //        } catch (NotFoundException ex) {
        //            System.out.println(ex.getMessage());
        //        }
        // 8) try {
        //            catalogoDAO.findAllPrestitiScadutiNonRestituiti();
        //        } catch (NotFoundException ex) {
        //            System.out.println(ex.getMessage());
        //        }
        
    }
}
