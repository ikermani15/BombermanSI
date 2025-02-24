package modeloa;

public abstract class Bomba {
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

    public int getX() { return x; }
    public int getY() { return y; }
    public int getRadio() { return radio; }

    // Método abstracto para explotar (cada bomba tendrá su propio radio)
    public abstract void explotar();

    // Simula la cuenta regresiva antes de explotar
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
