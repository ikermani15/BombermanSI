package modeloa;

import java.util.Observable;
import java.util.Random;

import javax.swing.ImageIcon;

public abstract class Laberinto extends Observable {
    protected final int ilara = 11; 
    protected final int zutabe = 17; 
    protected Gelaxka[][] gelaxka; // Gelaxken matrizea
    protected ImageIcon fondo;

    public Laberinto() {
    	this.gelaxka = new Gelaxka[getFilas()][getColumnas()];
        for (int i = 0; i < ilara; i++) {
            for (int j = 0; j < zutabe; j++) {	
            	gelaxka[i][j] = new Gelaxka(j, i, null);
            }
        }
    }
    
    public static Laberinto sortuLaberintoa(String mota) {
    	Laberinto laberintoMota;
		if ("Classic".equals(mota)) {
			laberintoMota = new ClassicLaberinto();
		} else if ("Soft".equals(mota)) {
			laberintoMota =  new SoftLaberinto();
		} else if ("Empty".equals(mota)) {
			laberintoMota = new EmptyLaberinto();
		} else {
			throw new IllegalArgumentException("Laberinto mota ez da zuzena.");
		}
		
		laberintoMota.laberintoaHasieratu();
		return laberintoMota;
	}
    
    // Bista notifikatu laberintoa sortu dela
    public void laberintoaHasieratu() {
    	System.out.println("Sortu deitu da!");
        setChanged();
        notifyObservers("sortu");
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
