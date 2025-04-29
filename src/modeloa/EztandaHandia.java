package modeloa;

public class EztandaHandia implements EztandaStrategy {

    @Override
    public void eztanda(Bomba bomba) {
        int x = bomba.getX();
        int y = bomba.getY();

        //System.out.println("Eztanda HANDIA pos (" + y + ", " + x + ")");

        bomba.kenduBombaEtaEztanda(x, y);
        eztandaPos(bomba, x, y);

        eztandaNorabidean(bomba, -1, 0);
        eztandaNorabidean(bomba, 1, 0);
        eztandaNorabidean(bomba, 0, -1);
        eztandaNorabidean(bomba, 0, 1);
    }

    private void eztandaNorabidean(Bomba bomba, int dx, int dy) {
        int x = bomba.getX();
        int y = bomba.getY();

        for (int i = 1; i <= bomba.getRadio(); i++) {
            int newX = x + i * dx;
            int newY = y + i * dy;
            if (!eztandaPos(bomba, newX, newY)) break;
        }
    }

    @Override
    public boolean eztandaPos(Bomba bomba, int x, int y) {
        if (x < 0 || x >= Jokoa.getJokoa().getLaberinto().getZutabeak() ||
            y < 0 || y >= Jokoa.getJokoa().getLaberinto().getIlarak()) return false;

        Gelaxka gelaxka = Jokoa.getJokoa().getLaberinto().getGelaxka(x, y);
        Bloke bloke = gelaxka.getBloke();

        if (bloke != null && !bloke.apurtuDaiteke()) return false;

        if (bloke != null && bloke.apurtuDaiteke()) {
            gelaxka.setBloke(null);
            Jokoa.getJokoa().getLaberinto().kenduBlokeBigunKop();
        }

        if (gelaxka.etsaiaDago()) {
            Etsaia etsaia = gelaxka.getEtsaia();
            if (etsaia != null) {
                etsaia.hil();
                gelaxka.kenduEtsaia();
                Jokoa.getJokoa().getLaberinto().kenduEtsaiKop();
                if (Jokoa.getJokoa().getLaberinto().getEtsaiKop() == 0) {
                	Jokoa.getJokoa().amaituJokoa(true);
                }
            }
        }

        gelaxka.suaJarri();

        if (gelaxka.bombermanDago()) {
        	Jokoa.getJokoa().amaituJokoa(false);
        }

        if (Jokoa.getJokoa().getLaberinto().getEtsaiKop() == 0) {
        	Jokoa.getJokoa().amaituJokoa(true);
        }

        return true;
    }

    @Override
    public void kenduSua(Bomba bomba) {
        int x = bomba.getX();
        int y = bomba.getY();

        kenduSuaPos(x, y);

        kenduNorabidean(bomba, -1, 0);
        kenduNorabidean(bomba, 1, 0);
        kenduNorabidean(bomba, 0, -1);
        kenduNorabidean(bomba, 0, 1);
    }

    private void kenduNorabidean(Bomba bomba, int dx, int dy) {
        int x = bomba.getX();
        int y = bomba.getY();

        for (int i = 1; i <= bomba.getRadio(); i++) {
            int newX = x + i * dx;
            int newY = y + i * dy;

            Gelaxka gelaxka = Jokoa.getJokoa().getLaberinto().getGelaxka(newX, newY);
            if (gelaxka != null && gelaxka.getBloke() != null && !gelaxka.getBloke().apurtuDaiteke()) {
                return;
            }

            kenduSuaPos(newX, newY);
        }
    }

    private void kenduSuaPos(int x, int y) {
        Gelaxka gelaxka = Jokoa.getJokoa().getLaberinto().getGelaxka(x, y);
        if (gelaxka != null && gelaxka.getBloke() == null &&
            !gelaxka.etsaiaDago() && !gelaxka.bombermanDago() && !gelaxka.bombaDago()) {
            gelaxka.suaKendu();
        }
    }
}
