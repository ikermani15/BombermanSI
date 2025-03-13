package modeloa;

import java.util.Random;

import javax.swing.ImageIcon;

public class SoftLaberinto extends Laberinto {
	public SoftLaberinto() {
        fondo = new ImageIcon(getClass().getResource("/img/stageBack3.png"));
        laberintoaSortu();
    }
	
	private void laberintoaSortu() {
	    Random rand = new Random();
	    int etsaiKop = 0;

	    for (int i = 0; i < getFilas(); i++) {
	        for (int j = 0; j < getColumnas(); j++) {
	        	Bloke bloke = null;
	            if ((i == 0 && j == 0) || (i == 1 && j == 0) || (i == 0 && j == 1)) {
	                bloke = null; // Hasierako pos hutsik
	            } else {
	                int prob = rand.nextInt(100);

	                if (prob > 40) {
	                    bloke = new BlokeBiguna(j, i);
	                } else {
	                    bloke = null;
	                }
	            }
	            
	            gelaxka[i][j] = new Gelaxka(j, i, bloke);
	        }
	    }
	}

}
