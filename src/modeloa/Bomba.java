package modeloa;

import javax.swing.ImageIcon;

public abstract class Bomba {
    protected int x, y;
    protected int radio;
    protected int tiempoExplosion = 3; // Segundos antes de explotar
    protected int duracionExplosion = 2; // Segundos que dura la explosión
    protected Laberinto laberinto;
    protected ImageIcon bombImg;

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

        // Uneko gelaxkan eztanda
        eztandaPos(x, y);

        // Radioaren arabera, alboko gelaxken eztanda
        for (int i = 1; i <= radio; i++) {
            eztandaPos(x - i, y);
            eztandaPos(x + i, y);
            eztandaPos(x, y - i);
            eztandaPos(x, y + i);
        }
        
        // Bomba gelaxkatik kendu
        Gelaxka gelaxka = laberinto.getGelaxka(x, y);
        if (gelaxka != null) {
            gelaxka.kenduBomba();
        }

        // Eztanda 2s
        try {
            Thread.sleep(duracionExplosion * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
 // Blokea ezabatu apurtu ahal bada
    public void eztandaPos(int x, int y) {
        if (x < 0 || x >= laberinto.getColumnas() || y < 0 || y >= laberinto.getFilas()) {
            return; // Limiteen kanpo badago, ezer ez egin
        }

        Gelaxka gelaxka = laberinto.getGelaxka(x, y);
        if (gelaxka.getBloke() != null) {
            Bloke bloke = (Bloke) gelaxka.getBloke();
            if (bloke.apurtuDaiteke()) {
                gelaxka.setBloke(null); // Blokea ezabatu
                System.out.println("Bloke apurtua pos (" + y + ", " + x + ")");
            }
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
    
    // Bomba aktiko dagoen konprobatu
    public boolean aktiboDago() {
        return tiempoExplosion > 0;
    }

}
