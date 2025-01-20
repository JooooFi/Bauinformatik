package model;

public class Kunde {
    private String name;
    private String kundennr;

    public Kunde(String name, String kundennr) {
        this.name = name;
        this.kundennr = kundennr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKundennr() {
        return kundennr;
    }

    public void setKundennr(String kundennr) {
        this.kundennr = kundennr;
    }
}
