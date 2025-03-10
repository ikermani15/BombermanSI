package modeloa;

import java.util.Observable;
import java.util.Random;

import javax.swing.ImageIcon;

public abstract class Laberinto extends Observable {
    protected final int ilara = 11; 
    protected final int zutabe = 17; 
    protected Bloke[][] laberinto; // Blokeen matrizea
    protected ImageIcon fondo;

    public Laberinto() {
        this.laberinto = new Bloke[getFilas()][getColumnas()];
        laberintoaHasieratu();
    }

    // Matrizea hasieratu bide hutsekin
    private void laberintoaHasieratu() {
        for (int i = 0; i < getFilas(); i++) {
            for (int j = 0; j < getColumnas(); j++) {
                laberinto[i][j] = null;  // Bide hutsa
            }
        }
    }

    // Subklaseak inplementatuko dute
    public void laberintoaSortu() {
    	setChanged();
    	notifyObservers("laberinto");
    }

    // Blokea lortu
    public Bloke getBloke(int x, int y) {
        return laberinto[y][x];
    }

    // Blokea ezarri eta bista notifikatu
    public void setBloke(int x, int y, Bloke bloke) {
        laberinto[y][x] = bloke;
        if(bloke != null) {
        	bloke.aldatu();
        }
    }
    
    public void explotarEn(int x, int y) {
        // Verificar que la explosión no salga fuera del laberinto
        if (x < 0 || x >= getColumnas() || y < 0 || y >= getFilas()) {
            return;
        }

        // Obtener el bloque en la celda
        Bloke bloke = getBloke(x, y);

        if (bloke != null && bloke.esDestructible()) { 
            setBloke(x, y, null);
            setChanged();
            notifyObservers(new int[]{x, y});
            System.out.println("BlokeBiguna destruido en (" + x + ", " + y + ")");
        }
    }

    public ImageIcon getFondo() {
    	return fondo;
    }
    public int getColumnas() { return zutabe; }
    public int getFilas() { return ilara; }
}
