package modeloa;

import java.util.Random;

import javax.swing.ImageIcon;

public class EmptyLaberinto extends Laberinto {
	public EmptyLaberinto() {
        fondo = new ImageIcon(getClass().getResource("/img/stageBack2.png"));
    }
	
	@Override
	public void laberintoaChanged() {
	    for (int i = 0; i < getFilas(); i++) {
	        for (int j = 0; j < getColumnas(); j++) {
	            laberinto[i][j] = null; // Todo vacío
	        }
	    }
	}

}
