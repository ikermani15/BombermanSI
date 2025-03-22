package modeloa;

import java.util.Random;

import javax.swing.ImageIcon;

public class EmptyLaberinto extends Laberinto {
	public EmptyLaberinto() {
        fondo = new ImageIcon(getClass().getResource("/img/stageBack2.png"));
        laberintoaSortu();
    }
	
	private void laberintoaSortu() {
	    for (int i = 0; i < getIlarak(); i++) {
	        for (int j = 0; j < getZutabeak(); j++) {
	        	gelaxka[i][j] = new Gelaxka(j, i, null); // Dena hutsik
	        }
	    }
	}

}
