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
    }

    // Subklaseak inplementatuko dute
    public void laberintoaChanged() {
    	setChanged();
    	notifyObservers("sortu");
    }

    public Bloke getBloke(int x, int y) {
        return laberinto[y][x];
    }
    
    public void setBloke(int x, int y, Bloke bloke) {
        laberinto[y][x] = bloke;
    }

    public ImageIcon getFondo() {
    	return fondo;
    }
    public int getColumnas() { 
    	return zutabe; 
    }
    public int getFilas() {
    	return ilara;
    }

}
