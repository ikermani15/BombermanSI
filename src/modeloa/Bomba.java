package modeloa;

import java.util.Timer;
import java.util.TimerTask;

public abstract class Bomba {
    private int x, y;
    private int radio;
    private int bombaDenb = 3; // Eztanda egin aurretik duen denbora
    private int eztandaDenb = 2; // Eztanda irauten duen denbora
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

    // Eztanda metodoa, bomba bakoitzak bere radioa izango du
    public void eztanda() {
    	estrategiaEztanda.eztanda(this);
        eztandaTimer();
    }
    
    public void kenduBombaEtaEztanda(int x, int y) {
        Gelaxka gelaxka = Laberinto.getLaberinto().getGelaxka(x, y);
        if (gelaxka != null) {
            gelaxka.kenduBomba();
        }
        eztandaPos(x, y);
    }

    
    // Eztandaren metodoa
    public boolean eztandaPos(int x, int y) {
        if (x < 0 || x >= Laberinto.getLaberinto().getZutabeak() || y < 0 || y >= Laberinto.getLaberinto().getIlarak()) {
            return false;
        }

        Gelaxka gelaxka = Laberinto.getLaberinto().getGelaxka(x, y);
        Bloke bloke = gelaxka.getBloke();
        if (bloke != null) {
            if (!bloke.apurtuDaiteke()) {
                return false; // BlokeGogorra bada, norabide honetan eztanda gelditu
            }
            // BlokeBiguna bada apurtu, baina eztandarekin jarraitu
            gelaxka.setBloke(null);
            Laberinto.getLaberinto().kenduBlokeBigunKop();
            //System.out.println("Bloke apurtua pos (" + y + ", " + x + ")");
        }
        
        // Gelaxkan etsaia badago
        if (gelaxka.etsaiaDago()) {
            Etsaia etsaia = gelaxka.getEtsaia();
            if (etsaia != null) {
                etsaia.hil();
                gelaxka.kenduEtsaia();
                Laberinto.getLaberinto().kenduEtsaiKop();
                System.out.println("Etsaia hil da pos (" + y + ", " + x + ")");
                System.out.println("Etsai kopuru totala: " + Laberinto.getLaberinto().getEtsaiKop());

                // Si ya no quedan enemigos, ganar el juego
                if (Laberinto.getLaberinto().getEtsaiKop() == 0) {
                    gelaxka.irabazi();
                }
            }
        }
        
        gelaxka.suaJarri(); // BlokeGogorrik ez dagoen tokian sua jarri

        // Bomberman eztanda radioan badago, partida amaitu
        if (gelaxka.bombermanDago()) {
            gelaxka.galdu();
        }

        // Etsai guztiak eliminatuz gero irabazi
        if (Laberinto.getLaberinto().getEtsaiKop() == 0) {
            gelaxka.irabazi();
        }

        return true; // Eztanda jarraitu
    }
    
    // Gelaxketatik sua kentzeko
    private void kenduSua() {
        kenduSuaPos(x, y);
        
        for (int i = 1; i <= radio; i++) {
            kenduSuaPos(x - i, y);
            kenduSuaPos(x + i, y);
            kenduSuaPos(x, y - i);
            kenduSuaPos(x, y + i);
        }
    }

    private void kenduSuaPos(int x, int y) {
        if (x < 0 || x >= Laberinto.getLaberinto().getZutabeak() || y < 0 || y >= Laberinto.getLaberinto().getIlarak()) {
            return;
        }

        Gelaxka gelaxka = Laberinto.getLaberinto().getGelaxka(x, y);
        // Irudiak ez ezabatzeko (BlokeGogorra topatuz gero)
        if (gelaxka != null && gelaxka.getBloke() == null && !gelaxka.etsaiaDago() && !gelaxka.bombermanDago() && !gelaxka.bombaDago()) {
            gelaxka.suaKendu();
        }
    }


    // Eztandarako countdown 3s
    public void bombaTimer() {
    	expTimer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                eztanda();
                //System.out.println("BlokeBigun totala: " + Laberinto.getLaberinto().getBlokeBigunKop());
            }
        };
        expTimer.schedule(timerTask, bombaDenb * 1000);
    }
    
    // Eztanda 2s
    public void eztandaTimer() {
    	eztTimer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                kenduSua();
            }
        };
        eztTimer.schedule(timerTask, eztandaDenb * 1000);
    }

}
