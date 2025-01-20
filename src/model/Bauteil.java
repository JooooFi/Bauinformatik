package model;

import java.util.Objects;

public class Bauteil {
    private String bezeichnung;
    private String material;

    public Bauteil(String bezeichnung, String material) {
        this.bezeichnung = bezeichnung;
        this.material = material;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
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
}
