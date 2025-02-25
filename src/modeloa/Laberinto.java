package modeloa;

import java.util.Observable;
import java.util.Random;

public abstract class Laberinto extends Observable {
    protected final int ilara = 11; 
    protected final int zutabe = 17; 
    protected int[][] laberinto; 
    protected Random random;
    protected int etsaiaKop = 0; 

    // Tipos de celdas
    public static final int BIDEA = 0;
    public static final int HARD = 1;
    public static final int SOFT = 2;
    public static final int ENEMY = 3;

    public Laberinto() {
        this.laberinto = new int[getFilas()][getColumnas()];
        this.random = new Random();
        laberintoaHasieratu();
    }

    // Laberintoa hasieratzeko metodoa
    private void laberintoaHasieratu() {
    	// Matrizea bide hutsekin bete
        for (int i = 0; i < getFilas(); i++) {
            for (int j = 0; j < getColumnas(); j++) {
                laberinto[i][j] = BIDEA;
            }
        }
    }

    // Metodo abstraktua, subklaseak inplementatu
    public abstract void generarLaberinto();

    // Gelaxka mota lortzeko metodoa
    public int getGelaxkaMota(int x, int y) {
        return laberinto[y][x];
    }

    // Observer-ari notifikatu laberintoa generatzean
    protected void actualizarObservadores() {
        setChanged();
        notifyObservers();
    }

	public int getColumnas() {
		return zutabe;
	}
	
	public int getFilas() {
		return ilara;
	}
}
