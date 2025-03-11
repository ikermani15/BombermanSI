package modeloa;

import java.awt.*;

import javax.swing.ImageIcon;

public abstract class Bomberman {
    private int x, y; // Matrizeko posizioa
    private int lastX, lastY;

    private final int cellSize = 40; // Gelaxka bakoitzaren tamaina
    private int bombaKop; // Bomba kop
    private int radioExplosion; // Bomba eztanda radioa
    protected Laberinto laberinto; // Laberinto erreferentzia talka lortzeko
    protected ImageIcon bombermanIrudia;

    public Bomberman(int bombaKop, int radioExplosion, String tipo) {
        this.x = 0; // (0, 0)-n hasieratu
        this.y = 0;
        this.bombaKop = bombaKop;
        this.radioExplosion = radioExplosion;
    }
    
    public void setLaberinto(Laberinto laberinto) {
        this.laberinto = laberinto;
    }

    // Mugimendu metodoak, talka konprobatuz
    public void mugituGora() { 
    	mugituPosible(x, y - 1); 
    }
    public void mugituBehera() { 
    	mugituPosible(x, y + 1); 
    }
    public void mugituEzkerra() { 
    	mugituPosible(x - 1, y); 
    }
    public void mugituEskuma() { 
    	mugituPosible(x + 1, y); 
    }
    
    // Mugitu al den konprobatu
    private void mugituPosible(int newX, int newY) {
        if (laberinto != null) {
            int filas = laberinto.getFilas();
            int columnas = laberinto.getColumnas();

            // Limiteen barruan dagoen konprobatu (matrize barruan)
            if (newX >= 0 && newX < columnas && newY >= 0 && newY < filas) {
                Bloke bloke = laberinto.getBloke(newX, newY);

                // Gelaxka hutsa bada, mugitu posible
                if (bloke == null) {
                	lastX = x;
                	lastY = y;
                    this.x = newX;
                    this.y = newY;
                    
                    // Observable-ari deitu bista eguneratu dadin
                    laberinto.mugituBomber(new int[]{lastX, lastY, x, y});
                }
            }
        }
    }

    // Bomba jartzeko
    public void bombaJarri() {
        if (bombaKop > 0) {
            System.out.println("Bomberman bomba ezarri du pos (" + x + ", " + y + ")");
            Bomba bomba = new DefaultBomba(x, y, laberinto);
            bomba.countdownHasi();
            bombaKop--;
        }
    }

    public ImageIcon getIrudia() {
        return bombermanIrudia;
    }
    
    // Posizioa lortu
    public int getXPixel() { 
    	return x * cellSize; 
    }
    public int getYPixel() { 
    	return y * cellSize; 
    }

    public int getCantidadBombas() {
        return bombaKop;
    }

    public int getRadioExplosion() {
        return radioExplosion;
    }
}
