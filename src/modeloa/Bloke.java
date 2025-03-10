package modeloa;

import java.util.Observable;

public abstract class Bloke extends Observable {
    protected int x, y;

    public Bloke(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    // Método para actualizar la celda y notificar la vista
    public void aldatu() {
        setChanged();
        notifyObservers();
    }
    public boolean esDestructible() {
        return false;
    }
}
