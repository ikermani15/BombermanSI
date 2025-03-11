package modeloa;

import javax.swing.ImageIcon;

public abstract class Bloke {
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

    public boolean esDestructible() {
        return false;
    }
}
