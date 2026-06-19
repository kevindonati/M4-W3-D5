package kevindonati.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "eventi")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Catalogo {
    @Id
    @GeneratedValue
    @Column(name = "elemento_id")
    private UUID id;

    @Column(name = "codice_isbn", nullable = false)
    private int codiceIsbn;

    @Column(nullable = false)
    private int titolo;

    @Column(name = "anno_di_pubblicazione")
    private int annoDiPubblicazione;

    @Column(name = "numero_pagine")
    private int numeroPagine;

    // COSTRUTTORE VUOTO
    protected Catalogo() {

    }

    // COSTRUTTORE
    public Catalogo(int codiceIsbn, int titolo, int annoDiPubblicazione, int numeroPagine) {
        this.codiceIsbn = codiceIsbn;
        this.titolo = titolo;
        this.annoDiPubblicazione = annoDiPubblicazione;
        this.numeroPagine = numeroPagine;
    }

    @Override
    public String toString() {
        return "Catalogo{" +
                "id=" + id +
                ", codiceIsbn=" + codiceIsbn +
                ", titolo=" + titolo +
                ", annoDiPubblicazione=" + annoDiPubblicazione +
                ", numeroPagine=" + numeroPagine +
                '}';
    }
}
