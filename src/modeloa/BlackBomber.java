package modeloa;

public class BlackBomber extends Bomberman {
    public BlackBomber() {
        super(1, "Black"); // 1 Ultrabomba
    }
    
    @Override
    public Bomba sortuBomba(int x, int y) {
        return new UltraBomba(x, y, 20);
    }
}
