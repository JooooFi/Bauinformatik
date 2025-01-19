import java.util.ArrayList;
import java.util.List;

public class Versandzentrum {
    private String name;
    private List<Bestellung> bestellungsliste = new ArrayList<>();

    public Versandzentrum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Bestellung> getBestellungsliste() {
        return bestellungsliste;
    }

    public void setBestellungsliste(List<Bestellung> bestellungsliste) {
        this.bestellungsliste = bestellungsliste;
    }

    public void addBestellung(Bestellung b) {
        bestellungsliste.forEach((bestellung -> {
            if (bestellung.kunde.getKundennr().equals(b.kunde.getKundennr())) {
                bestellung.setBauteilList(b.getBauteilList());
            }
        }));
        bestellungsliste.add(b);
    }
}
