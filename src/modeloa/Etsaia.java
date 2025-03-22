package modeloa;

import java.util.Random;

public class Etsaia {
    private int x, y;
    private String imagenSeleccionada;
    private final int cellSize = 40;
    private Random random;

    private final String[] imagenes = {
        "/img/baloon1.png",
        "/img/baloon2.png",
        "/img/doria1.png",
        "/img/doria2.png",
        "/img/pass1.png",
        "/img/pass2.png"
    };

    public Etsaia() {
        this.x = 5;
        this.y = 5;
        
        this.random = new Random();
        imagenSeleccionada = imagenes[random.nextInt(imagenes.length)];
    }

    public void moverAleatorio() {
        int direccion = random.nextInt(4);

        if (direccion == 0) {
            moverArriba();
        } else if (direccion == 1) {
            moverAbajo();
        } else if (direccion == 2) {
            moverIzquierda();
        } else if (direccion == 3) {
            moverDerecha();
        }
    }

    public void moverArriba() { if (y > 0) y--; }
    public void moverAbajo() { if (y < 10) y++; }
    public void moverIzquierda() { if (x > 0) x--; }
    public void moverDerecha() { if (x < 16) x++; }

    public int getXPixel() { return x * cellSize; }
    public int getYPixel() { return y * cellSize; }

    public String getImagen() {
        return imagenSeleccionada;
    }
}
