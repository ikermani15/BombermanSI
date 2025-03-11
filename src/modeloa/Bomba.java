package modeloa;

import java.util.Observable;

import javax.swing.ImageIcon;

public abstract class Bomba extends Observable {
    protected int x, y;
    protected int radio;
    protected int tiempoExplosion = 3; // Segundos antes de explotar
    protected int duracionExplosion = 2; // Segundos que dura la explosión
    protected Laberinto laberinto;

    public Bomba(int x, int y, int radio, Laberinto laberinto) {
        this.x = x;
        this.y = y;
        this.radio = radio;
        this.laberinto = laberinto;
    }

    // Get-errak
    public int getX() { return x; }
    public int getY() { return y; }
    public int getRadio() { return radio; }

    // Eztanda metodoa, bomba bakoitzak bere radioa izango du
    public void eztanda() {
        System.out.println("Eztanda pos (" + y + ", " + x + ")");

        // Uneko gelaxkan eztanda
        eztandaPos(x, y);

        // Radioaren arabera, alboko gelaxken eztanda
        for (int i = 1; i <= radio; i++) {
            eztandaPos(x - i, y);
            eztandaPos(x + i, y);
            eztandaPos(x, y - i);
            eztandaPos(x, y + i);
        }

        // Eztanda 2s
        try {
            Thread.sleep(duracionExplosion * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        

        setChanged();
        notifyObservers("eztanda");
    }
    
 // Blokea ezabatu apurtu ahal bada
    public void eztandaPos(int x, int y) {
        if (x < 0 || x >= laberinto.getColumnas() || y < 0 || y >= laberinto.getFilas()) {
            return; // Limiteen kanpo badago, ezer ez egin
        }

        Bloke bloke = laberinto.getBloke(x, y);
        if (bloke != null && bloke.apurtuDaiteke()) {
        	bloke.apurtu(); // Blokea apurtu dela notifikatu bistari
            laberinto.setBloke(x, y, null); // Blokea ezabatu
            System.out.println("Bloke apurtua pos (" + y + ", " + x + ")");
        }
    }

    // Eztandarako countdown 3s
    public void countdownHasi() {
        new Thread(() -> {
            try {
                Thread.sleep(tiempoExplosion * 1000);
                eztanda();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
