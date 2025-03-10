package modeloa;

public class BlokeGogorra extends Bloke {
    public BlokeGogorra(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean esDestructible() {
        return false;
    }
}
