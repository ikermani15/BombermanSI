package modeloa;

import java.util.Observable;
import java.util.Random;

public class Etsaia extends Observable {
    private int x, y; // Posici�n en la cuadr�cula
    private String imagenSeleccionada; // Ahora guardamos la ruta de la imagen seleccionada
    private final int cellSize = 40; // Tama�o de cada celda
    private Random random;

    // Lista de im�genes disponibles
    private final String[] imagenes = {
        "/img/baloon1.png",
        "/img/baloon2.png",
        "/img/doria1.png",
        "/img/doria2.png",
        "/img/pass1.png",
        "/img/pass2.png"
    };

    public Etsaia() {
        this.x = 5; // Posici�n inicial del enemigo (puedes cambiarlo)
        this.y = 5;
        
        this.random = new Random();
        // Elegir una imagen aleatoria para el enemigo
        imagenSeleccionada = imagenes[random.nextInt(imagenes.length)];
    }

    // Movimiento aleatorio del enemigo
    public void moverAleatorio() {
        int direccion = random.nextInt(4); // 0 = arriba, 1 = abajo, 2 = izquierda, 3 = derecha

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


    // M�todos de movimiento
    public void moverArriba() { if (y > 0) y--; }
    public void moverAbajo() { if (y < 10) y++; }
    public void moverIzquierda() { if (x > 0) x--; }
    public void moverDerecha() { if (x < 16) x++; }

    // M�todos para obtener la posici�n en p�xeles
    public int getXPixel() { return x * cellSize; }
    public int getYPixel() { return y * cellSize; }

    // Obtener la ruta de la imagen seleccionada
    public String getImagen() {
        return imagenSeleccionada;
    }
}
