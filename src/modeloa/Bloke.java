package modeloa;

import javax.swing.ImageIcon;
import java.util.Observable;
import java.util.Random;

public abstract class Bloke extends Observable {
    protected int x, y;
    protected ImageIcon blokeIrudia;

    public Bloke(int x, int y) {
        this.x = x;
        this.y = y;
        this.blokeIrudia = null;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public ImageIcon getBlokeIrudia() { return blokeIrudia; }

    // Bista eguneratu
    public void aldatu() {
        setChanged();
        notifyObservers();
    }

    public boolean esDestructible() {
        return false;
    }
}
