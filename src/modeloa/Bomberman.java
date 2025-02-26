package modeloa;

import java.awt.*;
import java.util.Observable;

public abstract class Bomberman extends Observable{
    private int x, y; // Matrizeko posizioa
    private final int cellSize = 40; // Gelaxka bakoitzaren tamaina
    private int cantidadBombas; // Bomba kop
    private int radioExplosion; // Bomba eztanda radioa
    protected Image imagen; // Bomberman irudia
    protected Laberinto laberinto; // Laberinto erreferentzia talka lortzeko

    public Bomberman(int cantidadBombas, int radioExplosion, String tipo) {
        this.x = 0; // (0, 0)-n hasieratu
        this.y = 0;
        this.cantidadBombas = cantidadBombas;
        this.radioExplosion = radioExplosion;
    }
    
    public void setLaberinto(Laberinto laberinto) {
        this.laberinto = laberinto;
    }

    // Mugimendu metodoak, talka konprobatuz
    public void mugituGora() { mugituPosible(x, y - 1); }
    public void mugituBehera() { mugituPosible(x, y + 1); }
    public void mugituEzkerra() { mugituPosible(x - 1, y); }
    public void mugituEskuma() { mugituPosible(x + 1, y); }
    
    // Mugitu al den konprobatu
    private void mugituPosible(int newX, int newY) {
        if (laberinto != null) {
            int filas = laberinto.getFilas();
            int columnas = laberinto.getColumnas();

            // Limiteen barruan dagoen konprobatu (matrize barruan)
            if (newX >= 0 && newX < columnas && newY >= 0 && newY < filas) {
                Bloke bloke = laberinto.getBloke(newX, newY);

                // Si la celda está vacía, permitir el movimiento
                if (bloke == null) {
                    this.x = newX;
                    this.y = newY;

                    setChanged();
                    notifyObservers();
                }
            }
        }
    }

    // Posizioa pixeletan lortu
    public int getXPixel() { return x * cellSize; }
    public int getYPixel() { return y * cellSize; }

    public int getCantidadBombas() {
        return cantidadBombas;
    }

    public int getRadioExplosion() {
        return radioExplosion;
    }
}
