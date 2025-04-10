package modeloa;

public class LaberintoFactory {
	
	private static LaberintoFactory laF;
    
    private LaberintoFactory(){
    }
    
    public static LaberintoFactory getLaberintoFactory(){
        if (laF == null){
        	laF = new LaberintoFactory();
        }
        
        return laF;
    }
    
    public Laberinto createLaberinto (String mota){
    	Laberinto nLab= null;
    	if (mota.equals("Classic")){
    		nLab = new ClassicLaberinto();
        } else if (mota.equals("Soft")){
        	nLab = new SoftLaberinto();
        } else if (mota.equals("Empty")){
        	nLab = new EmptyLaberinto();
        }
    	
    	return nLab;
    }
    
}