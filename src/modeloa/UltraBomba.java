package modeloa;

public class UltraBomba extends Bomba {
    public UltraBomba(int x, int y, int radio) {
        super(x, y,radio, new EztandaKlasikoa()); // Radio = 1, bomba normala
    }

    @Override
    public void eztanda() {
        super.eztanda();
    }
}
