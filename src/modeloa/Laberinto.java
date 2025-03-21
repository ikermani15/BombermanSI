package modeloa;

import java.util.Observable;
import java.util.Random;

import javax.swing.ImageIcon;

public abstract class Laberinto extends Observable {
	private static Laberinto nLab;
    protected final int ilara = 11; 
    protected final int zutabe = 17; 
    protected Gelaxka[][] gelaxka; // Gelaxken matrizea
    protected ImageIcon fondo;
    private static int blokeBigunKop = 0;

    public Laberinto() {
    	this.gelaxka = new Gelaxka[getFilas()][getColumnas()];
    }
   
    public static Laberinto getLaberinto() {
    	return nLab;
    }
    
    public static void sortuLaberintoa(String mota) {
		if ("Classic".equals(mota)) {
			nLab = new ClassicLaberinto();
		} else if ("Soft".equals(mota)) {
			nLab =  new SoftLaberinto();
		} else if ("Empty".equals(mota)) {
			nLab = new EmptyLaberinto();
		}
		
		//laberintoaHasieratu();
	}
    
    // Bista notifikatu laberintoa sortu dela
    public void laberintoaHasieratu() {
    	System.out.println("Sortu deitu da!");
        setChanged();
        notifyObservers("sortu");
    }
    
    // BlokeBigun kopurua lortzeko
    public static int getBlokeBigunKop() {
        return blokeBigunKop;
    }

    public void gehituBlokeBigunKop() {
        blokeBigunKop++;
    }

    public void kenduBlokeBigunKop() {
        if (blokeBigunKop > 0) {
            blokeBigunKop--;
        }
    }

    public Gelaxka getGelaxka(int x, int y) {
        return gelaxka[y][x];
    }

    public void setBloke(int x, int y, Bloke bloke) {
    	gelaxka[y][x].setBloke(bloke);
    }

    public ImageIcon getFondo() {
        return fondo;
    }

    public int getColumnas() {
        return zutabe;
    }

    public int getFilas() {
        return ilara;
    }

}
