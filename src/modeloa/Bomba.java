package modeloa;

import java.util.Timer;
import java.util.TimerTask;

public abstract class Bomba {
    private int x, y;
    private int radio;
    private int bombaDenb = 3; // Tiempo antes de explotar
    private int eztandaDenb = 2; // Duración del fuego
    private Timer expTimer;
    private Timer eztTimer;
    private EztandaStrategy estrategiaEztanda;

    public Bomba(int x, int y, int radio, EztandaStrategy estrategia) {
        this.x = x;
        this.y = y;
        this.radio = radio;
        this.estrategiaEztanda = estrategia;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getRadio() { return radio; }
    public boolean bombaAktiboDago() { return bombaDenb > 0; }

    public void eztanda() {
        estrategiaEztanda.eztanda(this);
        eztandaTimer();
    }

    public void kenduBombaEtaEztanda(int x, int y) {
        Gelaxka gelaxka = Laberinto.getLaberinto().getGelaxka(x, y);
        if (gelaxka != null) {
            gelaxka.kenduBomba();
        }
    }

    public void bombaTimer() {
        expTimer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                eztanda();
            }
        };
        expTimer.schedule(timerTask, bombaDenb * 1000);
    }

    public void eztandaTimer() {
        eztTimer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                estrategiaEztanda.kenduSua(Bomba.this);
            }
        };
        eztTimer.schedule(timerTask, eztandaDenb * 1000);
    }
}
