package modeloa;

public class BlackBomber extends Bomberman {
	private static BlackBomber nBB;
	
    public BlackBomber() {
        super(1, "Black"); // 1 Ultrabomba
    }
    
    public static BlackBomber getBlackBomber() {
        if (nBB == null) {
        	nBB = new BlackBomber();
        }
        return nBB;
    }
    
    @Override
    public Bomba sortuBomba(int x, int y) {
        return new UltraBomba(x, y, 20);
    }
}
