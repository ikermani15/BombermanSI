package modeloa;

import java.util.Observable;
import java.util.Random;

public abstract class Laberinto extends Observable {
    protected final int ilara = 11; 
    protected final int zutabe = 17; 
    protected Bloke[][] laberinto; // MATRIZ DE BLOQUES
    protected Random random;

    public Laberinto() {
        this.laberinto = new Bloke[getFilas()][getColumnas()];
        this.random = new Random();
        laberintoaHasieratu();
    }

    // Inicializar matriz con caminos vacíos
    private void laberintoaHasieratu() {
        for (int i = 0; i < getFilas(); i++) {
            for (int j = 0; j < getColumnas(); j++) {
                laberinto[i][j] = null;  // Camino vacío
            }
        }
    }

    // Método abstracto que las subclases implementarán
    public abstract void generarLaberinto();

    // Obtener un bloque
    public Bloke getBloke(int x, int y) {
        return laberinto[y][x];
    }

    // Asignar un bloque y notificar a la vista
    public void setBloke(int x, int y, Bloke bloke) {
        laberinto[y][x] = bloke;
        if (bloke != null) {
            bloke.aldatu(); // Notifica observadores de Bloke
        }
    }

    public int getColumnas() { return zutabe; }
    public int getFilas() { return ilara; }
}
