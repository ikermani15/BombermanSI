package modeloa;

import java.util.Timer;
import java.util.TimerTask;
import java.util.function.*;

public abstract class Etsaia {
    protected int x, y;
    private final int cellSize = 40;
    private Timer mugTimer;
    private String mota;

    public Etsaia(int x, int y, String mota) {
        this.x = x;
        this.y = y;
        this.mota = mota;
    }

    public String getEtsaiMota() {
        return mota;
    }

    public void abiaraziEtsaia() {
        mugTimer = new Timer();
        TimerTask mugimenduTask = new TimerTask() {
            @Override
            public void run() {
                mugituAleatorio();
            }
        };
        mugTimer.scheduleAtFixedRate(mugimenduTask, 0, 1000); // Mugitu segundoro
    }

    public void hil() {
        if (mugTimer != null) {
            mugTimer.cancel();
        }
    }

    protected boolean mugituPosible(int newX, int newY) {
        Jokoa jokoa = Jokoa.getJokoa();
        Laberinto lab = jokoa.getLaberinto();

        Predicate<Gelaxka> posizioEgokia = g ->
            g.hutsikDago() && !g.bombaDago() && !g.etsaiaDago();

        Consumer<Gelaxka> gehituEtsaia = g -> {
            if ("Normala".equals(getEtsaiMota())) {
                g.gehituEtsaiNormala();
            } else {
                g.gehituEtsaiBerezia();
            }
        };

        Runnable jokalariHiltzenBada = () -> jokoa.amaituJokoa(false);

        Consumer<Gelaxka> suaProzesatu = g -> {
            hil();
            g.kenduEtsaia();
            lab.kenduEtsaiKop();
            System.out.printf("Etsaia hil da pos (%d, %d)%n", y, x);
            System.out.println("Etsai kopuru totala: " + lab.getEtsaiKop());
            if (lab.getEtsaiKop() == 0) {
                jokoa.amaituJokoa(true);
            }
        };

        if (newX >= 0 && newX < lab.getZutabeak() && newY >= 0 && newY < lab.getIlarak()) {
            Gelaxka unekoa = lab.getGelaxka(x, y);
            Gelaxka berria = lab.getGelaxka(newX, newY);

            if (posizioEgokia.test(berria)) {
                unekoa.kenduEtsaia();
                x = newX;
                y = newY;
                gehituEtsaia.accept(berria);

                if (berria.bombermanDago()) {
                    jokalariHiltzenBada.run();
                } else if (berria.suaDago()) {
                    suaProzesatu.accept(berria);
                }
                return true;
            }
        }
        return false;
    }

    public abstract void mugituAleatorio();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getXPixel() {
        return x * cellSize;
    }

    public int getYPixel() {
        return y * cellSize;
    }
}
