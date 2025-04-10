package modeloa;

public class WhiteBomber extends Bomberman {
    public WhiteBomber() {
    	super(10, "White"); // 10 bomba
    }
    
    @Override
    public Bomba sortuBomba(int x, int y) {
        return new DefaultBomba(x, y, 1);
    }
}
