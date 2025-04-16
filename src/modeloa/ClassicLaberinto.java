package modeloa;

import java.util.Random;

import javax.swing.ImageIcon;

public class ClassicLaberinto extends Laberinto {
	public static ClassicLaberinto nCL;
	
	public ClassicLaberinto() {
        laberintoaSortu();
    }
	
	public static ClassicLaberinto getClassic() {
		if(nCL == null) {
			nCL = new ClassicLaberinto();
		}
		
		return nCL;
	}
	
	private void laberintoaSortu() {
	    Random rand = new Random();
	    BlokeFactory bFac = BlokeFactory.getBlokeFactory();
	    
	    for (int i = 0; i < getIlarak(); i++) {
            for (int j = 0; j < getZutabeak(); j++) {
                Bloke bloke = null;
                Etsaia etsaia = null;

                if ((i == 0 && j == 0) || (i == 1 && j == 0) || (i == 0 && j == 1)) {
                    bloke = null; // Hasierako gelaxkak hutsak
                } else if (i % 2 != 0 && j % 2 != 0) {
                    bloke = bFac.createBloke(i, j, false); // BlokeGogorrak posizio bakoitietan
                } else {
                    int prob = rand.nextInt(100);
                    if (prob > 40) {
                        bloke = bFac.createBloke(j, i, true);
                        gehituBlokeBigunKop();
                    } else { // Etsaiak gehitu
                        int prob2 = rand.nextInt(100);
                        if (prob2 > 90 && getEtsaiKop() < 6) {
                            etsaia = new Etsaia(j, i);
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