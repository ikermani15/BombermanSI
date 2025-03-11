package modeloa;

import java.util.Random;

import javax.swing.ImageIcon;

public class ClassicLaberinto extends Laberinto {
	public ClassicLaberinto() {
        fondo = new ImageIcon(getClass().getResource("/img/stageBack1.png"));
    }
	
	@Override
	public void laberintoaChanged() {
	    Random rand = new Random();
	    int etsaiKop = 0;

	    for (int i = 0; i < getFilas(); i++) {
	        for (int j = 0; j < getColumnas(); j++) {
	        	// Hasierako blokeak hutsak
	            if ((i == 0 && j == 0) || (i == 1 && j == 0) || (i == 0 && j == 1)) {
	                laberinto[i][j] = null;
	            } else {
	                int prob = rand.nextInt(100);

	                if (i % 2 != 0 && j % 2 != 0) {
	                    laberinto[i][j] = new BlokeGogorra(j, i);  // BlokeGogorrak bakoiti posizioetan
	                } else if (prob > 40) {
	                    laberinto[i][j] = new BlokeBiguna(j, i);  // BlokeBigunak ezarri
	                } else {
	                    laberinto[i][j] = null; // Bidea
	                }
	            }
	        }
	    }
	}

}
