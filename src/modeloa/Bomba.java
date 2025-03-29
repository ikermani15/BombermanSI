package modeloa;

import java.util.Timer;
import java.util.TimerTask;

public abstract class Bomba {
    private int x, y;
    private int radio;
    private int bombaDenb = 3; // Eztanda egin aurretik duen denbora
    private int eztandaDenb = 2; // Eztanda irauten duen denbora
    private Timer timer;

    public Bomba(int x, int y, int radio) {
        this.x = x;
        this.y = y;
        this.radio = radio;
    }

    // Get-errak
    public int getX() { return x; }
    public int getY() { return y; }
    public int getRadio() { return radio; }

    // Eztanda metodoa, bomba bakoitzak bere radioa izango du
    public void eztanda() {
        System.out.println("Eztanda pos (" + y + ", " + x + ")");

        // Bomba gelaxkatik kendu
        Gelaxka gelaxka = Laberinto.getLaberinto().getGelaxka(x, y);
        if (gelaxka != null) {
            gelaxka.kenduBomba();
        }
        
        // Uneko gelaxkan eztanda
        eztandaPos(x, y);

        // Radioaren arabera, alboko gelaxken eztanda
        // Ezkerra (-x)
        for (int i = 1; i <= radio; i++) {
            if (!eztandaPos(x - i, y)) break; 
        }

        // Eskuma (+x)
        for (int i = 1; i <= radio; i++) {
            if (!eztandaPos(x + i, y)) break; 
        }

        // Gora (-y)
        for (int i = 1; i <= radio; i++) {
            if (!eztandaPos(x, y - i)) break; 
        }

        // Behera (+y)
        for (int i = 1; i <= radio; i++) {
            if (!eztandaPos(x, y + i)) break; 
        }

        eztandaTimer();
    }
    
    // Eztandaren metodoa
    public boolean eztandaPos(int x, int y) {
        if (x < 0 || x >= Laberinto.getLaberinto().getZutabeak() || y < 0 || y >= Laberinto.getLaberinto().getIlarak()) {
            return false;
        }

        Gelaxka gelaxka = Laberinto.getLaberinto().getGelaxka(x, y);

        // Bloke bat badago
        Bloke bloke = gelaxka.getBloke();
        if (bloke != null) {
            if (!bloke.apurtuDaiteke()) {
                return false; // BlokeGogorra bada, norabide honetan eztanda gelditu
            }

            // BlokeBiguna bada apurtu, baina eztandarekin jarraitu
            gelaxka.setBloke(null);
            Laberinto.getLaberinto().kenduBlokeBigunKop();
            gelaxka.suaJarri();
            
            System.out.println("Bloke apurtua pos (" + y + ", " + x + ")");
        } else {
            gelaxka.suaJarri(); // Blokerik ez badago sua jarri
        }

        // Bomberman eztanda radioan badago, partida amaitu
        if (gelaxka.getBomberman() != null) {
            gelaxka.galdu();
        }

        // BlokeBigun guztiak apurtuz gero irabazi
        if (Laberinto.getLaberinto().getBlokeBigunKop() == 0) {
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
        // Blokeak ez ezabatzeko
        if (gelaxka != null && gelaxka.getBloke() == null) {
            gelaxka.suaKendu();
        }
    }

    // Eztandarako countdown 3s
    public void bombaTimer() {
    	timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                eztanda();
                System.out.println("BlokeBigun totala: " + Laberinto.getLaberinto().getBlokeBigunKop());
            }
        };
        timer.schedule(timerTask, bombaDenb * 1000);
    }
    
    // Eztanda 2s
    public void eztandaTimer() {
    	timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                kenduSua();
            }
        };
        timer.schedule(timerTask, eztandaDenb * 1000);
    }
    
    // Bomba aktiko dagoen konprobatu
    public boolean bombaAktiboDago() {
        return bombaDenb > 0;
    }

}
