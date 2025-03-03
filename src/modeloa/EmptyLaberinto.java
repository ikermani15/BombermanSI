package modeloa;

import java.util.Random;

public class EmptyLaberinto extends Laberinto {
	@Override
	public void generarLaberinto() {
	    for (int i = 0; i < getFilas(); i++) {
	        for (int j = 0; j < getColumnas(); j++) {
	            laberinto[i][j] = null; // Todo vacío
	        }
	    }
	    
	    setChanged();
        notifyObservers();
	}

}
