package modeloa;

public abstract class Bloke {
	protected int x, y;

    public Bloke(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }
}
