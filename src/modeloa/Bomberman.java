package modeloa;

import javax.swing.*;
import java.awt.*;

public class Bomberman {
    private int x, y; // Posizioa matrizean
    private Image imagen;
    private final int cellSize = 40; // Gelaxka bakoitzaren tamaina

    public Bomberman(String tipo) {
    	// (0,0) posizioan hasieratu
        this.x = 0;
        this.y = 0;

        // Bomberman motaren arabera irudia kargatu
        if (tipo.equals("White")) {
            this.imagen = new ImageIcon(getClass().getResource("/img/whitefront1.png")).getImage();
        } else {
            this.imagen = new ImageIcon(getClass().getResource("/img/blackfront1.png")).getImage();
        }
    }

    // Mugimendu metodoak
    public void moverArriba() { if (y > 0) y--; }
    public void moverAbajo() { if (y < 10) y++; } // 11 filas, índice 10 es el último
    public void moverIzquierda() { if (x > 0) x--; }
    public void moverDerecha() { if (x < 16) x++; } // 17 columnas, índice 16 es el último

    // Posizioa lortzeko metodoak (pixeletan)
    public int getXPixel() { return x * cellSize; }
    public int getYPixel() { return y * cellSize; }

    public Image getImagen() {
        return imagen;
    }
}
