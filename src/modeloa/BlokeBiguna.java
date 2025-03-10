package modeloa;

public class BlokeBiguna extends Bloke {
    public BlokeBiguna(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean esDestructible() {
        return true;
    }
}
