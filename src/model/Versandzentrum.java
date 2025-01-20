package model;

import java.util.*;

public class Versandzentrum {
    private String name;
    private List<Warenlager> warenlagerListe;

    public Versandzentrum(String name) {
        this.name = name;
        this.warenlagerListe = new ArrayList<>();
    }

    public void addWarenlager(Warenlager warenlager) {
        warenlagerListe.add(warenlager);
    }

    public Map<Bauteil, Integer> berechneFehlendeBauteile(Bestellung bestellung) {
        Map<Bauteil, Integer> fehlendeBauteile = new HashMap<>();

        for (Map.Entry<Bauteil, Integer> entry : bestellung.getBauteilListe().entrySet()) {
            Bauteil bauteil = entry.getKey();
            int benoetigteAnzahl = entry.getValue();

            // Verfügbaren Bestand in allen Warenlagern ermitteln
            int verfuegbarerBestand = 0;
            for (Warenlager lager : warenlagerListe) {
                verfuegbarerBestand += lager.getBestand().getOrDefault(bauteil, 0);
            }

            // Fehlende Anzahl berechnen
            int fehlendeAnzahl = benoetigteAnzahl - verfuegbarerBestand;
            if (fehlendeAnzahl > 0) {
                fehlendeBauteile.put(bauteil, fehlendeAnzahl);
            }
        }

        return fehlendeBauteile;
    }

    public List<Warenlager> getWarenlagerListeBeiDistanz(Map<Bauteil, Integer> orderlist) {
        List<Warenlager> list = new ArrayList<>();
        int bestand = 0;
        for (Bauteil b : orderlist.keySet()) {
            for (Warenlager lager : warenlagerListe) {
                if (lager.durchsuchen(b) != 0) {
                    list.add(lager);
                    bestand += lager.getBestand().get(b);
                }
                int bauteilBestand = orderlist.get(b);
                if (bestand < bauteilBestand) {
                    System.out.println("Warnung: Nicht genügend Bestand für model.Bauteil " +
                            b.getBezeichnung() + " (" + b.getMaterial() +
                            "). Fehlender Restbetrag: " + (bauteilBestand - bestand));
                }
            }

        }

        list.sort(Comparator.comparingInt(Warenlager::getEntfernung));

        return list;
    }

    public List<Warenlager> getWarenlagerListeBeiKapa(Map<Bauteil, Integer> orderlist) {
        List<Warenlager> list = new ArrayList<>();
        int bestand = 0;
        for (Bauteil b : orderlist.keySet()) {
            for (Warenlager lager : warenlagerListe) {
                if (lager.durchsuchen(b) != 0) {
                    list.add(lager);
                    bestand += lager.getBestand().get(b);
                }

            }
            int bauteilBestand = orderlist.get(b);
            if (bestand < bauteilBestand) {
                System.out.println("Warnung: Nicht genügend Bestand für model.Bauteil " +
                        b.getBezeichnung() + " (" + b.getMaterial() +
                        "). Fehlender Restbetrag: " + (bauteilBestand - bestand));
            }
        }

        list.sort(Comparator.comparingInt(Warenlager::getKapazitaetsauslastung).reversed());

        return list;
    }
}
