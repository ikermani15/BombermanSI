package modeloa;

import java.util.Timer;
import java.util.TimerTask;
import java.util.function.*;

public abstract class Bomberman {
    private int x, y;
    private int bombaKop;
    private int denbRegenBomba = 3;
    private boolean regenBomba = false;
    private Timer timer;
    private String mota;

    public Bomberman(int bombaKop, String mota) {
        this.bombaKop = bombaKop;
        this.mota = mota;
    }

    public String getBombermanMota() { return this.mota; }
    public Bomba sortuBomba(int x, int y) { return new DefaultBomba(x, y, 1); }
    public boolean regenAktibo() { return regenBomba; }
    public int getBombaKop() { return bombaKop; }

    public void setPosizioa(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void mugituGora() { mugituPosible(x, y - 1, "gora"); }
    public void mugituBehera() { mugituPosible(x, y + 1, "behera"); }
    public void mugituEzkerra() { mugituPosible(x - 1, y, "ezkerra"); }
    public void mugituEskuma() { mugituPosible(x + 1, y, "eskuma"); }

    private void mugituPosible(int newX, int newY, String norabidea) {
        Laberinto lab = Jokoa.getJokoa().getLaberinto();

        Predicate<int[]> posPosible = pos ->
            pos[0] >= 0 && pos[0] < lab.getZutabeak() &&
            pos[1] >= 0 && pos[1] < lab.getIlarak();

        BiPredicate<Gelaxka, Gelaxka> gelaxkaLibre = (berria, _) ->
            berria.hutsikDago() && !berria.bombaDago();

        Consumer<String> gehituBomber = dir -> {
            Gelaxka berria = lab.getGelaxka(newX, newY);
            if (getBombermanMota().equals("White")) {
                berria.gehituWhiteBomberman(dir);
            } else {
                berria.gehituBlackBomberman(dir);
            }
        };

        int[] pos = { newX, newY };
        if (lab != null && posPosible.test(pos)) {
            Gelaxka unekoa = lab.getGelaxka(x, y);
            Gelaxka berria = lab.getGelaxka(newX, newY);

            if (gelaxkaLibre.test(berria, unekoa)) {
                unekoa.kenduBomberman();
                this.x = newX;
                this.y = newY;
                gehituBomber.accept(norabidea);

                if (berria.suaDago() || berria.etsaiaDago()) {
                    Jokoa.getJokoa().amaituJokoa(false);
                }
            }
        }
    }

    public void bombaJarri() {
        Runnable ezarriBomba = () -> {
            Gelaxka unekoa = Jokoa.getJokoa().getLaberinto().getGelaxka(x, y);
            Bomba bomba = sortuBomba(x, y);
            bomba.bombaTimer();
            unekoa.gehituBomba(bomba);
            bombaKop--;
        };

        if (bombaKop > 0) {
            ezarriBomba.run();
        } else if (!regenAktibo()) {
            bombaRegeneratu();
        }
    }

    private void bombaRegeneratu() {
        regenBomba = true;
        System.out.println("Bomba regeneratzen...");
        timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                bombaKop++;
                regenBomba = false;
                System.out.println("Bomba bat erregeneratu da, oraingo kopurua: " + bombaKop);
            }
        }, denbRegenBomba * 1000);
    }
}
