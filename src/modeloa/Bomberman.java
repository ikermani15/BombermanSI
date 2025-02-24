package modeloa;

import java.awt.*;

public abstract class Bomberman {
    private int x, y; // Posici�n en la matriz
    private final int cellSize = 40; // Tama�o de cada celda
    private int cantidadBombas; // Cantidad de bombas
    private int radioExplosion; // Radio de explosi�n de la bomba
    protected Image imagen; // Imagen del Bomberman

    public Bomberman(int cantidadBombas, int radioExplosion, String tipo) {
        this.x = 0; // Inicializa en la posici�n (0, 0)
        this.y = 0;
        this.cantidadBombas = cantidadBombas;
        this.radioExplosion = radioExplosion;
    }

    // M�todos de movimiento
    public void moverArriba() { if (y > 0) y--; }
    public void moverAbajo() { if (y < 10) y++; } // 11 filas
    public void moverIzquierda() { if (x > 0) x--; }
    public void moverDerecha() { if (x < 16) x++; } // 17 columnas

    // Obtener la posici�n en p�xeles
    public int getXPixel() { return x * cellSize; }
    public int getYPixel() { return y * cellSize; }

    // M�todos para acceder a las bombas y radio de explosi�n
    public int getCantidadBombas() {
        return cantidadBombas;
    }

    public int getRadioExplosion() {
        return radioExplosion;
    }
}
