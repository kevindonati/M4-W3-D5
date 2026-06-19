package kevindonati.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import kevindonati.Enum.Genere;

@Entity
@DiscriminatorValue("libro")
public class Libro extends Catalogo {
    @Column(nullable = false)
    private String autore;

    @Column(nullable = false)
    private Genere genere;

    // COSTRUTTORE VUOTO
    protected Libro() {

    }

    // COSTRUTTORE
    public Libro(int codiceIsbn, int titolo, int annoDiPubblicazione, int numeroPagine, String autore, Genere genere) {
        super(codiceIsbn, titolo, annoDiPubblicazione, numeroPagine);
        this.autore = autore;
        this.genere = genere;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "genere=" + genere +
                ", autore='" + autore + '\'' +
                "} " + super.toString();
    }
}
