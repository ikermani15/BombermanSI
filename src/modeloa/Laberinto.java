package modeloa;

import java.util.Observable;
import java.util.Random;

public abstract class Laberinto extends Observable {
    protected final int filas = 11; 
    protected final int columnas = 17; 
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
        inicializarLaberinto();
    }

    private void inicializarLaberinto() {
        // Llenamos la matriz con caminos vacíos
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < getColumnas(); j++) {
                laberinto[i][j] = BIDEA;
            }
        }
    }

    // Método abstracto que implementarán las subclases
    public abstract void generarLaberinto();

    // Método para obtener el tipo de celda
    public int getTipoDeCelda(int x, int y) {
        return laberinto[y][x];
    }

    // Notificar a los observadores cuando se genere un laberinto
    protected void actualizarObservadores() {
        setChanged();
        notifyObservers();
    }

	public int getColumnas() {
		return columnas;
	}
	
	public int getFilas() {
		return filas;
	}
}
