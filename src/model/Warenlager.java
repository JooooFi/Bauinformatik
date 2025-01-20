package model;

import java.util.HashMap;
import java.util.Map;

public class Warenlager {
    private String name;
    private Map<Bauteil, Integer> bestand;
    private int entfernung;
    private int kapazitaetsauslastung;

    // Konstruktor, Getter und Setter
    public Warenlager(String name, int entfernung, int kapazitaetsauslastung) {
        this.name = name;
        this.bestand = new HashMap<>();
        this.entfernung = entfernung;
        this.kapazitaetsauslastung = kapazitaetsauslastung;
    }

    public Map<Bauteil, Integer> getBestand() {
        return bestand;
    }

    public void addBauteil(Bauteil bauteil, int anzahl) {
        bestand.merge(bauteil, anzahl, Integer::sum);
    }

    public int durchsuchen(Bauteil bauteil) {
        if (bestand.containsKey(bauteil)) {
            return bestand.getOrDefault(bauteil, 0);
        };
        return 0;
    }

    public int getEntfernung() {
        return entfernung;
    }

    public int getKapazitaetsauslastung() {
        return kapazitaetsauslastung;
    }
}

