package modeloa;

import java.util.Random;

import javax.swing.ImageIcon;

public class EmptyLaberinto extends Laberinto {
	public static EmptyLaberinto nEL;
	
	public EmptyLaberinto() {
        laberintoaSortu();
    }
	
	public static EmptyLaberinto getEmpty() {
		if(nEL == null) {
			nEL = new EmptyLaberinto();
		}
		
		return nEL;
	}
	
	private void laberintoaSortu() {
	    Random rand = new Random();
	    
	    for (int i = 0; i < getIlarak(); i++) {
	        for (int j = 0; j < getZutabeak(); j++) {
	            int prob = rand.nextInt(100);
	            if (prob > 95 && getEtsaiKop() < 10) {
	                Etsaia etsaia = new Etsaia(j, i);
	                gehituEtsaiKop();
	                gelaxka[i][j] = new Gelaxka(j, i, null);
	                gelaxka[i][j].sortuEtsaia(etsaia);
	            } else {
	                gelaxka[i][j] = new Gelaxka(j, i, null);
	            }
	        }
	    }
	    System.out.println("Etsai kopuru totala: " + getEtsaiKop());
	}

}