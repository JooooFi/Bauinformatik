package model;

import java.util.*;

public class Versandzentrum {
    private final String name; //unveränderebarer String
    private final List<Warenlager> warenlagerListe;

    //Konstruktor
    public Versandzentrum(String name) {
        this.name = name;
        this.warenlagerListe = new ArrayList<>();
    }

    public void addWarenlager(Warenlager warenlager) {
        warenlagerListe.add(warenlager); //fügt ein Warenlager in die Liste hinzu
    }

    // 2 Berechnung der Anzahl fehlender Bauteile
    //
    public Map<Bauteil, Integer> berechneFehlendeBauteile(Bestellung bestellung) {
        Map<Bauteil, Integer> fehlendeBauteile = new HashMap<>(); //Map mit Bauteil und Anzahl der fehlenden Bauteile

        // Iteration über alle Bauteile in der Bestellung
        // Map.Entry ist ein Schlüssel-Wert-Paar
        //Schlüssel: Bauteil, Wert: Anzahl
        for (Map.Entry<Bauteil, Integer> entry : bestellung.getBauteilListe()
                                                           .entrySet()) { //alle benötigen Bauteile werden
            // nacheinander gelesen
            // Entry ist ein Interface, das ein Schlüssel-Wert-Paar darstellt
            //entrySet() gibt ein Set zurück, das alle Schlüssel-Wert-Paare enthält
            //Set ist eine Collection, die keine doppelten Elemente enthält
            Bauteil bauteil = entry.getKey(); //Bauteil wird gespeichert durch das speichern des Schlüssels des Entry
            int benoetigteAnzahl = entry.getValue(); //Anzahl wird gespeichert durch das speichern des Wertes des Entry

            // Verfügbaren Bestand in allen Warenlagern ermitteln
            int verfuegbarerBestand = 0;
            for (Warenlager lager : warenlagerListe) {
                // getBestand() gibt eine Map zurück, die die Bauteile und deren Anzahl enthält
                //getOrDefault() gibt den Wert zurück, der dem angegebenen Schlüssel zugeordnet ist, oder den
                // Standardwert,
                // wenn der Schlüssel nicht vorhanden ist
                verfuegbarerBestand += lager.getBestand().getOrDefault(
                        bauteil,
                        0
                ); //
                //wenn das Bauteil nicht vorhanden ist, wird 0 zurückgegeben

            }

            // Fehlende Anzahl berechnen
            // benowtigteAnzahl = was angefordert wird von der Bestellung
            // verfuegbarerBestand = was vorhanden ist in den Warenlagern zusammengezählt
            int fehlendeAnzahl = benoetigteAnzahl - verfuegbarerBestand;
            if (fehlendeAnzahl > 0) {
                // wenn keine bauteile fehlen, wird das bauteil nicht in die map eingefügt
                // da es zur verwirrung kommen könnte, wenn die Anzahl des fehlenden bauteilen 0 ist
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
    private List<Warenlager> getWarenlagerListe(Map<Bauteil, Integer> bauteilListe) {
        List<Warenlager> list = new ArrayList<>();
        // Iteration über alle Bauteile in der Bestellung
        for (Bauteil b : bauteilListe.keySet()) {
            System.out.println("Benoetiges Bauteil: " + b.toString() + ". Folgende Warenlager haben es:");
            int bestand = 0;
            for (Warenlager lager : warenlagerListe) {
                if (lager.durchsuchen(b) != 0) {
                    //durchsuchen() gibt die Anzahl des Bauteils zurück, wenn es vorhanden ist
                    list.add(lager);
                    bestand += lager.getBestand().get(b); //addiert den Bestand des Bauteils in den Warenlagern von
                    // Bauteil b
                }

            }
            //bauteilBestand ist die geforderte Anzahl des jeweiligen Bauteils
            int bauteilBestand = bauteilListe.get(b);
            //bestand ist der Bestand des Bauteils in den Warenlagern zusammengezählt
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
        //StringBuilder ist eine Klasse, die eine modifizierbare Sequenz von Zeichen bereitstellt
        StringBuilder sb = new StringBuilder();
        //append() fügt den Inhalt des Strings an das Ende des StringBuilders an
        // ist einfacher zu handhaben als die Verkettung von Strings
        sb.append("Versandzentrum:\n ").append(name).append("\n");
        sb.append("Warenlager:\n");

        for (Warenlager lager : warenlagerListe) {
            sb.append(lager.getName()).append("\n"); // Nutzt die toString() von Warenlager
        }

        return sb.toString();
    }

}
