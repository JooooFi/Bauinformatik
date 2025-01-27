package model;

import java.util.*;

public class Versandzentrum {
    private final String name;
    private final List<Warenlager> warenlagerListe;

    public Versandzentrum(String name) {
        this.name = name;
        this.warenlagerListe = new ArrayList<>();
    }

    public void addWarenlager(Warenlager warenlager) {
        warenlagerListe.add(warenlager);
    }

    // 2 Berechnung der Anzahl fehlender Bauteile
    //
    public Map<Bauteil, Integer> berechneFehlendeBauteile(Bestellung bestellung) {
        Map<Bauteil, Integer> fehlendeBauteile = new HashMap<>();

        for (Map.Entry<Bauteil, Integer> entry : bestellung.getBauteilListe()
                                                           .entrySet()) { //alle benötigen Bauteile werden
            // nacheinander gelesen
            Bauteil bauteil = entry.getKey(); //Bauteil wird gespeichert durch das speichern des Schlüssels des Entry
            int benoetigteAnzahl = entry.getValue();

            // Verfügbaren Bestand in allen Warenlagern ermitteln
            int verfuegbarerBestand = 0;
            for (Warenlager lager : warenlagerListe) {
                verfuegbarerBestand += lager.getBestand().getOrDefault(
                        bauteil,
                        0
                ); //wenn das Bauteil nicht vorhanden ist, wird 0 zurückgegeben
            }

            // Fehlende Anzahl berechnen
            int fehlendeAnzahl = benoetigteAnzahl - verfuegbarerBestand;
            if (fehlendeAnzahl > 0) {
                fehlendeBauteile.put(
                        bauteil,
                        fehlendeAnzahl
                ); //wenn die fehlende Anzahl größer als 0 ist, wird das Bauteil und die fehlende Anzahl in die Map
                // eingefügt
            }
        }

        return fehlendeBauteile;
    }

    //gibt eine Liste zurück mit dem Warenlager, die das jeweilige Bauteil besitzen. Ordnet nach der Distanz
    public List<Warenlager> getWarenlagerListeBeiDistanz(Map<Bauteil, Integer> orderlist) {
        List<Warenlager> list = getWarenlagerListe(orderlist);
        list.sort(Comparator.comparingInt(
                Warenlager::getEntfernung)); //sortiert anhand des Integers von getEntfernung. der kleinste wert als
        // erstes.
        return list;
    }

    //gibt eine Liste zurück mit dem Warenlager, die das jeweilige Bauteil besitzen. Geordnet nach der Kapazität
    public List<Warenlager> getWarenlagerListeBeiKapa(Map<Bauteil, Integer> orderlist) {
        List<Warenlager> list = getWarenlagerListeBeiDistanz(orderlist);
        list.sort(Comparator.comparingInt(Warenlager::getKapazitaetsauslastung)
                            .reversed()); //sortiert anhand des Integers von getkapazität. der größte wert als Erstes.
        return list;
    }

    //um Redundanzen zu verhindern wurde der code ausgelagert.
    private List<Warenlager> getWarenlagerListe(Map<Bauteil, Integer> orderlist) {
        List<Warenlager> list = new ArrayList<>();
        for (Bauteil b : orderlist.keySet()) {
            System.out.println("Benoetiges Bauteil: " + b.toString() + ". Folgende Warenlager haben es:");
            int bestand = 0;
            for (Warenlager lager : warenlagerListe) {
                if (lager.durchsuchen(b) != 0) {
                    list.add(lager);
                    bestand += lager.getBestand().get(b); //addiert den Bestand des Bauteils in den Warenlagern von
                    // Bauteil b
                }

            }
            int bauteilBestand = orderlist.get(b);
            if (bestand < bauteilBestand) {
                System.out.println("Warnung: Nicht genügend Bestand für Bauteil " +
                                           b.getBezeichnung() + " (" + b.getMaterial() +
                                           "). Fehlender Restbetrag: " + (bauteilBestand - bestand) + "\n");
            }
        }
        return list;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Versandzentrum:\n ").append(name).append("\n");
        sb.append("Warenlager:\n");

        for (Warenlager lager : warenlagerListe) {
            sb.append(lager.getName()).append("\n"); // Nutzt die toString() von Warenlager
        }

        return sb.toString();
    }

}
