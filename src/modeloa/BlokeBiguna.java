package modeloa;

public class BlokeBiguna extends Bloke {
    public BlokeBiguna(int x, int y) {
        super(x, y);
    }

    // Bloke biguna apurtu daiteke
    @Override
    public boolean apurtuDaiteke() {
        return true;
    }
}
