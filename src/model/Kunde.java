package model;

import java.util.Objects;

public class Kunde {
    private final String name;
    private final String kundennr;

    public Kunde(String name, String kundennr) {
        this.name = name;
        this.kundennr = kundennr;
    }

    public String getName() {
        return name;
    }

    public String getKundennr() {
        return kundennr;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Kunde kunde = (Kunde) o;
        return Objects.equals(name, kunde.name) && Objects.equals(kundennr, kunde.kundennr);
        // wenn kundennr gleich ist und der name des kunden gleich mit den verglichen objekt wird true zurückgegeben
    }

    @Override
    public String toString() {
        return "Kunde{" + "name='" + name + '\'' + ", kundennr='" + kundennr + '\'' + '}';
    }
}
