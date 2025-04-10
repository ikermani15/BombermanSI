package modeloa;

public class EztandaKlasikoa implements EztandaStrategy {

    @Override
    public void eztanda(Bomba bomba) {
        int x = bomba.getX();
        int y = bomba.getY();

        System.out.println("Eztanda KLASIKO pos (" + y + ", " + x + ")");

        bomba.kenduBombaEtaEztanda(x, y);
        bomba.eztandaPos(x - 1, y);
        bomba.eztandaPos(x + 1, y);
        bomba.eztandaPos(x, y - 1);
        bomba.eztandaPos(x, y + 1);
    }
}
