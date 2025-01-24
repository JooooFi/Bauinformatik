package model;

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
    public String toString() {
        return "Kunde{" +
                "name='" + name + '\'' +
                ", kundennr='" + kundennr + '\'' +
                '}';
    }
}
