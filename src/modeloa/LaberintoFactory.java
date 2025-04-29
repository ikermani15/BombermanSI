package modeloa;

public class LaberintoFactory {
	private static LaberintoFactory nLF;
    
    private LaberintoFactory(){
    }
    
    public static LaberintoFactory getLaberintoFactory(){
        if (nLF == null){
        	nLF = new LaberintoFactory();
        }
        
        return nLF;
    }
    
    public Laberinto createLaberinto(String mota) {
        switch (mota) {
            case "Classic": return ClassicLaberinto.getClassic();
            case "Soft": return SoftLaberinto.getSoft();
            case "Empty": return EmptyLaberinto.getEmpty();
            default: throw new IllegalArgumentException("Laberinto mota ezezaguna: " + mota);
        }
    }
    
}