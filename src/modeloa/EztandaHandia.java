package modeloa;

import java.util.function.*;

public class EztandaHandia implements EztandaStrategy {

    @Override
    public void eztanda(Bomba bomba) {
        int x = bomba.getX();
        int y = bomba.getY();

        bomba.kenduBombaEtaEztanda(x, y);
        eztandaPos(bomba, x, y);

        int[][] norabideak = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] d : norabideak) {
            eztandaNorabidean(bomba, d[0], d[1]);
        }
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
        Laberinto lab = Jokoa.getJokoa().getLaberinto();

        

        if (x < 0 || x >= lab.getZutabeak() || y < 0 || y >= lab.getIlarak()) return false;

        Gelaxka gelaxka = lab.getGelaxka(x, y);
        Bloke bloke = gelaxka.getBloke();

        if (bloke != null && !bloke.apurtuDaiteke()) return false;

        if (bloke != null && bloke.apurtuDaiteke()) {
            gelaxka.setBloke(null);
            lab.kenduBlokeBigunKop();
        }

        if (gelaxka.etsaiaDago()) {
            Etsaia etsaia = gelaxka.getEtsaia();
            if (etsaia != null) {
                etsaia.hil();
                gelaxka.kenduEtsaia();
                lab.kenduEtsaiKop();

                if (lab.getEtsaiKop() == 0) {
                    Jokoa.getJokoa().amaituJokoa(true);
                }
            }
        }

        gelaxka.suaJarri();

        if (gelaxka.bombermanDago()) {
            Jokoa.getJokoa().amaituJokoa(false);
        }

        if (lab.getEtsaiKop() == 0) {
            Jokoa.getJokoa().amaituJokoa(true);
        }

        return true;
    }

    @Override
    public void kenduSua(Bomba bomba) {
        int x = bomba.getX();
        int y = bomba.getY();

        kenduSuaPos(x, y);

        int[][] norabideak = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] d : norabideak) {
            kenduNorabidean(bomba, d[0], d[1]);
        }
    }

    private void kenduNorabidean(Bomba bomba, int dx, int dy) {
        int x = bomba.getX();
        int y = bomba.getY();
        Laberinto lab = Jokoa.getJokoa().getLaberinto();

        for (int i = 1; i <= bomba.getRadio(); i++) {
            int newX = x + i * dx;
            int newY = y + i * dy;

            Gelaxka gelaxka = lab.getGelaxka(newX, newY);
            if (gelaxka != null && gelaxka.getBloke() != null && !gelaxka.getBloke().apurtuDaiteke()) {
                return;
            }

            kenduSuaPos(newX, newY);
        }
    }

    private void kenduSuaPos(int x, int y) {
        Gelaxka gelaxka = Jokoa.getJokoa().getLaberinto().getGelaxka(x, y);

        Predicate<Gelaxka> suaKendaDaiteke = g ->
            g != null && g.getBloke() == null &&
            !g.etsaiaDago() && !g.bombermanDago() && !g.bombaDago();

        if (suaKendaDaiteke.test(gelaxka)) {
            gelaxka.suaKendu();
        }
    }
}
