package modeloa;

import javax.swing.ImageIcon;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Bomba {
    protected int x, y;
    protected int radio;
    protected int bombaDenb = 3; // Eztanda egin aurretik duen denbora
    protected int eztandaDenb = 2; // Eztanda irauten duen denbora
    protected Laberinto laberinto;
    protected ImageIcon bombImg;
    private Timer timer;

    public Bomba(int x, int y, int radio, Laberinto laberinto) {
        this.x = x;
        this.y = y;
        this.radio = radio;
        this.laberinto = laberinto;
        this.bombImg = null;
    }

    // Get-errak
    public int getX() { return x; }
    public int getY() { return y; }
    public int getRadio() { return radio; }
    public ImageIcon getBombaIrudia() { return bombImg; }

    // Eztanda metodoa, bomba bakoitzak bere radioa izango du
    public void eztanda() {
        System.out.println("Eztanda pos (" + y + ", " + x + ")");

        // Bomba gelaxkatik kendu
        Gelaxka gelaxka = laberinto.getGelaxka(x, y);
        if (gelaxka != null) {
            gelaxka.kenduBomba();
        }
        
     // Uneko gelaxkan eztanda
        eztandaPos(x, y);

        // Radioaren arabera, alboko gelaxken eztanda
        for (int i = 1; i <= radio; i++) {
            eztandaPos(x - i, y);
            eztandaPos(x + i, y);
            eztandaPos(x, y - i);
            eztandaPos(x, y + i);
        }

        eztandaTimer();
    }
    
    // Eztandaren metodoa
    public void eztandaPos(int x, int y) {
        if (x < 0 || x >= laberinto.getColumnas() || y < 0 || y >= laberinto.getFilas()) {
            return; // Limiteen kanpo badago, ezer ez egin
        }

        Gelaxka gelaxka = laberinto.getGelaxka(x, y);
        // Bloke bat bada
        if (gelaxka.getBloke() != null) {
            Bloke bloke = gelaxka.getBloke();
            // Bloke biguna bada
            if (bloke.apurtuDaiteke()) {
                gelaxka.setBloke(null); // Blokea ezabatu
                gelaxka.suaJarri();
                System.out.println("Bloke apurtua pos (" + y + ", " + x + ")");
            }
        } else {
        	gelaxka.suaJarri();
        }
        
    }
    
    // Gelaxketatik sua kentzeko
    private void kenduSua() {
        eztandaPosKendu(x, y);
        
        for (int i = 1; i <= radio; i++) {
            eztandaPosKendu(x - i, y);
            eztandaPosKendu(x + i, y);
            eztandaPosKendu(x, y - i);
            eztandaPosKendu(x, y + i);
        }
    }
    
    // Sua kentzeko
    private void eztandaPosKendu(int x, int y) {
        if (x < 0 || x >= laberinto.getColumnas() || y < 0 || y >= laberinto.getFilas()) {
            return;
        }

        Gelaxka gelaxka = laberinto.getGelaxka(x, y);
        // Bloke gogorra den kontuan hartu
        if (gelaxka != null && (gelaxka.getBloke() == null || gelaxka.getBloke().apurtuDaiteke())) {
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
    public boolean aktiboDago() {
        return bombaDenb > 0;
    }

}
