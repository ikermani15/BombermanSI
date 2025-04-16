package modeloa;

public class BombermanFactory {
	
	private static BombermanFactory nBF;
    
    private BombermanFactory(){
    }
    
    public static BombermanFactory getBombermanFactory(){
        if (nBF == null){
        	nBF = new BombermanFactory();
        }
        
        return nBF;
    }
    
    public Bomberman createBomberman(String mota){
        if (mota.equals("White")){
            return WhiteBomber.getWhiteBomber();
        } else if (mota.equals("Black")){
            return BlackBomber.getBlackBomber();
        }
        throw new IllegalArgumentException("Bomberman mota ezezaguna: " + mota);
    }
    
}
