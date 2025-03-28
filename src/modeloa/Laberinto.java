package modeloa;

import java.util.Observable;
import javax.swing.ImageIcon;

public class Laberinto extends Observable {
	private static Laberinto nLab;
    protected final int ilara = 11; 
    protected final int zutabe = 17; 
    protected Gelaxka[][] gelaxka; // Gelaxken matrizea
    protected ImageIcon fondo;
    private int blokeBigunKop = 0;

    public Laberinto() {
    	this.gelaxka = new Gelaxka[getIlarak()][getZutabeak()];
    }
   
    public static Laberinto getLaberinto() {
    	return nLab;
    }
    
    // Autatutako laberinto mota sortu
    public static void sortuLaberintoa(String mota) {
		if ("Classic".equals(mota)) {
			nLab = new ClassicLaberinto();
		} else if ("Soft".equals(mota)) {
			nLab =  new SoftLaberinto();
		} else if ("Empty".equals(mota)) {
			nLab = new EmptyLaberinto();
		}
	}
    
    // Bista notifikatu laberintoa sortu dela
    public void laberintoaHasieratu() {
    	System.out.println("Laberintoa sortu da!");
        setChanged();
        notifyObservers("sortu");
    }
    
    // BlokeBigun kopurua lortzeko
    public int getBlokeBigunKop() {
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

    public int getZutabeak() {
        return zutabe;
    }

    public int getIlarak() {
        return ilara;
    }

}
