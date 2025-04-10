package modeloa;

public class EztandaKlasikoa implements EztandaStrategy {

    @Override
    public void eztanda(Bomba bomba) {
        int x = bomba.getX();
        int y = bomba.getY();
        int radio = bomba.getRadio();

        System.out.println("Eztanda pos (" + y + ", " + x + ")");

        Gelaxka gelaxka = Laberinto.getLaberinto().getGelaxka(x, y);
        if (gelaxka != null) {
            gelaxka.kenduBomba();
        }

        bomba.eztandaPos(x, y);

        for (int i = 1; i <= radio; i++) {
            if (!bomba.eztandaPos(x - i, y)) break;
        }
        for (int i = 1; i <= radio; i++) {
            if (!bomba.eztandaPos(x + i, y)) break;
        }
        for (int i = 1; i <= radio; i++) {
            if (!bomba.eztandaPos(x, y - i)) break;
        }
        for (int i = 1; i <= radio; i++) {
            if (!bomba.eztandaPos(x, y + i)) break;
        }
    }
}
