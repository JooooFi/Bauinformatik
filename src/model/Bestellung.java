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
        return bauteilListe; //gibt die Bauteile mit dessen Anzahl zurück
    }

    public void addBauteil(Bauteil bauteil, int anzahl) {
        bauteilListe.merge(bauteil, anzahl, Integer::sum); //wenn das Bauteil schon vorhanden ist, wird die Anzahl
        // addiert ansonsten das Objekt wird hinzugefügt
        // Integer::sum ist eine Methode, die zwei Integer addiert
    }

    public  void addBauteil(String bezeichnung, String material, int anzahl) {
        Bauteil bauteil = new Bauteil(bezeichnung, material); //neues Bauteil wird erstellt
        addBauteil(bauteil, anzahl);
    }

    @Override
    public String toString() {
        return "Bestellung{" +
                "kunde=" + kunde +
                ", bauteilListe=" + bauteilListe +
                '}';
    }
}
