import java.util.Objects;

public class Bauteil {
    private String bezeichnung;
    private String material;
    private int anzahl;

    public Bauteil(String bezeichnung, String material, int anzahl) {
        this.bezeichnung = bezeichnung;
        this.material = material;
        this.anzahl = anzahl;
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

    public int getAnzahl() {
        return anzahl;
    }

    public void addAnzahl(int anzahl) {
        this.anzahl += anzahl;
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
}
