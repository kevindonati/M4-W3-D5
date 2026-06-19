package kevindonati.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import kevindonati.Enum.Genere;
import kevindonati.Enum.Periodicita;

@Entity
@DiscriminatorValue("rivista")
public class Rivista extends Catalogo {
    @Column(nullable = false)
    private Periodicita periodicita;

    // COSTRUTTORE VUOTO
    protected Rivista() {

    }

    // COSTRUTTORE
    public Rivista(int codiceIsbn, int titolo, int annoDiPubblicazione, int numeroPagine, Periodicita periodicita) {
        super(codiceIsbn, titolo, annoDiPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    @Override
    public String toString() {
        return "Rivista{" +
                "periodicita=" + periodicita +
                "} " + super.toString();
    }
}
