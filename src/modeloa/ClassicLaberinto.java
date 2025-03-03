package modeloa;

import java.util.Random;

public class ClassicLaberinto extends Laberinto {
	@Override
	public void generarLaberinto() {
	    Random rand = new Random();
	    int etsaiKop = 0;

	    for (int i = 0; i < getFilas(); i++) {
	        for (int j = 0; j < getColumnas(); j++) {
	            // Espacios iniciales vacíos
	            if ((i == 0 && j == 0) || (i == 1 && j == 0) || (i == 0 && j == 1)) {
	                laberinto[i][j] = null;
	            } else {
	                int prob = rand.nextInt(100);

	                if (i % 2 != 0 && j % 2 != 0) {
	                    laberinto[i][j] = new BlokeGogorra(j, i);  // Bloques HARD en coordenadas impares
	                } else if (prob > 40) {
	                    laberinto[i][j] = new BlokeBiguna(j, i);  // Bloques SOFT con un 40% de probabilidad
	                } else {
	                    laberinto[i][j] = null; // Camino vacío
	                }
	            }
	        }
	    }
	    
	    setChanged();
        notifyObservers();
	}

}
