package modeloa;

import java.util.Observable;

import javax.swing.ImageIcon;

public abstract class Bomba extends Observable {
    protected int x, y;
    protected int radio;
    protected int tiempoExplosion = 3; // Segundos antes de explotar
    protected int duracionExplosion = 2; // Segundos que dura la explosión
    protected Laberinto laberinto;
    protected ImageIcon bombaImage;
    protected ImageIcon expImage;

    public Bomba(int x, int y, int radio, Laberinto laberinto) {
        this.x = x;
        this.y = y;
        this.radio = radio;
        this.laberinto = laberinto;
        this.bombaImage = new ImageIcon(getClass().getResource("/img/bomb1.png"));
        this.expImage = new ImageIcon(getClass().getResource("/img/kaBomb3.png"));
    }

    // Get-errak
    public int getX() { return x; }
    public int getY() { return y; }
    public int getRadio() { return radio; }
    public ImageIcon getBombaImagen() { return bombaImage; }
    public ImageIcon getExpImagen() { return expImage; }

    // Eztanda metodoa, bomba bakoitzak bere radioa izango du
    public void eztanda() {
        System.out.println("Eztanda pos (" + x + ", " + y + ")");

        // Uneko gelaxkan eztanda
        laberinto.eztandaPos(x, y);

        // Radioaren arabera, alboko gelaxken eztanda
        for (int i = 1; i <= radio; i++) {
            laberinto.eztandaPos(x - i, y); // Izquierda
            laberinto.eztandaPos(x + i, y); // Derecha
            laberinto.eztandaPos(x, y - i); // Arriba
            laberinto.eztandaPos(x, y + i); // Abajo
        }

        // Eztanda 2s
        try {
            Thread.sleep(duracionExplosion * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
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
