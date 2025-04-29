package modeloa;

import java.util.Random;

import javax.swing.ImageIcon;

public class SoftLaberinto extends Laberinto {
	public static SoftLaberinto nFL;
	private int normalEtsaiKop = 0;
	
	public SoftLaberinto() {
        laberintoaSortu();
    }
	
	public static SoftLaberinto getSoft() {
		if(nFL == null) {
			nFL = new SoftLaberinto();
		}
		
		return nFL;
	}
	
	private void laberintoaSortu() {
        Random rand = new Random();
        BlokeFactory bFac = BlokeFactory.getBlokeFactory();
        EtsaiaFactory eFac = EtsaiaFactory.getEtsaiaFactory();
        
        for (int i = 0; i < getIlarak(); i++) {
            for (int j = 0; j < getZutabeak(); j++) {
                Bloke bloke = null;
                Etsaia etsaia = null;

                if ((i == 0 && j == 0) || (i == 1 && j == 0) || (i == 0 && j == 1)) {
                    bloke = null;
                } else {
                    int prob = rand.nextInt(100);
                    if (prob > 40) {
                        bloke = bFac.createBloke(j, i, true);
                        gehituBlokeBigunKop();
                    } else {
                        int prob2 = rand.nextInt(100);
                        if (prob2 > 90 && getEtsaiKop() < 8) {
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
                        }
                    }
                }

                Gelaxka g = new Gelaxka(j, i, bloke);
                if (etsaia != null) {
                    g.sortuEtsaia(etsaia);
                }
                gelaxka[i][j] = g;
            }
        }
        System.out.println("BlokeBigun totala: " + getBlokeBigunKop());
        System.out.println("Etsai kopuru totala: " + getEtsaiKop());
    }
}
