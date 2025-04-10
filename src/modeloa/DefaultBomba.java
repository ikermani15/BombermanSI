package modeloa;

public class DefaultBomba extends Bomba {
    public DefaultBomba(int x, int y, int radio) {
        super(x, y,radio, new EztandaKlasikoa()); // Radio = 1, bomba normala
    }

    @Override
    public void eztanda() {
        super.eztanda();
    }
}
