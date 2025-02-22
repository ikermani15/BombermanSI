package modeloa;

import java.util.Random;

public class Etsaia {
    private int x, y; // Posición en la cuadrícula
    private String imagenSeleccionada; // Ahora guardamos la ruta de la imagen seleccionada
    private final int cellSize = 40; // Tamaño de cada celda
    private Random random;

    // Lista de imágenes disponibles
    private final String[] imagenes = {
        "/img/baloon1.png",
        "/img/baloon2.png",
        "/img/doria1.png",
        "/img/doria2.png",
        "/img/pass1.png",
        "/img/pass2.png"
    };

    public Etsaia() {
        this.x = 5; // Posición inicial del enemigo (puedes cambiarlo)
        this.y = 5;
        
        this.random = new Random();
        // Elegir una imagen aleatoria para el enemigo
        imagenSeleccionada = imagenes[random.nextInt(imagenes.length)];
    }

    // Movimiento aleatorio del enemigo
    public void moverAleatorio() {
        int direccion = random.nextInt(4); // 0 = arriba, 1 = abajo, 2 = izquierda, 3 = derecha

        switch (direccion) {
            case 0 -> moverArriba();
            case 1 -> moverAbajo();
            case 2 -> moverIzquierda();
            case 3 -> moverDerecha();
        }
    }

    // Métodos de movimiento
    public void moverArriba() { if (y > 0) y--; }
    public void moverAbajo() { if (y < 10) y++; }
    public void moverIzquierda() { if (x > 0) x--; }
    public void moverDerecha() { if (x < 16) x++; }

    // Métodos para obtener la posición en píxeles
    public int getXPixel() { return x * cellSize; }
    public int getYPixel() { return y * cellSize; }

    // Obtener la ruta de la imagen seleccionada
    public String getImagen() {
        return imagenSeleccionada;
    }
}
