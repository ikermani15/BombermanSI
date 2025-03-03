package modeloa;

import java.util.Random;

public class SoftLaberinto extends Laberinto {
	@Override
	public void generarLaberinto() {
	    Random rand = new Random();
	    int etsaiKop = 0;

	    for (int i = 0; i < getFilas(); i++) {
	        for (int j = 0; j < getColumnas(); j++) {
	            if ((i == 0 && j == 0) || (i == 1 && j == 0) || (i == 0 && j == 1)) {
	                laberinto[i][j] = null;
	            } else {
	                int prob = rand.nextInt(100);

	                if (prob > 40) {
	                    laberinto[i][j] = new BlokeBiguna(j, i); // Más bloques SOFT
	                } else {
	                    laberinto[i][j] = null;
	                }
	            }
	        }
	    }
	    
	    setChanged();
        notifyObservers();
	}

}
