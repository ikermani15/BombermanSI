package modeloa;

import java.util.Observable;
import java.util.Random;

public abstract class Laberinto extends Observable {
    protected final int ilara = 11; 
    protected final int zutabe = 17; 
    protected Bloke[][] laberinto; // Blokeen matrizea
    protected String labMota;

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

    // Metodo abstraktua, subklaseak inplementatuko dute
    public abstract void generarLaberinto();

    // Blokea lortu
    public Bloke getBloke(int x, int y) {
        return laberinto[y][x];
    }

    // Blokea ezarri eta bista notifikatu
    public void setBloke(int x, int y, Bloke bloke) {
        laberinto[y][x] = bloke;
        if (bloke != null) {
            bloke.aldatu(); // Bloke Observer-a notifikatu
        }
    }

    public int getColumnas() { return zutabe; }
    public int getFilas() { return ilara; }
}
