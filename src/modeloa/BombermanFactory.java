package modeloa;

public class BombermanFactory {
	
	private static BombermanFactory boF;
    
    private BombermanFactory(){
    }
    
    public static BombermanFactory getBombermanFactory(){
        
        if (boF == null){
            
        	boF= new BombermanFactory();
        
        }
        
        return boF;
    
    }
    
    public Bomberman createBomberman (String mota){
        
            Bomberman nBomber= null;
            if (mota=="White"){
                
            	nBomber= new WhiteBomber();
            
            }
            
            else if (mota=="Black"){
                
            	nBomber= new BlackBomber();
            
            }
            
            return nBomber;
    
    }
	
	

}
