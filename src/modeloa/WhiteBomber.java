package modeloa;

public class WhiteBomber extends Bomberman {
	private static WhiteBomber nWB;
	
    public WhiteBomber() {
    	super(10, "White"); // 10 bomba
    }
    
    public static WhiteBomber getWhiteBomber() {
        if (nWB == null) {
        	nWB = new WhiteBomber();
        }
        return nWB;
    }
    
    @Override
    public Bomba sortuBomba(int x, int y) {
        return new DefaultBomba(x, y, 1);
    }
}
