package modeloa;

public class BlokeFactory {
    private static BlokeFactory nBF;

    private BlokeFactory() {}

    public static BlokeFactory getBlokeFactory() {
        if (nBF == null) {
        	nBF = new BlokeFactory();
        }
        return nBF;
    }

    public Bloke createBloke(int x, int y, boolean apurtuDaiteke) {
        if (apurtuDaiteke) {
            return new BlokeBiguna(x, y);
        } else {
            return new BlokeGogorra(x, y);
        }
    }
}
