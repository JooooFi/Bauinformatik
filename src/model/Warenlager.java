package model;

import java.util.HashMap;
import java.util.Map;

public class Warenlager {
    private final String name;
    private final Map<Bauteil, Integer> bestand;
    private final int entfernung;
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

    public void addBauteil(String bezeichnung, String material, int anzahl) {
        Bauteil b = new Bauteil(bezeichnung, material);
        addBauteil(b, anzahl);
    }

    public int durchsuchen(Bauteil bauteil) {
        if (bestand.containsKey(bauteil)) {
            return bestand.getOrDefault(bauteil, 0);
        }
        return 0;
    }

    public int getEntfernung() {
        return entfernung;
    }

    public int getKapazitaetsauslastung() {
        return kapazitaetsauslastung;
    }

    public void setKapazitaetsauslastung(int kapazitaetsauslastung) {
        this.kapazitaetsauslastung = kapazitaetsauslastung;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Warenlager{" +
                "Name='" + name + '\'' +
                ", Entfernung=" + entfernung +
                ", Kapazitätsauslastung=" + kapazitaetsauslastung +
                ", Bestand=" + bestand +
                '}';
    }

}

