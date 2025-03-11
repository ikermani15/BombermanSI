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
    public void laberintoaChanged() {
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
        setChanged();
        notifyObservers("laberinto");
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
    
    //Bomberman mugitzen bada
    public void mugituBomber(int[] pos) {
        setChanged();
        notifyObservers(pos);
    }
    
    // Blokea ezabatu apurtu ahal bada
    public void eztandaPos(int x, int y) {
        if (x < 0 || x >= getColumnas() || y < 0 || y >= getFilas()) {
            return; // Limiteen kanpo badago, ezer ez egin
        }

        Bloke bloke = getBloke(x, y);
        if (bloke != null && bloke.apurtuDaiteke()) {
            setBloke(x, y, null); // Blokea apurtu (ezabatu)
            setChanged();
            notifyObservers(new int[]{x, y}); // Bista notifikatu
            System.out.println("Bloke destruido en (" + x + ", " + y + ")");
        }
    }

}
