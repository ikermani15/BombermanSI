package modeloa;

import javax.swing.ImageIcon;

public abstract class Bomba {
    protected int x, y;
    protected int radio;
    protected int tiempoExplosion = 3; // Segundos antes de explotar
    protected int duracionExplosion = 2; // Segundos que dura la explosi�n
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

    // M�todo para explotar (cada bomba tendr� su propio radio)
    public void explotar() {
        System.out.println("Explotando en (" + x + ", " + y + ")");

        // Explosi�n en la celda actual
        laberinto.explotarEn(x, y);

        // Explosi�n en las celdas adyacentes (seg�n el radio)
        for (int i = 1; i <= radio; i++) {
            laberinto.explotarEn(x - i, y); // Izquierda
            laberinto.explotarEn(x + i, y); // Derecha
            laberinto.explotarEn(x, y - i); // Arriba
            laberinto.explotarEn(x, y + i); // Abajo
        }

        // La explosi�n dura un par de segundos
        try {
            Thread.sleep(duracionExplosion * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Iniciar la cuenta regresiva para la explosi�n
    public void iniciarCuentaRegresiva() {
        new Thread(() -> {
            try {
                Thread.sleep(tiempoExplosion * 1000);
                explotar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
