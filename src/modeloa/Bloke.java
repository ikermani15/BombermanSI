package modeloa;

import java.util.Observable;

import javax.swing.ImageIcon;

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

    // Apurtu daiteke(?)
    public boolean apurtuDaiteke() {
        return false;
    }
    
    // Gelaxka apurtzen bada, bista notifikatu
    public void apurtu() {
        setChanged();
        notifyObservers("apurtu");
    }

}
