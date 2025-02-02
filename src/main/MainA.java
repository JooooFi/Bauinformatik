package main;

import model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainA {
    public static void main(String[] args) {
        System.out.println("testbeispiel A\n");
        System.out.println("Kunden");
        Kunde kundeA = new Kunde("A", "123");
        Kunde kundeB = new Kunde("B", "456");
        Kunde kundeC = new Kunde("C", "789");
        System.out.println("Kunde 1:"+ kundeA);
        System.out.println("Kunde 2:"+ kundeB);
        System.out.println("Kunde 3:"+ kundeC);

        System.out.println("\nWarenlager");
        Warenlager warenlagerA = new Warenlager("LagerA", 12, 80);
        Warenlager warenlagerB = new Warenlager("LagerB", 5, 60);
        Warenlager warenlagerC = new Warenlager("LagerC", 4, 70);
        System.out.println("Warenlager1:" + warenlagerA);
        System.out.println("Warenlager2:" + warenlagerB);
        System.out.println("Warenlager3:" + warenlagerC);

        System.out.println("\nBestellungen");
        Bestellung bestellungA = new Bestellung(kundeA);
        Bestellung bestellungB = new Bestellung(kundeB);
        System.out.println("Bestellung 1: " + bestellungA);
        System.out.println("Bestellung 2: " + bestellungB);

        System.out.println("\nBauteile");
        Bauteil bauteilA = new Bauteil("Holzplatte", "Eiche");
        Bauteil bauteilB = new Bauteil("Holzbrett", "Kiefer");
        Bauteil bauteilC = new Bauteil("Mauerziegel", "Zement");
        Bauteil bauteilD = new Bauteil("IPE-Profile", "Stahl");
        Bauteil bauteilE = new Bauteil("HEA-Profile", "Stahl");
        System.out.println("Bauteil 1:"+ bauteilA);
        System.out.println("Bauteil 2:"+ bauteilB);
        System.out.println("Bauteil 3:"+ bauteilC);
        System.out.println("Bauteil 4:"+ bauteilD);
        System.out.println("Bauteil 5:"+ bauteilE);
        warenlagerA.addBauteil(bauteilA, 120);
        warenlagerA.addBauteil(bauteilB, 50);
        warenlagerB.addBauteil(bauteilB, 50);
        warenlagerB.addBauteil(bauteilC, 50);
        warenlagerC.addBauteil(bauteilD, 50);
        warenlagerC.addBauteil(bauteilC, 40);
        warenlagerC.addBauteil(bauteilE, 40);



        System.out.println("\nVersandzentrum");
        Versandzentrum versandzentrum = new Versandzentrum("Zentrale");

        warenlagerB.addBauteil(bauteilB, 100);
        warenlagerA.addBauteil(bauteilA, 10);
        warenlagerB.addBauteil(bauteilA, 20);

        bestellungA.addBauteil(bauteilA, 1000);
        bestellungA.addBauteil(bauteilB, 40);
        bestellungA.addBauteil(bauteilC, 50);
        bestellungB.addBauteil(bauteilD, 50);
        bestellungB.addBauteil(bauteilB, 10);

        versandzentrum.addWarenlager(warenlagerA);
        versandzentrum.addWarenlager(warenlagerB);
        versandzentrum.addWarenlager(warenlagerC);
        versandzentrum.addBestellung(bestellungA);
        versandzentrum.addBestellung(bestellungB);
        System.out.println("Versandzentrum 1:"+ versandzentrum);

        //zeigt an welche Bauteile fehlen. der erste wert ist der Adressname des objects
        System.out.println(" \nAufgabe 2");
        System.out.println(versandzentrum.berechneFehlendeBauteile(bestellungA));

        //suche nach kürzester Entfernung nach einem Bauteil
        // kein defizit
        System.out.println("\nAufgabe 3");
        Map<Bauteil, Integer> orderlist = new HashMap<>();
        orderlist.put(bauteilA, 1);
        List<Warenlager> list = versandzentrum.getWarenlagerListeBeiDistanz(orderlist);
        for (Warenlager warenlager : list) {
            System.out.println("Warenlager: "+ warenlager.getName() +": "+ warenlager.getEntfernung());
        }


        System.out.println("\nDefizit" );
        orderlist.put(bauteilB, 1000);
        list = versandzentrum.getWarenlagerListeBeiKapa(orderlist);
        for (Warenlager warenlager : list) {
            System.out.println("Warenlager: "+ warenlager.getName() +": "+ warenlager.getKapazitaetsauslastung());
        }

        System.out.println("\nDurchsuchen nach Bauteil A in Warenlager A");
        System.out.println(bauteilA.getBezeichnung() +": "+ warenlagerA.durchsuchen(bauteilA));
    }
}
