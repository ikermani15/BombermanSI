package modeloa;

import java.util.Random;

import javax.swing.ImageIcon;

public class EmptyLaberinto extends Laberinto {
	public static EmptyLaberinto nEL;
	private int normalEtsaiKop = 0;
	
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
	    EtsaiaFactory eFac = EtsaiaFactory.getEtsaiaFactory();
	    
	    for (int i = 0; i < getIlarak(); i++) {
	        for (int j = 0; j < getZutabeak(); j++) {
	        	Etsaia etsaia = null;
	        	
	            int prob = rand.nextInt(100);
	            if (prob > 95 && getEtsaiKop() < 10) {
	            	String mota;
                    if (normalEtsaiKop == 2) {
                        mota = "Berezia";
                        normalEtsaiKop = 0;
                    } else {
                        mota = "Normala";
                        normalEtsaiKop++;
                    }
                    etsaia = eFac.createEtsaia(j, i, mota);
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