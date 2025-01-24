package model;

import java.util.Objects;

public class Bauteil {
    private final String bezeichnung;
    private final String material;

    public Bauteil(String bezeichnung, String material) {
        this.bezeichnung = bezeichnung;
        this.material = material;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }


    public String getMaterial() {
        return material;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bauteil bauteil = (Bauteil) o;
        return Objects.equals(bezeichnung, bauteil.bezeichnung) &&
                Objects.equals(material, bauteil.material);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bezeichnung, material);
    }

    @Override
    public String toString() {
        return "Bauteil{" +
                "Bezeichnung='" + bezeichnung + '\'' +
                ", Material='" + material + '\'' +
                '}';
    }
}
