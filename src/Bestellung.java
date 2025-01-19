import java.util.ArrayList;
import java.util.List;

public class Bestellung {
    Kunde kunde;

    List<Bauteil> bauteilList = new ArrayList<>();

    public Bestellung(Kunde kunde) {
        this.kunde = kunde;
    }

    public Kunde getKunde() {
        return kunde;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

    public List<Bauteil> getBauteilList() {
        return bauteilList;
    }

    public void addBauteil(Bauteil b) {
        for (int i = 0; i < bauteilList.size(); i++) {
            Bauteil e = bauteilList.get(i);
            if (b.equals(e)) {
                e.addAnzahl(b.getAnzahl());
            }
        }
        bauteilList.add(b);

    }
}
