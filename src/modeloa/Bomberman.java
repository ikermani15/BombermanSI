package modeloa;

import java.awt.*;

public abstract class Bomberman {
    private int x, y; // Posición en la matriz
    private final int cellSize = 40; // Tamaño de cada celda
    private int cantidadBombas; // Cantidad de bombas
    private int radioExplosion; // Radio de explosión de la bomba
    protected Image imagen; // Imagen del Bomberman

    public Bomberman(int cantidadBombas, int radioExplosion, String tipo) {
        this.x = 0; // Inicializa en la posición (0, 0)
        this.y = 0;
        this.cantidadBombas = cantidadBombas;
        this.radioExplosion = radioExplosion;
    }

    // Métodos de movimiento
    public void moverArriba() { if (y > 0) y--; }
    public void moverAbajo() { if (y < 10) y++; } // 11 filas
    public void moverIzquierda() { if (x > 0) x--; }
    public void moverDerecha() { if (x < 16) x++; } // 17 columnas

    // Obtener la posición en píxeles
    public int getXPixel() { return x * cellSize; }
    public int getYPixel() { return y * cellSize; }

    // Métodos para acceder a las bombas y radio de explosión
    public int getCantidadBombas() {
        return cantidadBombas;
    }

    public int getRadioExplosion() {
        return radioExplosion;
    }
}
