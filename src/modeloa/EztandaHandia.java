package modeloa;

public class EztandaHandia implements EztandaStrategy {

    @Override
    public void eztanda(Bomba bomba) {
        int x = bomba.getX();
        int y = bomba.getY();

        System.out.println("Eztanda HANDIA pos (" + y + ", " + x + ")");

        bomba.kenduBombaEtaEztanda(x, y);

        // Norabide bakoitzean luzatu eztanda
        eztandaNorabidean(bomba, -1, 0); // Ezkerra
        eztandaNorabidean(bomba, 1, 0);  // Eskuma
        eztandaNorabidean(bomba, 0, -1); // Goian
        eztandaNorabidean(bomba, 0, 1);  // Behean
    }

    // Eztanda norabide batean
    private void eztandaNorabidean(Bomba bomba, int dx, int dy) {
        int x = bomba.getX();
        int y = bomba.getY();

        for (int i = 1; i <= bomba.getRadio(); i++) {
            int newX = x + i * dx;
            int newY = y + i * dy;
            if (!bomba.eztandaPos(newX, newY)) break;
        }
    }
}
