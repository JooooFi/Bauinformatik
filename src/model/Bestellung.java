package model;

import java.util.HashMap;
import java.util.Map;

public class Bestellung {
    private Kunde kunde;
    private Map<Bauteil, Integer> bauteilListe;

    // Konstruktor, Getter und Setter
    public Bestellung(Kunde kunde) {
        this.kunde = kunde;
        this.bauteilListe = new HashMap<>();
    }

    public Kunde getKunde() {
        return kunde;
    }

    public Map<Bauteil, Integer> getBauteilListe() {
        return bauteilListe;
    }

    public void addBauteil(Bauteil bauteil, int anzahl) {
        bauteilListe.merge(bauteil, anzahl, Integer::sum);
    }
}
