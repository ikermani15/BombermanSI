package modeloa;

import java.util.Random;

import javax.swing.ImageIcon;

public class ClassicLaberinto extends Laberinto {
	public ClassicLaberinto() {
        fondo = new ImageIcon(getClass().getResource("/img/stageBack1.png"));
        laberintoaSortu();
    }
	
	private void laberintoaSortu() {
	    Random rand = new Random();

	    for (int i = 0; i < getIlarak(); i++) {
            for (int j = 0; j < getZutabeak(); j++) {
                Bloke bloke = null;

                if ((i == 0 && j == 0) || (i == 1 && j == 0) || (i == 0 && j == 1)) {
                    bloke = null; // Hasierako gelaxkak hutsak
                } else if (i % 2 != 0 && j % 2 != 0) {
                    bloke = new BlokeGogorra(j, i); // BlokeGogorrak posizio bakoitietan
                } else if (rand.nextInt(100) > 40) {
                    bloke = new BlokeBiguna(j, i); // BlokeBigunak
                    gehituBlokeBigunKop();
                }

                gelaxka[i][j] = new Gelaxka(j, i, bloke);
            }
        }
	    
	    System.out.println("BlokeBigun totala: " + getBlokeBigunKop());
	}

}
