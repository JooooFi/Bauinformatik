package main;

import model.*;

import java.util.HashMap;
import java.util.Map;

public class MainB {
    public static void main(String[] args) {
        System.out.println("MainB");
        Versandzentrum versandzentrum =new Versandzentrum("Versandzentrum");
        // Schotterfirma
        Warenlager schotterfirma = new Warenlager("Schotterfirma", 47, 78);
        schotterfirma.addBauteil("C24 10x12cm", "Holz", 65);
        schotterfirma.addBauteil("C30 12x16cm", "Holz", 2);
        schotterfirma.addBauteil("Mauerziegel", "Mz 10", 53);
        schotterfirma.addBauteil("Mauerziegel", "KS 12", 150);
        System.out.println("Schotterfimra: "+schotterfirma +"\n");

        // Rostfrei Deluxe
        Warenlager rostfreiDeluxe = new Warenlager("Rostfrei Deluxe", 3, 17);
        rostfreiDeluxe.addBauteil("C30 12x16cm", "Holz", 6);
        rostfreiDeluxe.addBauteil("IPE-Profile", "Stahl S355", 7);
        System.out.println("Rostfrei Deluxe: "+rostfreiDeluxe +"\n");

        // Kieskönig
        Warenlager kieskoenig = new Warenlager("Kieskönig", 55, 85);
        kieskoenig.addBauteil("C30 12x16cm", "Holz", 4);
        kieskoenig.addBauteil("IPE-Profile", "Stahl S355", 6);
        kieskoenig.addBauteil("HEA-Profile", "Stahl S235", 8);
        System.out.println("Kieskönig: "+kieskoenig +"\n");

        // Bauschutt
        Warenlager bauschutt = new Warenlager("Bauschutt", 2, 35);
        bauschutt.addBauteil("IPE-Profile", "Stahl S355", 9);
        bauschutt.addBauteil("Mauerziegel", "Mz 10", 50);
        bauschutt.addBauteil("Mauerziegel", "KS 12", 200);
        System.out.println("Bauschutt: "+bauschutt +"\n");

        // Die Baustoffbarone
        Warenlager dieBaustoffbarone = new Warenlager("Die Baustoffbarone", 32, 57);
        dieBaustoffbarone.addBauteil("C24 10x12cm", "Holz", 10);
        dieBaustoffbarone.addBauteil("C30 12x16cm", "Holz", 35);
        dieBaustoffbarone.addBauteil("IPE-Profile", "Stahl S355", 3);
        dieBaustoffbarone.addBauteil("HEA-Profile", "Stahl S235", 5);
        dieBaustoffbarone.addBauteil("Mauerziegel", "KS 12", 300);
        System.out.println("Die Baustoffbarone: "+dieBaustoffbarone +"\n");

        // Warenlager zum Versandzentrum hinzufügen
        versandzentrum.addWarenlager(schotterfirma);
        versandzentrum.addWarenlager(rostfreiDeluxe);
        versandzentrum.addWarenlager(kieskoenig);
        versandzentrum.addWarenlager(bauschutt);
        versandzentrum.addWarenlager(dieBaustoffbarone);

        // Bestellung 1: Klaus Kiesinger
        Kunde klausKiesinger = new Kunde("Klaus Kiesinger", "1");
        Bestellung bestellung1 = new Bestellung(klausKiesinger);
        bestellung1.addBauteil("C24 10x12cm", "Holz", 26);
        bestellung1.addBauteil("C30 12x16cm", "Holz", 33);
        bestellung1.addBauteil("IPE-Profile", "Stahl S355", 44); // Y=4
        System.out.println("Kunde: "+klausKiesinger +"\n");
        System.out.println("Bestellung 1: "+bestellung1 +"\n");


        // Bestellung 2: Maximilian Schotterstein
        Kunde maximilianSchotterstein = new Kunde("Maximilian Schotterstein", "2");
        Bestellung bestellung2 = new Bestellung(maximilianSchotterstein);
        bestellung2.addBauteil("C24 10x12cm", "Holz", 73);
        bestellung2.addBauteil("C30 12x16cm", "Holz", 44); // X=4
        bestellung2.addBauteil("Mauerziegel", "Mz 10", 164); // Z=6
        bestellung2.addBauteil("Mauerziegel", "KS 12", 700);
        System.out.println("Kunde: "+maximilianSchotterstein +"\n");
        System.out.println("Bestellung 2: "+bestellung2 +"\n");


        // Bestellung 3: Clara Chrom
        Kunde claraChrom = new Kunde("Clara Chrom", "3");
        Bestellung bestellung3 = new Bestellung(claraChrom);
        bestellung3.addBauteil("C24 10x12cm", "Holz", 67);
        bestellung3.addBauteil("C30 12x16cm", "Holz", 15);
        bestellung3.addBauteil("IPE-Profile", "Stahl S355", 41);
        bestellung3.addBauteil("HEA-Profile", "Stahl S235", 17);
        bestellung3.addBauteil("Mauerziegel", "KS 12", 614);
        System.out.println("Kunde: "+claraChrom +"\n");
        System.out.println("Bestellung 3: "+bestellung3 +"\n");


        // Bestellung 4: Klaus Kiesinger (erneut)
        Bestellung bestellung4 = new Bestellung(klausKiesinger);
        bestellung4.addBauteil("IPE-Profile", "Stahl S355", 6);
        bestellung4.addBauteil("HEA-Profile", "Stahl S235", 13);
        bestellung4.addBauteil("Mauerziegel", "Mz 10", 430);
        System.out.println("Bestellung 4: "+bestellung4 +"\n");

        //fehlendeBauteile ->Defizit bei
        Map<Bauteil, Integer> bauteile = versandzentrum.berechneFehlendeBauteile(bestellung2);
        for (Bauteil b :bauteile.keySet()){
            System.out.println("Fehlendes Bauteil: " +b.getBezeichnung()+ ", " +bauteile.get(b));
        }

        //Durchsuchen nach HEA-Profile
        //dieBaustoffbarone.addBauteil("C24 10x12cm", "Holz", 10);
        Bauteil bauteilC24 = new Bauteil("C24 10x12cm", "Holz");
        System.out.println("C24 10x12cm: "+ dieBaustoffbarone.durchsuchen(bauteilC24));

        //IPE-Profile, HEA-Profile fehlen
        System.out.println(versandzentrum.berechneFehlendeBauteile(bestellung3));

        Map<Bauteil, Integer> orderMap= new HashMap<Bauteil, Integer>();
        orderMap.put(bauteilC24, 400);
        orderMap.put(new Bauteil("Mauerziegel", "KS 12" ), 30);
        System.out.println(versandzentrum.getWarenlagerListeBeiKapa(orderMap));
    }
}
